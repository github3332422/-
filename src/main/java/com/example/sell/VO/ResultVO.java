package com.example.sell.VO;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @program: sell
 * @description: 返回结果数据,http返回最外层数据
 * @author: 张清
 * @create: 2019-11-26 10:08
 **/
@Data
public class ResultVO <T>{
    /*状态码*/
    private Integer code;
    /*状态信息*/
    private String msg;
    /*返回的具体内容*/
    private T date;
}
