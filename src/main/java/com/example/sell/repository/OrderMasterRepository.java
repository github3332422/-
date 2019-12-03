package com.example.sell.repository;

import com.example.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    /*
    * 根据某个人的openid来进行查询
    * */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
