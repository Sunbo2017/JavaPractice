package com.sunbo.practice.domain.stream;

import lombok.Data;

/**
 * @author sunboyan
 * Date 2018/1/26
 * Description 员工类
 */
@Data
public class Staff {
    /** 员工编号，唯一不可重复 */
    private String id;
    private String name;
    private String birth;

    public Staff() {
    }

    public Staff(String id, String name, String birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }
}
