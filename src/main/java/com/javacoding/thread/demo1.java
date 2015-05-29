package com.javacoding.thread;


public class demo1 extends Thread{

	@Override
	public void run() {
		System.out.println("调用那个了");
	}
	
	public demo1(){
		//start();
	}
	
	public static void main(String[] args) {
		demo1 demo1 = new demo1();
		ThreadPool pool = new ThreadPool(3);
		pool.excute(demo1);
	}
}
