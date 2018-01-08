package com.sunbo.test.design;

import com.sunbo.study.design.LazySingleton;
import com.sunbo.study.design.HungrySingleton;
import com.sunbo.study.design.RegSingleton;
import com.sunbo.study.design.SyncLazySingleton;
import org.junit.Test;

/**
 * @author sunboyan
 * Date 2018/1/8
 * Description 单例模式使用测试
 */
public class DesignPatternTest {

    @Test
    public void testHungrySingleton(){
        HungrySingleton singleton = HungrySingleton.getInstance();
        singleton.print();
    }

    @Test
    public void testLazySingleton(){
        LazySingleton singleton = LazySingleton.getInstance();
        singleton.print();
    }

    @Test
    public void testSyncLazySingleton(){
        SyncLazySingleton singleton = SyncLazySingleton.getInstance();
        singleton.print();
    }

    @Test
    public void testRegSingleton(){
        RegSingleton singleton = RegSingleton.getInstance(null);
        singleton.print();
    }

}
