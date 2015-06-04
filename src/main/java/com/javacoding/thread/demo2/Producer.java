package com.javacoding.thread.demo2;

public class Producer implements Runnable{
	
	private int num=0;
	private Warehouse warehouse;
	
	public Producer(Warehouse warehouse,int num){
		this.warehouse=warehouse;
		this.num=num;
	}
	
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			warehouse.producer(num);
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
}
