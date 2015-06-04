package com.javacoding.thread.demo2;
/**
 * 生产者 消费者模式
 * @author JM
 */
public class Client {
	public static void main(String[] args) {
		Warehouse w = new Warehouse();
		
		//消费者
		Consumer c1 = new Consumer(w,10);
		Consumer c2 = new Consumer(w,10);
		Consumer c3 = new Consumer(w,10);
		new Thread(c1).start();
		new Thread(c2).start();
		new Thread(c3).start();
		//生产者
		Producer p1 = new Producer(w,10);
		Producer p2 = new Producer(w,10);
		Producer p3 = new Producer(w,10);
		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(p3).start();
	}
}
