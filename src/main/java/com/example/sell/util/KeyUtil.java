package com.example.sell.util;

import java.util.Random;

/**
 * @program: sell
 * @description: .
 * @author: 张清
 * @create: 2019-11-26 20:17
 **/
public class KeyUtil {

    /*
    * 生成唯一的主键
    * 格式: 时间 + 随机数
    * @return
    * */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
