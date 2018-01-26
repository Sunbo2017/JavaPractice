package com.sunbo.test.stream;

import com.google.common.collect.Lists;
import com.sunbo.practice.domain.stream.Person;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author sunboyan
 * Date 2018/1/26
 * Description 获取流的几种方法
 */
public class StreamApiGetTest {

    /**
     * 1.由集合获取
     */
    @Test
    public void getStreamFromList(){

        /** 准备使用对象 */
        Person person1 = new Person("001","令狐冲",30,true);
        Person person2 = new Person("002","张无忌",30,true);
        Person person3 = new Person("003","段誉",30,true);
        Person person4 = new Person("004","杨过",30,true);
        Person person5 = new Person("005","郭靖",30,true);

        /** 创建集合 */
        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);

        /** 创建流 */
        Stream<Person> personStream = personList.stream();
        personStream.forEach(p -> System.out.println(p.getName()));
    }

    /**
     * 2.由数组获取
     */
    @Test
    public void getStreamFromArray(){

        /** 准备使用对象 */
        Person person1 = new Person("001","令狐冲",30,true);
        Person person2 = new Person("002","张无忌",30,true);
        Person person3 = new Person("003","段誉",30,true);
        Person person4 = new Person("004","杨过",30,true);
        Person person5 = new Person("005","郭靖",30,true);

        /** 创建数组 */
        Person[] personArray = new Person[]{person1,person2,person3,person4,person5};

        /** 创建流 */
        Stream<Person> personStream = Arrays.stream(personArray);
        personStream.forEach(p -> System.out.println(p.getName()));
    }

    /**
     * 3.使用静态工厂直接由值创建流
     */
    @Test
    public void getStreamFromValue(){
        /** 准备使用对象 */
        Person person1 = new Person("001","令狐冲",30,true);
        Person person2 = new Person("002","张无忌",30,true);
        Person person3 = new Person("003","段誉",30,true);
        Person person4 = new Person("004","杨过",30,true);
        Person person5 = new Person("005","郭靖",30,true);

        /** 创建流 */
        Stream<Person> personStream = Stream.of(person1,person2,person3,person4,person5);
        personStream.forEach(p -> System.out.println(p.getName()));
    }

    /**
     * 4.读取文件创建流
     */
    @Test
    public void getStreamFromFile(){

        /** 文件路径 */
        Path path = Paths.get("E:\\person.txt");

        /** 创建流 */
        try(Stream personStream = Files.lines(path, StandardCharsets.UTF_8)){
            personStream.forEach(System.out :: println);
        }catch(IOException e){
            e.printStackTrace();
        }
    }




}
