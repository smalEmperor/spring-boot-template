package com.template.algorithm;


import java.util.Arrays;

/**
 * 二分查找
 *
 * @author 14328
 * @date 2022/02/14
 */
public class TwoSearch {

    // 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            int midValue = nums[mid];
            if (midValue == target) {
                return mid;
            }

            if (midValue > target) {
                right = mid - 1;
            }

            if (midValue < target) {
                left = mid + 1;
            }
        }

        return -1;
    }

    public static int firstBadVersion(int n) {
        int a = 1;
        int b = n;
        while (a < b) {
            int mid = a + ((b - a) >> 1);
            if (isBadVersion(mid)) {
                b = mid;
            } else {
                a = mid + 1;
            }
        }
        return a;
    }

    public static boolean isBadVersion (int n) {
        if (n >= 10) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length -1 ;
        int[] array = new int[right + 1];
        int index = array.length -1;
        while (left <= right) {
            int leftValue = nums[left] * nums[left];
            int rightValue = nums[right] * nums[right];
            if (leftValue > rightValue) {
                array[index] = leftValue;
                left ++;
            } else {
                array[index] = rightValue;
                right --;
            }
            index --;
        }
        return array;
    }

    public static void main(String[] args) {

        int[] nums = {-1,0,3,5,9,12};
        int target = 9;

        //System.out.println(search(nums, target));

        // System.out.println("........." + firstBadVersion(20));

        int[] arrays = {-4,-1,0,3,10};

        System.out.println("........." + Arrays.toString(sortedSquares(arrays)));
    }
}
