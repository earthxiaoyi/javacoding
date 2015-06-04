package com.javacoding.thread.demo2;

public class Consumer implements Runnable{

	private int num=0;
	private Warehouse s;
	
	public Consumer(Warehouse s,int num){
		this.num=num;
		this.s=s;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.consumer(num);
		}
	}

}
