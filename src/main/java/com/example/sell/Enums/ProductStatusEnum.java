package com.example.sell.Enums;

import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
public enum ProductStatusEnum {
    UP(0,"在线"),
    DOWN(1,"不在线");

    private Integer code;
    private String message;
    ProductStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
