package com.example.sell;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @program: sell
 * @description: 日志测试类
 * @author: 张清
 * @create: 2019-11-25 16:35
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class loggerTest {
    /**
     * 一定要写当前类,或者使用 @Slf4j注解
     */
    private final Logger logger = LoggerFactory.getLogger(loggerTest.class);

    @Test
    public void test1(){
        String name = "张清";
        String password = "123456789";
        logger.debug("debug ...");
        logger.info("info ...");
        logger.error("error ...");
        //日志输出变量
        logger.info("name: {} , password: {}",name,password);
    }
}
