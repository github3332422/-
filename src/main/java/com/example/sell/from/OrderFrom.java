package com.example.sell.from;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @program: sell
 * @description: 用来表单验证
 * @author: 张清
 * @create: 2019-12-03 14:48
 **/
@Data
public class OrderFrom {
    /*
    * 买家姓名
    * */
    @NotEmpty(message = "姓名必填")
    private String name;

    /*
    * 买家手机号
    * */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /*
    * 买家地址
    * */
    @NotEmpty(message = "地址必填")
    private String address;

    /*
    * 买家openId
    * */
    @NotEmpty(message = "openid必填")
    private String openid;

    /*
    * 购物车信息
    * */
    @NotEmpty(message = "购物车必填")
    private String items;


}
