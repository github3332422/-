package com.example.sell.converter;

import com.example.sell.Enums.ResultEnum;
import com.example.sell.dataobject.OrderDetail;
import com.example.sell.dto.OrderDto;
import com.example.sell.exception.SellException;
import com.example.sell.from.OrderFrom;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sell
 * @description: 转换器
 * @author: 张清
 * @create: 2019-12-03 15:17
 **/
@Slf4j
public class OrderForm2OrderDtoConverter {
    public static OrderDto convert(OrderFrom orderFrom){
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderFrom.getName());
        orderDto.setBuyerPhone(orderFrom.getPhone());
        orderDto.setBuyerAddress(orderFrom.getAddress());
        orderDto.setBuyerOpenid(orderFrom.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = gson.fromJson(orderFrom.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("[对象转化错误]");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
