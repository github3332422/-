package com.example.sell.service.impl;

import com.example.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategroyServiceImplTest {
    @Autowired
    private CategroyServiceImpl categroyService;

    @Test
    public void findOne() {
        ProductCategory productCategroy = categroyService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategroy.getCategroyId());
        System.out.println(productCategroy.toString());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categroyService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategroyRepository() {
        List<Integer> list = Arrays.asList(1,4);
        List<ProductCategory> result = categroyService.findByCategroyRepository(list);
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男生专享",10);
        ProductCategory result = categroyService.save(productCategory);
        Assert.assertNotNull(result);
    }
}
