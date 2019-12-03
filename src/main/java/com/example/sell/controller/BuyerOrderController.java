package com.example.sell.controller;

import com.example.sell.Enums.ResultEnum;
import com.example.sell.VO.ResultVO;
import com.example.sell.converter.OrderForm2OrderDtoConverter;
import com.example.sell.dto.OrderDto;
import com.example.sell.exception.SellException;
import com.example.sell.from.OrderFrom;
import com.example.sell.service.OrderService;
import com.example.sell.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: sell
 * @description: 1
 * @author: 张清
 * @create: 2019-12-03 14:46
 **/
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    // 1. 创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderFrom orderFrom,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[创建订单]参数不正确，orderFrom={}",orderFrom);
//            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderFrom);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.info("[创建订单] 购物车不能为空");
//            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto cresteresult = orderService.create(orderDto);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",cresteresult.getOrderId());

        return ResultVOUtil.success(map);
    }

    // 2. 订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDto>> list(@RequestParam String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单列表] openid为空");
//            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid, pageRequest);

        return ResultVOUtil.success(orderDtoPage.getContent());
    }
    // 3. 订单详情

    // 4. 取消订单
}
