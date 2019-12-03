package com.example.sell.exception;

import com.example.sell.Enums.ResultEnum;

/**
 * @program: sell
 * @description: 异常自定义
 * @author: 张清
 * @create: 2019-11-26 20:01
 **/
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }

}
