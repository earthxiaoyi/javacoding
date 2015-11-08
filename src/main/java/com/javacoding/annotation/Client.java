package com.javacoding.annotation;

import java.lang.reflect.Field;

public class Client {
	public static void main(String[] args) {
		BookEntity book = new BookEntity();
		Class<? extends BookEntity> clz = book.getClass();
		Field[] fields = clz.getDeclaredFields();
		BookAnnotation bookAnnotation = clz.getAnnotation(BookAnnotation.class);
		/*for(int i=0;i<fields.length;i++){
			fields[i].setAccessible(true);
			//获得注解
			BookAnnotation annotation = fields[i].getAnnotation(BookAnnotation.class);
			String name = annotation.name();
			System.out.println(name);
		}*/
	}
}

