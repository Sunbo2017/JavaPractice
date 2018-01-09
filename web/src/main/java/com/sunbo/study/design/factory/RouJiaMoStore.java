package com.sunbo.study.design.factory;

import com.sunbo.practice.domain.factory.RouJiaMo;

/**
 * @author sunboyan
 * Date 2018/1/9
 * Description 肉夹馍商店
 */
public class RouJiaMoStore {
    private SimpleFactory factroy;

    public RouJiaMoStore(SimpleFactory factroy) {
        this.factroy = factroy;
    }

    /**
     * 根据传入类型卖不同的肉夹馍
     *
     * @param type
     * @return
     */
    public RouJiaMo sellRouJiaMo(String type) {
        RouJiaMo rouJiaMo = factroy.createRouJiaMo(type);
        rouJiaMo.receiveName();
        rouJiaMo.prepare();
        rouJiaMo.fire();
        rouJiaMo.pack();
        return rouJiaMo;
    }
}
