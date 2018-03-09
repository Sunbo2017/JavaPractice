package com.sunbo.test.leetcode;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sunboyan
 * Date 2018/3/9
 * Description 给定两个非空linkedList，将每个list中所有数字转换为一个数字，求和
 */
public class ListNumbersTest {

    public ArrayList<Character> addTwoNumbers(List l1, List l2) {
        ArrayList<Character> targetList = Lists.newArrayList();
        Object[] arr1 = l1.toArray();
        Object[] arr2 = l2.toArray();
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i<arr1.length; i++){
            sum1 += (Integer)arr1[i] * Math.pow(10,i);
        }
        for (int i = 0; i<arr2.length; i++){
            sum2 += (Integer)arr2[i] * Math.pow(10,i);
        }
        int sumAll = sum1 + sum2;
        char[] chars = String.valueOf(sumAll).toCharArray();
        for (char i : chars){
            targetList.add(i);
        }
        return targetList;
    }
    @Test
    public void testListNumbers(){
        List<Integer> l1 = Stream.of("1","2","3").map(p ->Integer.parseInt(p)).collect(Collectors.toList());
        List<Integer> l2 = Stream.of("4","5","6").map(p ->Integer.parseInt(p)).collect(Collectors.toList());
        List<Character> targetList = addTwoNumbers(l1,l2);
        targetList.forEach(i -> System.out.println(i));
    }
}