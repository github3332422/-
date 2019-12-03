package com.example.sell.repository;


import com.example.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("zq");
        orderMaster.setBuyerPhone("18814664090");
        orderMaster.setBuyerAddress("齐齐哈尔大学19#611");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmout(new BigDecimal(2.5));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByOpenId(){
        PageRequest request = new PageRequest(0,2);
        Page<OrderMaster> result = repository.findByBuyerOpenid("110110", request);
        System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0,result.getTotalElements());
    }

}
