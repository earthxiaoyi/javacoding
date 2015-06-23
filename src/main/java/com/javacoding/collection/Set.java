package com.javacoding.collection;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

public class Set {
	
	@Test
	public void demo1(){
		HashSet<String> set = new HashSet<String>();
		for(int i=0;i<10;i++){
			set.add("myset:");
		}
		//遍历
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()){
			String next = iter.next();
			System.out.println(next);
		}
	}
}
