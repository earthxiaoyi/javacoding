package com.javacoding.thread.callable;

import java.util.concurrent.Callable;

public class SomeTwo implements Callable<Integer>{

	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		i++;
		while(true){
			if(i>100){
				break;
			}
			System.out.println("thread SomeTwo "+i);
			i++;
		}
		return i+1;
	}

}
