package com.sunbo.test.stream;

import com.google.common.collect.Lists;
import com.sunbo.practice.domain.stream.Person;
import com.sunbo.practice.domain.stream.Staff;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sunboyan
 * Date 2018/1/26
 * Description 各种流操作示例
 */
public class StreamApiOperateTest {

    /**
     * filter 筛选
     */
    @Test
    public void filterStream(){

        /** 准备使用对象 */
        Person person1 = new Person("001","令狐冲",0,30,true);
        Person person2 = new Person("002","张无忌",0,30,true);
        Person person3 = new Person("003","周芷若",1,30,true);
        Person person4 = new Person("004","杨过",0,30,true);
        Person person5 = new Person("005","郭靖",0,30,true);
        Person person6 = new Person("006","任盈盈",1,30,false);
        Person person7 = new Person("007","岳灵珊",1,30,false);

        /** 创建集合 */
        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);

        /** 创建流，该流使用之后就会关闭流，不允许重复引用同一个流，需要每次使用流时重新创建流 */
        Stream<Person> personStream = personList.stream();

        /** 筛选有效对象 */
        List<Person> personTrue = personStream.filter(p -> p.getValid() == true).collect(Collectors.toList());
        personTrue.forEach(p -> System.out.println(p.getName()));
        System.out.println("==========================================================");

        /** 筛选无效对象 */
        /** stream是一次性产品，使用之后就会关闭流，不允许重复引用同一个流，需要每次使用流时重新创建流 */
        List<Person> personFalse = personList.stream().filter(p -> p.getValid() == false).collect(Collectors.toList());
        personFalse.forEach(p -> System.out.println(p.getName()));
        System.out.println("==========================================================");

        /** 筛选有效女性对性*/
        List<Person> womanPersonTrue = personList.stream().filter(p -> p.getValid() == true && p.getSex() == 1).collect(Collectors.toList());
        womanPersonTrue.forEach(p -> System.out.println(p.getName()));

    }

    /**
     * distinct 去重
     */
    @Test
    public void distinctStream(){

        /** 准备使用对象 */
        Person person1 = new Person("001","令狐冲",0,30,true);
        Person person2 = new Person("002","张无忌",0,30,true);
        Person person3 = new Person("003","周芷若",1,31,true);
        Person person4 = new Person("004","杨过",0,31,true);
        Person person5 = new Person("005","郭靖",0,32,true);
        Person person6 = new Person("006","任盈盈",1,33,false);
        Person person7 = new Person("007","岳灵珊",1,34,false);

        /** 创建集合 */
        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);
        personList.add(person1);
        personList.add(person2);
        personList.add(person5);
        personList.add(person6);
        personList.add(person6);

        /** 去掉重复对像 */
        List<Person> people = personList.stream().distinct().collect(Collectors.toList());
        people.forEach(p ->System.out.println(p.getName()));
        System.out.println("==========================================================");

        /** 获取年龄集合并去重 */
        List<Integer> ages = personList.stream().map(p -> p.getAge()).distinct().collect(Collectors.toList());
        ages.forEach(a -> System.out.println(a));
    }

    /**
     * 截取与跳过 limit skip
     */
    @Test
    public void limitAndSkipStream(){

        /** 准备使用对象 */
        Person person1 = new Person("001","令狐冲",0,30,true);
        Person person2 = new Person("002","张无忌",0,30,true);
        Person person3 = new Person("003","周芷若",1,30,true);
        Person person4 = new Person("004","杨过",0,30,true);
        Person person5 = new Person("005","郭靖",0,30,true);
        Person person6 = new Person("006","任盈盈",1,30,false);
        Person person7 = new Person("007","岳灵珊",1,30,false);

        /** 创建集合 */
        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);

        /** 截取前3条数据 */
        List<Person> limit3PersonList = personList.stream().limit(3).collect(Collectors.toList());
        limit3PersonList.forEach(p ->System.out.println(p.getName()));
        System.out.println("==========================================================");

        /** 跳过前3条数据 */
        List<Person> skip3PersonList = personList.stream().skip(3).collect(Collectors.toList());
        skip3PersonList.forEach(p ->System.out.println(p.getName()));
        System.out.println("==========================================================");

        /** 跳过前3条并截取3条数据 */
        List<Person> skip3Limit3PersonList = personList.stream().skip(3).limit(3).collect(Collectors.toList());
        skip3Limit3PersonList.forEach(p ->System.out.println(p.getName()));
        System.out.println("==========================================================");

        /** 截取前5条并跳过2条数据 */
        List<Person> limit5Skip2PersonList = personList.stream().limit(5).skip(2).collect(Collectors.toList());
        limit5Skip2PersonList.forEach(p ->System.out.println(p.getName()));
        System.out.println("==========================================================");
    }

    /**
     * map 映射 流会将每一个元素输送给map函数，并执行map中的Lambda表达式，最后将执行结果存入一个新的流中。
     */
    @Test
    public void mapStream(){

        /** 准备使用对象 */
        Person person1 = new Person("001","令狐冲",0,30,true);
        Person person2 = new Person("002","张无忌",0,30,true);
        Person person3 = new Person("003","周芷若",1,30,true);
        Person person4 = new Person("004","杨过",0,30,true);
        Person person5 = new Person("005","郭靖",0,30,true);
        Person person6 = new Person("006","任盈盈",1,30,false);
        Person person7 = new Person("007","岳灵珊",1,30,false);

        /** 创建集合 */
        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);

        /** 获取姓名返回新集合 */
        List<String> nameList = personList.stream().map(p -> p.getName()).collect(Collectors.toList());
        nameList.forEach(n ->System.out.println(n));
        System.out.println("==========================================================");

        /** 为每个对象添加详细信息 */
        List<Person> detailPersonList = personList.stream().map(p -> {
            p.setProvince("汉东省");
            p.setCity("津港市");
            p.setDistrict("长丰区");
            return p;
        }).collect(Collectors.toList());
        detailPersonList.forEach(p ->System.out.println(p.getName() + "," + p.getProvince() + "," + p.getCity() + "," + p.getDistrict()));
        System.out.println("==========================================================");

        /** 获取人员信息创建员工并返回员工集合 */
        List<Staff> staffList = personList.stream().map(p -> {
            Long seconds = (long) p.getAge() * 365 * 24 * 60 * 60 * 1000;
            Long birthTime = new Date().getTime() - seconds;
            Date birthDate = new Date(birthTime);
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String birth = df.format(birthDate);
            Staff staff = new Staff(p.getId(),p.getName(),birth);
            return staff;
        }).collect(Collectors.toList());
        staffList.forEach(p ->System.out.println(p.getName() + "," + p.getBirth()));
        System.out.println("==========================================================");

    }

}
