package com.javacoding.thread.callable;

import java.util.concurrent.Callable;

public class SomeOne implements Callable<Integer>{

	public Integer call() throws Exception {
		int i = 0;
		i++;
		while(true){
			if(i>100){
				break;
			}
			System.out.println("thread SomeOne "+i);
			i++;
		}
		return i+10;
	}

}
