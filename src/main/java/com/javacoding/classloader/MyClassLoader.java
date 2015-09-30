package com.javacoding.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{
	
	//需要加载的类路径
	private String classDir;
	private String name;
	public MyClassLoader(String classDir,String name){
		this.classDir = classDir;
		this.name = name;
	}
	
	/**
	 * 重写findClass类
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] clz = null;
		try {
			
			clz = loadData(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.defineClass("com.javacoding.classloader."+name,clz, 0, clz.length);
	}

	/**
	 * 加载类文件
	 * @param in
	 * @param bos
	 * @return
	 * @throws FileNotFoundException 
	 */
	private byte[] loadData(String name){
		try {
			String classPathFile = classDir + "\\"+ name + ".class";
			FileInputStream in = new FileInputStream(classPathFile);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int b = 0;
			while((b = in.read())!=-1){
				bos.write(b);
			}
			return bos.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}


	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String clzPath= "D:\\space\\javacoding\\src\\main\\java\\com\\javacoding\\classloader";
		MyClassLoader loader = new MyClassLoader(clzPath,"MyClassLoadObject");
		Class<?> clz = loader.loadClass("MyClassLoadObject");
		Object o = clz.newInstance();
		System.out.println(1);
	}
}	
