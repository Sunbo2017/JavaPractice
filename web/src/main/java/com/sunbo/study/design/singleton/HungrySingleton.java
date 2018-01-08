package com.sunbo.study.design.singleton;

/**
 * @author sunboyan
 * Date 2018/1/8
 * Description 饿汉式单例设计模式
 */
public class HungrySingleton {

    /**
     * 私有构造方法
     */
    private HungrySingleton(){
        System.out.println("饿汉式单例设计模式，构造方法私有化，在类初始化时已经自行实例化 ");
    }

    /**
     * 静态私有本类实例对象
     */
    private static final HungrySingleton instance = new HungrySingleton();

    /**
     * 公共静态获取实例对象方法
     * @return
     */
    public static HungrySingleton getInstance(){
        System.out.println("声明静态私有本类实例对象，定义公共静态获取实例对象方法");
        return instance;
    }

    /**
     * 单例模式提供的功能性方法
     */
    public void print() {
        System.out.println("调用饿汉式单例模式提供的功能性方法");
    }
}
