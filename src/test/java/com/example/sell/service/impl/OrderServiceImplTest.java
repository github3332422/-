package com.example.sell.service.impl;

import com.example.sell.Enums.OrderStatusEnum;
import com.example.sell.Enums.PayStatusEnum;
import com.example.sell.dataobject.OrderDetail;
import com.example.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "110110";
    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("张清");
        orderDto.setBuyerAddress("齐齐哈尔大学19#611");
        orderDto.setBuyerPhone("18814664090");
        orderDto.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("2");
        o1.setProductQuantity(1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.create(orderDto);
        log.info("[创建订单] result={}",result);
    }

    @Test
    public void findOne() {
        OrderDto result = orderService.findOne("1574816789259787132");
        log.info("[查询单个订单] result={}", result);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage = orderService.findList("110110", request);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne("1574816789259787132");
        orderService.cancel(orderDto);
    }

    @Test
    public void finish() {
        OrderDto orderDto = orderService.findOne("1575346485107604425");
        OrderDto result = orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDto orderDto = orderService.findOne("1575346843357755755");
        OrderDto result = orderService.paid(orderDto);
        System.out.println(result.getPayStatus());
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}
