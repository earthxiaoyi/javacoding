package com.javacoding.thread.callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {
	public static void main(String[] args) throws Exception {
		System.out.println("mainthread started");
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		Future<Integer> futureOne = service.submit(new SomeOne());
		Future<Integer> futureTwo = service.submit(new SomeTwo());
		Integer oneValue = futureOne.get();
		System.out.println("first:"+oneValue);
		Integer twoValue = futureTwo.get();
		System.out.println("second:"+twoValue);
		System.out.println("mainthread end");
	}
}
