package com.javacoding.io;

public class demo2 {
	
	static int n=1;
	static int sum=0;
	
	/**
	 * 递归 :自己调用自己
	 * @param args
	 */
	public static void main(String[] args) {
		sum();
		System.out.println(sum);
	}
	
	public static void sum(){
		sum+=n;
		n++;
		if(n<1000){
			sum();
		}
	}
	
}
