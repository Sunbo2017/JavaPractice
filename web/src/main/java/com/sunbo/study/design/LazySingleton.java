package com.sunbo.study.design;

/**
 * @author sunboyan
 * Date 2018/1/8
 * Description 懒汉式单例模式
 */
public class LazySingleton {
    /**
     * 私有构造方法
     */
    private LazySingleton(){
        System.out.println("懒汉式单例模式，构造方法私有化，在第一次调用的时候实例化自己");
    }

    /**
     * 静态私有本类实例对象
     */
    private static LazySingleton instance = null;

    /**
     * 公共静态获取实例对象方法
     * @return
     */
    public static LazySingleton getInstance(){
        System.out.println("声明静态私有本类实例对象,值为null，定义公共静态获取实例对象方法");
        if (null == instance){
            instance = new LazySingleton();
        }
        return instance;
    }

    /**
     * 单例模式提供的功能性方法
     */
    public void print() {
        System.out.println("调用懒汉式单例模式提供的功能性方法");
    }
}
