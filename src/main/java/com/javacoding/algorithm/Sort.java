package com.javacoding.algorithm;

import java.util.Random;

import org.junit.Test;

/**
 * 经典排序算法
 * 
 * @author JM
 */
public class Sort {

	/**
	 * 快速排序
	 */
	@Test
	public void demo4() {
		long startTime = System.currentTimeMillis();
		int[] array = genaratorArray(1000 * 1000 * 10);

		quick_sort(array, 0, array.length - 1);

		System.out
				.println("快速排序耗时+" + (System.currentTimeMillis() - startTime));

	}

	public int[] quick_sort(int array[], int l, int r) {
		if (l < r) {
			// Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
			int i = l, j = r, x = array[l];
			while (i < j) {
				while (i < j && array[j] >= x)
					// 从右向左找第一个小于x的数
					j--;
				if (i < j)
					array[i++] = array[j];

				while (i < j && array[i] < x)
					// 从左向右找第一个大于等于x的数
					i++;
				if (i < j)
					array[j--] = array[i];
			}
			array[i] = x;
			quick_sort(array, l, i - 1); // 递归调用
			quick_sort(array, i + 1, r);
		}
		return array;
	}

	/**
	 * 哈希排序
	 */
	@Test
	public void demo3() {
		long startTime = System.currentTimeMillis();

		int[] array = genaratorArray(1000 * 1000 * 10);
		int temp;
		int j = 0;
		for (int increment = array.length / 2; increment > 0; increment /= 2) {
			for (int i = increment; i < array.length; i++) {
				temp = array[i];
				for (j = i; j >= increment; j -= increment) {
					if (temp < array[j - increment]) {
						array[j] = array[j - increment];
					} else {
						break;
					}
				}
				array[j] = temp;
			}
		}

		System.out
				.println("哈希排序耗时+" + (System.currentTimeMillis() - startTime));
	}

	/**
	 * 插入排序
	 */
	@Test
	public void demo2() {
		long startTime = System.currentTimeMillis();

		int[] array = genaratorArray(1000 * 1000);
		System.out
				.println("生成数组耗时+" + (System.currentTimeMillis() - startTime));
		int temp;
		for (int i = 1; i < array.length; i++) {
			for (int j = i; (j > 0) && (array[j] < array[j - 1]); j--) {
				temp = array[j];
				array[j] = array[j - 1];
				array[j - 1] = temp;
			}
		}
		System.out
				.println("插入排序耗时+" + (System.currentTimeMillis() - startTime));
		/*
		 * for(int i=0;i<array.length;i++){ System.out.print(array[i]+","); }
		 */
	}

	/**
	 * 选择排序
	 */
	@Test
	public void demo() {
		long startTime = System.currentTimeMillis();
		int[] array = genaratorArray(1000 * 1000);
		int size = array.length;
		for (int i = 0; i < size; i++) {
			int index = i;
			// 找出最小的元素
			for (int j = i + 1; j < size; j++) {
				// 选择最小的元素
				if (array[j] < array[index]) {
					index = j;
				}
			}
			// 交换
			int temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}
		System.out
				.println("选择排序耗时+" + (System.currentTimeMillis() - startTime));
	}

	/**
	 * 生成一个数组
	 * 
	 * @return
	 */
	public int[] genaratorArray(int size) {
		int[] array = new int[size];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(100000);
		}
		return array;
	}
}
