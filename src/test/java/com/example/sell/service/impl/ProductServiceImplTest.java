package com.example.sell.service.impl;

import com.example.sell.Enums.ProductStatusEnum;
import com.example.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("1");
        Assert.assertNotEquals("1",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
//        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setProductName("鸡腿");
        productInfo.setProductPrice(new BigDecimal(4.0));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃的鸡腿");
        productInfo.setProductIcon("http://baidu.com");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategroyType(1);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }
}
