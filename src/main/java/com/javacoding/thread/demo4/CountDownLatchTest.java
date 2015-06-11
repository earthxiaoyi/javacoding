package com.javacoding.thread.demo4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;

public class CountDownLatchTest {

	@Test
	public void demo1() {
		int count = 10;
		final CountDownLatch latch = new CountDownLatch(count);
		System.out.println("=====begin=====");
		for (int i = 0; i < count; i++) {
			new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName());
					latch.countDown();
				}
			}).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("======end ========");
	}

	/**
	 * countdownlatch 百米赛跑问题
	 */
	@Test
	public void demo2() {
		//开始的倒数锁
		final CountDownLatch begin = new CountDownLatch(1);
		//结束的倒数锁
		final CountDownLatch end = new CountDownLatch(10);
		//十名选手
		final ExecutorService exec = Executors.newFixedThreadPool(10);
		
		for (int index = 0; index < 10; index++) {
			final int NO = index + 1;
			Runnable run = new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					try {
						begin.await();
						Thread.sleep((long)(Math.random()*1000));
						System.out.println("No."+NO+" arrived");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						end.countDown();
					}
				}
			};
			exec.submit(run);
		}
		System.out.println("game begin ");
		//开始比赛
		begin.countDown();
		try {
			end.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("game over~");
	}

}
