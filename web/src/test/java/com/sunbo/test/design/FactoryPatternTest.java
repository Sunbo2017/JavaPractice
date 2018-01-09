package com.sunbo.test.design;

import com.sunbo.study.design.factory.RouJiaMoStore;
import com.sunbo.study.design.factory.SimpleFactory;
import org.junit.Test;

/**
 * @author sunboyan
 * Date 2018/1/9
 * Description 工厂设计模式测试
 */
public class FactoryPatternTest {

    @Test
    public void testSimpleFactory(){
        RouJiaMoStore store = new RouJiaMoStore(new SimpleFactory());
        store.sellRouJiaMo("sweet");
    }
}
