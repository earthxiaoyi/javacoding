package com.javacoding.classloader;

public class MyClassLoadObject {

	private String name;
	private int age;

	public void print() {
		System.out.println("my name is :" + this.name + " my age is :"
				+ this.age);
	}
	
	public MyClassLoadObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyClassLoadObject(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	public static void main(String[] args) {
		System.out.println(2);
	}
	
}
