package com.example.sell.converter;

import com.example.sell.dataobject.OrderMaster;
import com.example.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @description: 转换器
 * @author: 张清
 * @create: 2019-11-27 16:04
 **/
public class OrderMaster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->
                convert(e)
        ).collect(Collectors.toList());
    }
}
