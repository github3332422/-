package com.example.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @program: sell
 * @description: 商品信息类
 * @author: 张清
 * @create: 2019-11-26 08:01
 **/
@Entity
@DynamicUpdate
@Data
public class ProductInfo {
    @Id
    private String productId;
    /*名字*/
    private String productName;
    /*单价*/
    private BigDecimal productPrice;
    /*库存*/
    private Integer productStock;
    /*描述*/
    private String productDescription;
    /*小图 存链接*/
    private String productIcon;
    /*状态*/
    private Integer productStatus;
    /*类目编号*/
    private Integer categroyType;
}
