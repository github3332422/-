package com.example.sell.Enums;

import lombok.Getter;

import javax.persistence.Entity;

/**
 * @program: sell
 * @description: .
 * @author: 张清
 * @create: 2019-11-26 14:12
 **/
@Getter
public enum PayStatusEnum {
    SUCCESS(1,"已字符"),
    Faild(0,"未支付"),
    ;

    private Integer code;
    private String message;
    PayStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
