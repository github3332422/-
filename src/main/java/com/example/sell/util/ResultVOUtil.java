package com.example.sell.util;

import com.example.sell.VO.ResultVO;

import java.util.Arrays;

/**
 * @program: sell
 * @description: 对各种状态码进行返回
 * @author: 张清
 * @create: 2019-11-26 11:17
 **/
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setDate(Arrays.asList(object));
        resultVO.setCode(200);
        resultVO.setMsg("获取成功");
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
