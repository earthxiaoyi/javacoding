package com.javacoding.thread.demo3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class OptimisticLocking {
	
	private AtomicInteger version = new AtomicInteger(0);
	private int max=10;
	public int getVersion(){
		return this.version.get();
	}
	
	public int update(int updateId){
		int current = version.get();
		if(version.compareAndSet(current, updateId)){
			if(max>0){
				max--;
				System.out.println(updateId+":is ok! 当前库存:"+max);
			}
		}
		return updateId;
	}
	
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(200);
		final OptimisticLocking optimistic = new OptimisticLocking();
		final AtomicInteger updateId = new AtomicInteger(1);
		for(int i=0;i<1000;i++){
			pool.submit(new Runnable() {
				public void run() {
					int incrementAndGet = updateId.incrementAndGet();
					optimistic.update(incrementAndGet);
				}
			});
		}
		pool.shutdown();
	}
}
