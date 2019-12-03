package com.example.sell.repository;


import com.example.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("汉堡");
        productInfo.setProductPrice(new BigDecimal(7.0));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃的汉堡");
        productInfo.setProductIcon("http://baidu.com");
        productInfo.setProductStatus(0);
        productInfo.setCategroyType(1);
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}
