package com.example.sell.service;

import com.example.sell.dataobject.ProductInfo;
import com.example.sell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/*
* 商品信息类
* */
public interface ProductService {
    /**
     * 查找一个
     * */
    ProductInfo findOne(String productId);

    /**
     * 查找所有
     * @return List
    */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品，有分页。
     * */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 保存商品
     * */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 添加库存
     * */
    void increaseStock(List<CartDto> cartDtoList);

    /**
     * 减少库存
     * */
    void decreaseStock(List<CartDto> cartDtoList);
}
