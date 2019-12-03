package com.example.sell.repository;

import com.example.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: sell
 * @description: SpringBoot jpa
 * @author: 张清
 * @create: 2019-11-25 20:57
 **/
public interface ProductCategroyRepository extends JpaRepository<ProductCategory,Integer> {
//    categroyType
    List<ProductCategory> findByCategroyTypeIn(List<Integer> categroyTypeList);

}
