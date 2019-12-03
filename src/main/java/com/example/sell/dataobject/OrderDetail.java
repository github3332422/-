package com.example.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @program: sell
 * @description: 订单详情表
 * @author: 张清
 * @create: 2019-11-26 18:16
 **/
@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    /*订单号*/
    @Id
    private String detailId;
    /*订单id*/
    private String orderId;
    /*商品id*/
    private String productId;
    /*商品名字*/
    private String productName;
    /*商品价格*/
    private BigDecimal productPrice;
    /*商品数量*/
    private Integer productQuantity;
    /*商品小图*/
    private String productIcon;
}
