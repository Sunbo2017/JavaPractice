package com.sunbo.practice.domain.stream;

import lombok.Data;

/**
 * @author sunboyan
 * Date 2018/1/26
 * Description
 */
@Data
public class Person {
    /** 人员编号，唯一不可重复 */
    private String id;
    private String name;
    private Integer age;
    /** 0代表男，1代表女 */
    private Integer sex;
    private String province;
    private String city;
    private String district;
    private String address;
    /** 学历 */
    private String education;
    /** true代表有效，false代表无效 */
    private Boolean valid;

    public Person(){}

    public Person(String id, String name,Integer age,Boolean valid){
        this.id = id;
        this.name = name;
        this.age = age;
        this.valid = valid;
    }

    public Person(String id, String name,Integer sex,Integer age,Boolean valid){
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.valid = valid;
    }

}
