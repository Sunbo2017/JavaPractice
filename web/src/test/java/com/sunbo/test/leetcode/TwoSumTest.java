package com.sunbo.test.leetcode;

import org.junit.Test;

/**
 * @author sunboyan
 * Date 2018/3/9
 * Description 给定一个数字不重复数组和计算结果值，要求找出数组中两个数字之和为给定结果值的数字下标，并返回下标组成的新数组
 */
public class TwoSumTest {
    public int[] twoSum(int[] nums,int target){
        int[] targetArr = new int[2];
        ok:
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] == target){
                    targetArr[0] = i;
                    targetArr[1] = j;
                    break ok;
                }
            }
        }
        return targetArr;
    }
    @Test
    public void testTwoSun(){
        int[] array = {1,3,5,7,9,11,13,15};
        int[] targetArray = twoSum(array,22);
        for (int i : targetArray){
            System.out.println(i);
        }
    }
}
