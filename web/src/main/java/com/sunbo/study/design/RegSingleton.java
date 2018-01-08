package com.sunbo.study.design;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author sunboyan
 * Date 2018/1/8
 * Description 登记式单例模式
 */
public class RegSingleton {

    /**
     * 静态私有map
     */
    private static Map<String,RegSingleton> map = Maps.newHashMap();

    /**
     * 静态代码块，实例化自身并put入map中
     */
    static {
        System.out.println("静态代码块，实例化自身并put入map中");
        RegSingleton regSingleton = new RegSingleton();
        map.put(regSingleton.getClass().getName(),regSingleton);
    }
    /**
     * 受保护的构造方法
     */
    protected RegSingleton(){
        System.out.println("受保护的构造方法");
    }

    /**
     * 静态工厂方法,返还此类惟一的实例
     * @return
     */
    public static RegSingleton getInstance(String name){
        if(name == null) {
            name = RegSingleton.class.getName();
            System.out.println("name == null"+"--->name="+name);
        }
        if(null == map.get(name)){
            try {
                map.put(name,(RegSingleton) Class.forName(name).newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map.get(name);
    }
    /**
     * 单例模式提供的功能性方法
     */
    public void print() {
        System.out.println("调用登记式单例模式提供的功能性方法");
    }
}
