package com.example.sell.service;

import com.example.sell.dataobject.ProductCategory;

import java.util.List;

/*
* 服务层接口
* */
public interface CategroyService {

    ProductCategory findOne (Integer categroyId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategroyRepository(List<Integer> list);

    /*新增*/
    ProductCategory save(ProductCategory productCategroy);
}
