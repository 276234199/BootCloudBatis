package com.casit;

import java.util.Arrays;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年10月31日 下午2:46:53
*/
public class Test9999 {
	
	
	public static int[] plus1(int[] nums) {
		
		int size = nums.length;
		if(size<=0) { return null;}
		
		for(int index = size-1 ; index>=0 ;index--) {
			if(nums[index] < 9) {
				nums[index] = nums[index] + 1;
				return nums;
			}else {
				nums[index] = 0;
			}
			
		}
		//第一位是0，说明全是9
		if(nums[0]==0) {
			nums = new int[size+1];
			nums[0]=1;
			for(int i = 1;i< nums.length;i++) {
				nums[i] = 0;
			}
		}
		
		return nums;
	}
	
	public static int[] jisuan(int[] a) {
		int m = a.length - 1;
		while (m >= 0 && a[m] == 9)
			m -= 1;
		if (m < 0) {
			// 说明数组无脑是9
			int[] b = new int[a.length + 1];
			b[0] = 1;
			for (int i = 1; i < b.length; i++) 
				b[i] = 0;
			return b;
		}
		a[m] += 1;
		for (int i = m + 1; i < a.length; i++)
			a[i] = 0;
		return a;
	}
	
	public static void main(String[] args) {
		int[]  nums1 = {9,9,9,9};
		System.out.println(Arrays.toString(plus1(nums1)));
		int[]  nums2 = {3,9,9,9};
		System.out.println(Arrays.toString(plus1(nums2)));
		int[]  nums3 = {3,9,9,1};
		System.out.println(Arrays.toString(plus1(nums3)));
		int[]  nums4 = {0,9,9,1};
		System.out.println(Arrays.toString(plus1(nums4)));
		int[]  nums5 = {0,9,9,9};
		System.out.println(Arrays.toString(plus1(nums5)));
		
		System.out.println(Arrays.toString(jisuan(nums1)));
		System.out.println(Arrays.toString(jisuan(nums2)));
		System.out.println(Arrays.toString(jisuan(nums3)));
		System.out.println(Arrays.toString(jisuan(nums4)));
		System.out.println(Arrays.toString(jisuan(nums5)));
	}

}
