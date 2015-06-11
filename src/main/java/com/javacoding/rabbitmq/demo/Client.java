package com.javacoding.rabbitmq.demo;

import java.util.HashMap;

public class Client {
	public static void main(String[] args) throws Exception {
		QueueConsumer consumer = new QueueConsumer("testQueue");
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();

		/*Producer producer = new Producer("queue");

		for (int i = 0; i < 100000; i++) {
			HashMap message = new HashMap();
			message.put("message number", i);
			producer.sendMessage(message);
			//System.out.println("Message Number " + i + " sent.");
		}*/
	}
}
