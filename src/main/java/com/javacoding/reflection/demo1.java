package com.javacoding.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class demo1 {
	/**
	 * 反射：1.加载类，获得类的字节码。2.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Class<?> forName = Class.forName("com.javacoding.reflection.UserEntity");
		Constructor<?> constructor = forName.getConstructor(null);
		String name = constructor.getName();
		//UserEntity userEntity = (UserEntity)forName.newInstance();
		constructor.newInstance(null);
		forName.newInstance();
		
	}
	
	//反射构造函数
	@Test
	public void demo2() throws Exception{
		Class<?> forName = Class.forName("com.javacoding.reflection.UserEntity");
		Constructor<?> con = forName.getConstructor(null);
		con.newInstance(null);
	}
	
	//反射对象的方法
	@Test
	public void demo3() throws Exception{
		Class<?> forName = Class.forName("com.javacoding.reflection.UserEntity");
		Method method = forName.getMethod("setId", String.class);
		method.invoke(forName.newInstance(), "ssss");
	}
	//反射字段
	@Test
	public void demo4() throws Exception{
		Class<?> clz = Class.forName("com.javacoding.reflection.UserEntity");
		Field field = clz.getDeclaredField("id");
		field.setAccessible(true);
		System.out.println(field.get(clz.newInstance()));
	}
}
