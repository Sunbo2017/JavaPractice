package com.sunbo.practice.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sunboyan
 * Date 2017/10/27
 * Description
 */
@Data
public class Student implements Serializable {

    private String name;
    private char sex;
    private int year;
    private double gpa;

    public Student() {

    }

    public Student(String name, char sex, int year, double gpa) {
        this.name = name;
        this.sex = sex;
        this.year = year;
        this.gpa = gpa;
    }
}
