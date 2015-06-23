package com.javacoding.algorithm;

public class demo1 {

	public static void main(String[] args) {
		sums(1000);
	}

	public static void sums(int nums) {
		int num1 = nums / 5;
		int num2 = nums / 8;
		int numbers=0;
		for (int i = 0; i < num1; i++) {
			for (int j = 0; j < num2; j++) {
				if((i*5+j*8)==nums){
					numbers++;
					System.out.println(i+"个5 "+j+"个8");
				}
			}
		}
		System.out.println(numbers+" 个组合");
	}
}
