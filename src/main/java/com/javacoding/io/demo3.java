package com.javacoding.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

/**
 * java 字符流
 * @author JM
 */
public class demo3 {
	/**
	 * BufferedReader 的用法
	 * @throws Exception 
	 */
	@Test
	public void bufferedReader() throws Exception{
		//设置编码格式
		InputStreamReader inr = new InputStreamReader(new FileInputStream("D:\\a.txt"),"GB2312");
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("D:\\b.txt"), "GB2312");
		
		BufferedReader reader = new BufferedReader(inr);
		BufferedWriter writer = new BufferedWriter(out);
		String line = null;
		while((line = reader.readLine())!=null){
			writer.write(line);
		}
		reader.close();
		writer.close();
	}
}
