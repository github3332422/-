package com.example.sell.dto;

import lombok.Data;

/**
 * @program: sell
 * @description: .
 * @author: 张清
 * @create: 2019-11-26 20:40
 **/
@Data
public class CartDto {
    /* 商品Id */
    private String productId;
    /* 商品数量 */
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
