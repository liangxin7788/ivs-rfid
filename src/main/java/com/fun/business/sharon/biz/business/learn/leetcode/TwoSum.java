package com.fun.business.sharon.biz.business.learn.leetcode;

import com.alibaba.fastjson.JSON;

/**
 * 求数组中两数之和等于目标值的数，返回对应下标
 * 如：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 */
public class TwoSum {


    public static void main(String[] args) {
        int[] nums ={2, 4, 7, 9};
        int[] arr = twoSum(nums, 9);
        System.out.println(JSON.toJSONString(arr));
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        for(int i =0; i < nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                int result = nums[i] + nums[j];
                if(target == result){
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return null;
    }

}
