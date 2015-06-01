package com.javacoding.thread;

import java.util.LinkedList;

/**
 * 线程池类,模拟 java5中自带的线程池
 * 
 * @author JM
 */
public class ThreadPool {
	/* 线程数量 */
	private int nTheadsNumber;
	// 任务列表
	private final LinkedList taskList;

	private final PoolWork[] works;

	public ThreadPool(int threadNumber) {

		this.nTheadsNumber = threadNumber;
		taskList = new LinkedList();
		works = new PoolWork[nTheadsNumber];

		for (int i = 0; i < threadNumber; i++) {
			works[i] = new PoolWork();
			works[i].start();
		}

	}

	public void excute(Runnable r) {
		synchronized (taskList) {
			taskList.addLast(r);
			taskList.notify();
		}
	}

	/**
	 * 工作线程
	 * 
	 * @author JM
	 */
	private class PoolWork extends Thread {
		@Override
		public void run() {
			Runnable r;
			while (true) {
				synchronized (taskList) {
					while (taskList.isEmpty()) {
						try {
							taskList.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					r = (Runnable) taskList.removeFirst();
				}
				//调用线程的run方法
				try {
					r.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public PoolWork() {
			// start();
		}
	}

}
