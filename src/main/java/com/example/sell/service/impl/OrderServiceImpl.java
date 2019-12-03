package com.example.sell.service.impl;

import com.example.sell.Enums.OrderStatusEnum;
import com.example.sell.Enums.PayStatusEnum;
import com.example.sell.Enums.ResultEnum;
import com.example.sell.converter.OrderMaster2OrderDtoConverter;
import com.example.sell.dataobject.OrderDetail;
import com.example.sell.dataobject.OrderMaster;
import com.example.sell.dataobject.ProductInfo;
import com.example.sell.dto.CartDto;
import com.example.sell.dto.OrderDto;
import com.example.sell.exception.SellException;
import com.example.sell.repository.OrderDetailRepository;
import com.example.sell.repository.OrderMasterRepository;
import com.example.sell.service.OrderService;
import com.example.sell.service.ProductService;
import com.example.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @description: 实现类
 * @author: 张清
 * @create: 2019-11-26 19:33
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String tmpId = KeyUtil.genUniqueKey();
        //定义一个总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//        List<CartDto> cartDtoList = new ArrayList<>();
        // 1. 查询商品 (数量,价格)
        for(OrderDetail orderDetail: orderDto.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.product_not_exit);
            }
            // 2. 计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(tmpId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
//            CartDto cartDto = new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDtoList.add(cartDto);

        }
        // 3. 写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(tmpId);
        // 拷贝的时候如果只是null的话也会被拷贝
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(tmpId);
        orderMaster.setOrderAmout(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.Faild.getCode());
        orderMasterRepository.save(orderMaster);

        // 4. 扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e->
                new CartDto(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);

        // 5. 向商家发送消息
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.order_not_exit);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.orderDetail_not_exit);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtopage = new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());
        return orderDtopage;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        // 判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单] 失败 orderId = {} orderStatus = {}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.order_status_error);
        }

        // 修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("[取消订单失败] orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.order_update_faild);
        }

        // 返还库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("[取消订单失败] orderDto={}",orderDto);
            throw new SellException(ResultEnum.order_update_faild);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDtoList);

        // 如果已字符,用户退款
        if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){

        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        // 1. 判断状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单状态不正确]，orderId={} , orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.order_status_error);
        }
        // 2. 修改状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("[订单更新失败] orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.order_update_faild);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        // 1. 判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单状态不正确]，orderId={} , orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.order_status_error);
        }

        // 2. 判断支付状态
        if(!orderDto.getPayStatus().equals(PayStatusEnum.Faild.getCode())){
            log.error("[支付状态不正确]，paySTatus={}",orderDto.getPayStatus());
            throw new SellException(ResultEnum.order_pay_status_pay);
        }

        // 3. 修改支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("[支付更新失败] orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.order_update_faild);
        }
        return orderDto;
    }
}
