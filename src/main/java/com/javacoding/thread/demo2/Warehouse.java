package com.javacoding.thread.demo2;

import java.util.LinkedList;

public class Warehouse {
	
	private final int max_size=100;
	
	/**
	 * 存放商品,容量是100
	 */
	private LinkedList list = new LinkedList();
	
	/**
	 * 生产商品
	 */
	public void producer(int num){
		synchronized(list){
			
			while(list.size()+num>max_size){
				System.out.println("库存已满,当前库存为:"+list.size());
				try {
					list.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int i=0;i<num;i++){
				Object o = new Object();
				list.add(o);
			}
			System.out.println("已经生产:"+num+" 个产品,当前库存为:"+list.size());
			//生产完,通知消费者消费
			list.notifyAll();
		}
	}
	/**
	 * 消费者商品
	 */
	public void consumer(int num){
		synchronized (list) {
			while(list.size()<num){
				System.out.println("库存不足 不能消费");
				try {
					list.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int i=0;i<num;i++){
				list.remove();
			}
			System.out.println("已经消费:"+num+" 个产品,当前库存为:"+list.size());
			//消费完,通知生产者生产
			list.notifyAll();
		}
	}
	
}
