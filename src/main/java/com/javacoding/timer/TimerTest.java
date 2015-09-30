package com.javacoding.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new ShowSchedule(), 1000,1000);
	}
}

class ShowSchedule extends TimerTask{

	@Override
	public void run() {
		System.out.println("hehehe");
	}
	
}
