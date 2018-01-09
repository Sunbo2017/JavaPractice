package com.sunbo.study.design.factory;

import com.sunbo.practice.domain.factory.RouJiaMo;
import com.sunbo.practice.domain.factory.SourRouJiaMo;
import com.sunbo.practice.domain.factory.SpicyRouJiaMo;
import com.sunbo.practice.domain.factory.SweetRouJiaMo;

/**
 * @author sunboyan
 * Date 2018/1/9
 * Description 简单工厂模式
 */
public class SimpleFactory {

    private final String SOUR = "sour";
    private final String SWEET = "sweet";
    private final String SPICY = "spicy";

    /**
     * 根据传入类型卖不同的肉夹馍
     *
     * @param type
     * @return
     */
    public RouJiaMo createRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;
        switch (type) {
            case SOUR:
                rouJiaMo = new SourRouJiaMo();
                break;
            case SWEET:
                rouJiaMo = new SweetRouJiaMo();
                break;
            case SPICY:
                rouJiaMo = new SpicyRouJiaMo();
                break;
            default:
                break;
        }
        return rouJiaMo;
    }
}
