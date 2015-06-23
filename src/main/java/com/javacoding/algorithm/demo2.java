package com.javacoding.algorithm;

import org.junit.Test;

public class demo2 {
	
	@Test
	public void test2(){
		demo2 demo = new demo2();
		System.out.println(demo.test1());
	}
	
	public int test1(){
		int i=0;
		//语句执行完毕后,再i=i+1操作
		System.out.println("执行前的:"+i++);
		i++;
		// i++; 已经操作完毕了,所以下面打印的是i
		System.out.println("返回前的:"+i);
		return i;
	}
}
