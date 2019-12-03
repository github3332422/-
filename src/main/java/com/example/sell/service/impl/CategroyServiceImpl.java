package com.example.sell.service.impl;

import com.example.sell.dataobject.ProductCategory;
import com.example.sell.repository.ProductCategroyRepository;
import com.example.sell.service.CategroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sell
 * @description: 服务层
 * @author: 张清
 * @create: 2019-11-26 07:31
 **/
@Service
public class CategroyServiceImpl implements CategroyService {

    @Autowired
    private ProductCategroyRepository repository;
    @Override
    public ProductCategory findOne(Integer categroyId) {
        return repository.findOne(categroyId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategroyRepository(List<Integer> list) {
        return repository.findByCategroyTypeIn(list);
    }

    @Override
    public ProductCategory save(ProductCategory productCategroy) {
        return repository.save(productCategroy);
    }
}
