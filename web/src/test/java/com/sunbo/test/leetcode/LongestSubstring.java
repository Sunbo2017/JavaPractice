package com.sunbo.test.leetcode;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunboyan
 *         Date 2018/3/15
 *         Description 给定某个字符串，要求截取最长的无重复子串，返回子串长度
 */
public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (StringUtils.isEmpty(s)){
            return 0;
        }
        if (1 == s.length()){
            return 1;
        }
        List<String> list = Lists.newArrayList();
        String s1;
        char[] chars;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length()+1; j++) {
                s1 = s.substring(i, j);
                chars = s1.toCharArray();
                Boolean b = Boolean.TRUE;
                for (int n = 0; n < chars.length; n++) {
                    for (int m = n + 1; m < chars.length; m++){
                        if (chars[n] == chars[m]) {
                            b = Boolean.FALSE;
                        }
                    }
                }
                if (b) {
                    list.add(s1);
                }
            }
        }
        List<String> list1 = list.stream().distinct().collect(Collectors.toList());
        list1.forEach(p -> System.out.println(p));
        Integer max = list1.stream().mapToInt(p -> p.length()).max().getAsInt();
        return max;
    }

    @Test
    public void testLength() {
        String s = "gwzzzlykevhxxgeqmahltovorbiivcfczgdatbkaytxwzdondvazjwpczxkw";
        int length = lengthOfLongestSubstring(s);
        System.out.println(length);
    }
}
