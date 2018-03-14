package com.sunbo.test.stream;

import com.google.common.collect.Lists;
import com.sunbo.practice.domain.stream.Person;
import com.sunbo.practice.domain.stream.Staff;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    /**
     * 合并多个流,将小流合并成一个大流：用flatMap替换map
     */
    @Test
    public void flatMapStream(){

        /** 创建集合 */
        List<String> list = Lists.newArrayList();
        list.add("I am a boy");
        list.add("I love the girl");
        list.add("But the girl loves another girl");

        /** 创建流，元素按空格分词，去重后组合为一个新流 */
        List<String> newList = list.stream().map(line -> line.split(" "))
                .flatMap(Arrays :: stream)
                .distinct()
                .collect(Collectors.toList());
        newList.forEach(p -> System.out.println(p));
        System.out.println("==========================================================");

    }

    /**
     * 匹配操作，返回布尔类型
     */
    @Test
    public void matchStream(){

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

        /** 是否至少有一个无效对象 */
        Boolean result1 = personList.stream().anyMatch(p -> p.getValid().equals(Boolean.FALSE));
        /** 是否全部为无效对象 */
        Boolean result2 = personList.stream().allMatch(p -> p.getValid().equals(Boolean.FALSE));
        /** 是否全部不是无效对象 */
        Boolean result3 = personList.stream().noneMatch(p -> p.getValid().equals(Boolean.FALSE));
        System.out.println("anyMatch:result====" + result1);
        System.out.println("allMatch:result====" + result2);
        System.out.println("noneMatch:result====" + result3);
        System.out.println("==========================================================");
    }

    /**
     * 查询操作，返回一个Optional类型的元素
     */
    @Test
    public void findStream(){
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

        /** findAny能够从流中随便选一个元素出来 */
        Optional<Person> anyPerson = personList.stream().findAny();
        System.out.println(anyPerson.get().getName());
        Optional<Person> firstPerson = personList.stream().findFirst();
        System.out.println(firstPerson.get().getName());
        System.out.println("==========================================================");
    }

    /**
     * 归约操作，也叫聚合操作：将集合中的所有元素经过指定运算，折叠成一个元素输出
     * 如：求最值、平均数等，这些操作都是将一个集合的元素折叠成一个元素输出。
     */
    @Test
    public void reduceStream(){

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

        /** 提取年龄集合并求和 */
        List<Integer> ageList = personList.stream().map(p -> p.getAge()).collect(Collectors.toList());
        Integer sumAge = ageList.stream().reduce(0,(p,a) -> p + a);
        System.out.println("方式1，年龄总和：" + sumAge);

        /** 直接聚合求和 */
        Person temp = new Person();
        temp.setAge(0);
        Person sum = personList.stream().reduce(temp,(p1,p2) -> {
            p1.setAge(p1.getAge() + p2.getAge());
            return p1;
        });
        System.out.println("方式2，年龄总和：" + sum.getAge());

        /** 聚合求和，返回 Optional */
        Optional<Person> opt = personList.stream().reduce((p1,p2) -> {
            p1.setAge(p1.getAge() + p2.getAge());
            return p1;
        });
        System.out.println("方式3，年龄总和：" + opt.get().getAge());

        /** 使用integer的sum函数 */
        Integer sumAge1 = ageList.stream().reduce(0,Integer :: sum);
        System.out.println("方式4，年龄总和：" + sumAge1);
        System.out.println("==========================================================");
    }

    /**
     * 数值流使用
     * StreamAPI提供了三种数值流：IntStream、DoubleStream、LongStream，
     * 也提供了将普通流转换成数值流的三种方法：mapToInt、mapToDouble、mapToLong。
     * 每种数值流都提供了数值计算函数，如max、min、sum、average等。
     * mapToInt、mapToDouble、mapToLong进行数值操作后的返回结果分别为：OptionalInt、OptionalDouble、OptionalLong。
     * 它们是Optional的一个子类，能够判断流是否为空，并对流为空的情况作相应的处理。
     */
    @Test
    public void numberStream(){
        /** 准备使用对象 */
        Person person1 = new Person("001","令狐冲",0,30,true);
        Person person2 = new Person("002","张无忌",0,31,true);
        Person person3 = new Person("003","周芷若",1,32,true);
        Person person4 = new Person("004","杨过",0,30,true);
        Person person5 = new Person("005","郭靖",0,30,true);
        Person person6 = new Person("006","任盈盈",1,30,false);
        Person person7 = new Person("007","岳灵珊",1,36,false);

        /** 创建集合 */
        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);

        /** 获取数值流 */
        IntStream stream = personList.stream().mapToInt(Person::getAge);
        stream.forEach(a -> System.out.println("年龄 = " + a));

        /** 求最大值max */
        OptionalInt maxAge = personList.stream().mapToInt(Person :: getAge).max();
        System.out.println("最大年龄：" + maxAge.getAsInt());

        /** 求最小值min */
        OptionalInt minAge = personList.stream().mapToInt(Person :: getAge).min();
        System.out.println("最小年龄：" + minAge.getAsInt());

        /** 年龄求和 */
        Integer sumAge = personList.stream().mapToInt(Person :: getAge).sum();
        System.out.println("年龄总和：" + sumAge);

        /** 求平均值 */
        OptionalDouble avgAge = personList.stream().mapToInt(Person :: getAge).average();
        System.out.println("年龄平均值：" + avgAge.getAsDouble());

        System.out.println("==========================================================");

    }

    /**
     * 使用java8进行集合排序
     */
    @Test
    public void listSort8(){
        /** 准备使用对象 */
        Person person1 = new Person("001","令狐冲",0,30,true);
        Person person2 = new Person("002","张无忌",0,40,true);
        Person person3 = new Person("003","周芷若",1,20,true);
        Person person4 = new Person("004","杨过",0,35,true);
        Person person5 = new Person("005","郭靖",0,41,true);
        Person person6 = new Person("006","任盈盈",1,25,false);
        Person person7 = new Person("007","岳灵珊",1,10,false);

        /** 创建集合 */
        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);
        /** 排序 */
        personList.sort(Comparator.comparing(Person::getAge));
        personList.stream().forEach(p -> System.out.println(p.getName()));
    }

    /**
     * 不使用java8进行集合排序
     */
    @Test
    public void listSort(){
        /** 准备使用对象 */
        Person person4 = new Person("004","杨过",0,35,true);
        Person person1 = new Person("001","令狐冲",0,30,true);
        Person person2 = new Person("002","张无忌",0,40,true);
        Person person3 = new Person("003","周芷若",1,20,true);
        Person person5 = new Person("005","郭靖",0,41,true);
        Person person6 = new Person("006","任盈盈",1,25,false);
        Person person7 = new Person("007","岳灵珊",1,10,false);

        /** 创建集合 */
        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);
        /** 排序，使用Collections工具类 */
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });
        personList.stream().forEach(p -> System.out.println(p.getName()));
    }
}
