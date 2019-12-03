package com.example.sell.Enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数不正确"),
    CART_EMPTY(2,"购物车不能为空"),
    product_not_exit(10,"商品不存在"),
    product_stock_error(11,"商品库存不足"),
    order_not_exit(12,"订单不存在"),
    orderDetail_not_exit(13,"订单详情不存在"),
    order_status_error(14,"订单状态不正确"),
    order_update_faild(15,"订单取消失败"),
    order_detail_empty(16,"订单中无商品"),
    order_pay_status_pay(17,"支付状态不正确"),
    ;
    private Integer code;
    private String message;
    ResultEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
