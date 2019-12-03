package com.example.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: sell
 * @description: 商品详情表
 * @author: 张清
 * @create: 2019-11-26 10:24
 **/
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private String productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

}
