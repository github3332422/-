package com.example.sell.controller;

import com.example.sell.VO.ProductInfoVO;
import com.example.sell.VO.ProductVO;
import com.example.sell.VO.ResultVO;
import com.example.sell.dataobject.ProductCategory;
import com.example.sell.dataobject.ProductInfo;
import com.example.sell.service.CategroyService;
import com.example.sell.service.ProductService;
import com.example.sell.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @description: 买家控制
 * @author: 张清
 * @create: 2019-11-26 10:02
 **/
//返回json格式
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategroyService categroyService;
    
    @GetMapping("/List")
    public ResultVO list(){
        // 1. 查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2. 查询类目(一次性查询)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategroyType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categroyService.findByCategroyRepository(categoryTypeList);

        // 3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategroyType());
            productVO.setCategoryName(productCategory.getCategroyName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategroyType().equals(productCategory.getCategroyType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
