package com.javacoding.classloader;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class MyClassLoader {
	
	public static void main(String[] args) {
		System.out.println("MyClassLoader的加载器名称:"+MyClassLoader.class.getClassLoader().getClass().getName());
		System.out.println("System类加载器的名称:"+System.class.getClassLoader());
		System.out.println("List类的加载器的名称:"+List.class.getClassLoader());
		
		System.out.println(MyClassLoader.class.getClassLoader().getParent());
	}
}	
