package com.sunbo.study.design;

/**
 * @author sunboyan
 * Date 2018/1/8
 * Description 懒汉式单例模式，线程安全
 */
public class SyncLazySingleton {
    /**
     * 私有构造方法
     */
    private SyncLazySingleton(){
        System.out.println("懒汉式单例模式，构造方法私有化");
    }

    /**
     * 静态私有本类实例对象
     */
    private static SyncLazySingleton instance = null;

    /**
     * 公共静态获取实例对象方法,加同步锁
     * @return
     */
    public static synchronized SyncLazySingleton getInstance(){
        System.out.println("声明静态私有本类实例对象,值为null，定义公共静态获取实例对象方法,加同步锁");
        if (null == instance){
            instance = new SyncLazySingleton();
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
