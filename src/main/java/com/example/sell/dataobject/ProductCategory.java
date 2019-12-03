package com.example.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: sell
 * @description: 类目
 * @author: 张清
 * @create: 2019-11-25 20:49
 **/
//实现数据库映射
@Entity
//实现动态的更新时间
@DynamicUpdate
//实现get set 和 toString 方法 需要导入lombok依赖
@Data
public class ProductCategory {
    /*类目id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categroyId;

    /*类目名字*/
    private String categroyName;

    /*类目编号*/
    private Integer categroyType;

//    /*创建订单时间*/
//    private Date createTime;
//
//    /*修改订单时间*/
//    private Date updateTime;


    public ProductCategory(String categroyName, Integer categroyType) {
        this.categroyName = categroyName;
        this.categroyType = categroyType;
    }

    public ProductCategory() {
    }
}
