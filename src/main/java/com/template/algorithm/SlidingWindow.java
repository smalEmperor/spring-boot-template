package com.template.algorithm;

import cn.hutool.core.collection.CollUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlidingWindow {

    public static int maxSum1(int[] arry, int k) {
        int size = arry.length;

        if (size < k) {
            return -1;
        }

        //初始化第一个窗口值的总和
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum = maxSum + arry[i];
            System.out.println("初始化："+maxSum);
        }

        int sum = maxSum;
        for (int i = k; i < size; i++) {
            System.out.println("i"+i);
            sum = sum + arry[i] - arry[i - k];
            System.out.println("arry[i]"+arry[i]);
            System.out.println("arry[i - k]"+arry[i - k]);
            System.out.println("sum："+sum);
            maxSum = Math.max(maxSum,sum);
            System.out.println("比较："+maxSum);
        }

        return maxSum;
    }

    static int lengthOfLongestSubstring(String s){
        //获取原字符串的长度
        int len = s.length();
        //维护一个哈希集合的窗口
        Set<Character> windows = new HashSet<>();
        int left=0,right =0;
        int res =0;

        while(right<len){
            char c = s.charAt(right);
            System.out.println("c:"+c);
            //窗口右移
            right++;
            System.out.println("right:"+right);

            //判断是否左边窗口需要缩减，如果已经包含，那就需要缩减

            while(windows.contains(c)){
                System.out.println("s.charAt(left)"+s.charAt(left));
                windows.remove(s.charAt(left));
                System.out.println("windows2:"+CollUtil.join(windows,","));
                left++;
                System.out.println("left:"+left);
            }
            windows.add(c);
            System.out.println("windows1:"+CollUtil.join(windows,","));
            //比较更新答案
            res = Math.max(res,windows.size());
            System.out.println("res:"+res);
            System.out.println("....................................");
        }
        return res;
    }

    public static void main(String[] args) {
        /*List<Integer> integers = Arrays.asList(100, 200, 300, 400);
        int[] a = new int[]{100, 200, 300, 400};
        int k = 2;
        System.out.println(maxSum1(a, k));*/

        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
