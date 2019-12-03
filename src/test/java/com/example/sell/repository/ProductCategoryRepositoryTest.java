package com.example.sell.repository;

import com.example.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategroyRepository repository;

    /*实现查询*/
    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findOne(4);
        System.out.println(productCategory.toString());
    }

    /*实现添加*/
    @Test
    /*事务完全回滚，和service不一样，service是抛出异常后回滚*/
    @Transactional
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategroyName("最实惠");
        productCategory.setCategroyType(2);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    /*实现修改*/
    @Test
    public void updateTest(){
        ProductCategory productCategory = repository.findOne(4);
        productCategory.setCategroyType(1);
        repository.save(productCategory);
    }

    @Test
    public void findByCategeryTypeInTest(){
        List<Integer> list = Arrays.asList(1,4);
        //categroyType
        List<ProductCategory> result = repository.findByCategroyTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}