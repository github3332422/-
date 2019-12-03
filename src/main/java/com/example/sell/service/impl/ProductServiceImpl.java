package com.example.sell.service.impl;

import com.example.sell.Enums.ProductStatusEnum;
import com.example.sell.Enums.ResultEnum;
import com.example.sell.dataobject.ProductInfo;
import com.example.sell.dto.CartDto;
import com.example.sell.exception.SellException;
import com.example.sell.repository.ProductInfoRepository;
import com.example.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: sell
 * @description: 商品信息操作
 * @author: 张清
 * @create: 2019-11-26 08:41
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto: cartDtoList){
            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.product_not_exit);
            }
            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto:cartDtoList){
            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.product_not_exit);
            }
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.product_stock_error);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
}
