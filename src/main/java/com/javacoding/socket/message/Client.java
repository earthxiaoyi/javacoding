package com.javacoding.socket.message;

import java.text.NumberFormat;

public class Client {
	public static void main(String[] args) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMinimumIntegerDigits(8);
		numberFormat.setGroupingUsed(false);
		byte [] num = numberFormat.format(7).getBytes();
		System.out.println(numberFormat.format(7));
		for(int i=0;i<num.length;i++){
			System.out.print(num[i]);
		}
	}
}
