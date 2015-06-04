package com.javacoding.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 当一个对象发生改变的时候  serialVersionUID 这个也会发生相应的改变,
 * 一个对象被序列化写到文件中,当反序列化来 serialVersionUID 不相同会报错.
 * @author JM
 *
 */
public class SerializationTest {
	public static void main(String[] args) throws Exception {
		FileOutputStream file = new FileOutputStream("D:\\a.txt");
		ObjectOutputStream objOut = new ObjectOutputStream(file);
		Student stu = new Student();
		stu.setId(1);
		stu.setName("李强");
		stu.setSex("1");
		objOut.writeObject(stu);
		objOut.close();
		
		//睡2秒
		Thread.sleep(2000);
		
		//读取
		FileInputStream fileIn = new FileInputStream("D:\\a.txt");
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		Student readObject = (Student)objIn.readObject();
		System.out.println(readObject);
		fileIn.close();
		objIn.close();
	}
}
