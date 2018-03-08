package com.sunbo.test.recursion;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunboyan
 * Date 2018/3/8
 * Description 递归算法练习
 * 递归： 就是函数自身调用自身。
 * 什么时候用递归呢？
 * 当一个功能被重复使用，而每一次使用该功能时的参数不确定，都由上次的功能元素结果来确定。
 * 简单说： 功能内部又用到该功能，但是传递的参数值不确定。(每次功能参与运算的未知内容不确定)。
 * 递归的注意事项：
 * 1：一定要定义递归的条件。
 * 2：递归的次数不要过多。容易出现 StackOverflowError 栈内存溢出 错误。
 * 其实递归就是在栈内存中不断的加载同一个函数。
 */
public class RecursionTest {

    /**
     * 找出指定目录下的所有文件
     * 递归
     * @param files
     * @return
     */
    public List<File> listFiles(File files) {
        List<File> fileList = new ArrayList<>();
        if (files.isDirectory()) {
            for (File file : files.listFiles()) {
                fileList.addAll(listFiles(file));
            }
        } else {
            fileList.add(files);
        }
        return fileList;
    }
    @Test
    public void testListFiles(){
        File file = new File("D:\\FirefoxDownload\\maven_hadoop_template-master\\src");
        Long beginTime = System.currentTimeMillis();
        List<File> files = listFiles(file);
        System.out.println("一共" + files.size() + "个文件");
        files.forEach(f ->{
            System.out.println("文件名：" + f.getName());
            System.out.println("文件路径：" + f.getPath());
        });
        Long endTime = System.currentTimeMillis();
        System.out.println("扫描用时：" + (endTime - beginTime) + "毫秒");
    }

    /**********************************************************************************************/

    /**
     * 获取从1加到N的和
     * @param n
     * @return
     */
    public Integer addSum(Integer n){
        if (n == 1){
            return n;
        }else {
            return n + addSum(n - 1);
        }
    }
    @Test
    public void testSum(){
        System.out.println(addSum(10));
    }

    /**********************************************************************************************/

    /**
     * 计算阶乘
     * @param n
     * @return
     */
    public Integer factorialSum(Integer n){
        if (n == 1){
            return n;
        }else {
            return n * factorialSum(n - 1);
        }
    }
    @Test
    public void testFactorialSum(){
        System.out.println(factorialSum(5));
    }

    /**********************************************************************************************/

    /**
     * 计算斐波那契数列
     * @param n
     * @return
     */
    public Integer fibonacci(Integer n){
        if (n == 1 || n == 2){
            return 1;
        }else{
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    @Test
    public void testFibonacci(){
        System.out.println(fibonacci(20));
    }

}
