package com.example.sell.dto;


import com.example.sell.Enums.OrderStatusEnum;
import com.example.sell.Enums.PayStatusEnum;
import com.example.sell.dataobject.OrderDetail;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: sell
 * @description: .
 * @author: 张清
 * @create: 2019-11-26 19:28
 **/
@Data
public class OrderDto {
    /*订单id*/
    private String orderId;
    /*顾客姓名*/
    private String buyerName;
    /*送餐电话*/
    private String buyerPhone;
    /*送餐地址*/
    private String buyerAddress;
    /*微信ID号*/
    private String buyerOpenid;
    /*订单金额*/
    private BigDecimal orderAmout;
    /*订单状态*/
    private Integer orderStatus;
    /*订单支付状态*/
    private Integer payStatus;
    /*创建订单时间*/
    private Date createTime;
    /*修改订单时间*/
    private Date updateTime;


    /*订单详情*/
    @Transient
    List<OrderDetail> orderDetailList;
}
