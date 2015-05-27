package com.javacoding.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.junit.Test;

import com.javacoding.UserEntity;

public class demo1 {
	public static void main(String[] args) throws Exception {
		
	}
	
	/**
	 * FileInputStream 读取文件
	 * @throws Exception
	 */
	@Test
	public void fileinput() throws Exception{
		File filein = new File("G:\\java教学视频\\老徐将集合和io流\\day18\\01-基本数据类型的包装类.avi");
		File fileout = new File("D:\\a.avi");
		InputStream io = new FileInputStream(filein);
		OutputStream out = new FileOutputStream(fileout);
		byte[] b = new byte[1024];
		int len=0;
		long begintime = System.currentTimeMillis();
		//如果读取到-1 就是读取到文件的末尾
		while((len=io.read(b, 0, b.length))!=-1){
			out.write(b, 0, len);
		}
		System.out.println("耗时:"+(System.currentTimeMillis()-begintime));
		io.close();
		out.close();
	}
	/**
	 * ByteArrayInputStream 用法
	 * 
	 * 一般是从缓冲区中复制数据
	 * 
	 * @throws Exception 
	 */
	@Test
	public void byteArrayInputStream() throws Exception{
		String buf = "abcdefghijklmnopqrstuvwxyz";
		
		ByteArrayInputStream in = new ByteArrayInputStream(buf.getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		transf(in,out);
		//获取数据
		byte[] result = out.toByteArray();
		System.out.println("buf:"+new String(buf.getBytes()));
		System.out.println("result:"+new String(result));
	}
	
	
	//将输入流中的数据读取到输出流中,通过tobytearray方法获取数据
	public void transf(InputStream in,OutputStream out) throws Exception{
		int ch=0;
		while((ch=in.read())!=-1){
			char upperCase = Character.toUpperCase((char)ch);
			out.write(upperCase);
		}
		
	}
	
	/**
	 * BufferedInputStream 用法
	 * 1.第一次缓存数据只缓存8m,减少了和硬盘交互的次数  
	 * 2.其他用法与inputream 差不多.
	 * @throws Exception 
	 */
	@Test
	public void bufferedInputStream() throws Exception{
		InputStream in = new FileInputStream(new File("G:\\java教学视频\\老徐将集合和io流\\day18\\01-基本数据类型的包装类.avi"));
		OutputStream out = new FileOutputStream("D:\\a.avi");
		BufferedInputStream bis = new BufferedInputStream(in);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		int ch;
		byte[] b = new byte[1024];
		long begintime = System.currentTimeMillis();
		while((ch = bis.read(b))!=-1){
			bos.write(b);
		}
		//刷新缓冲区
		bos.flush();
		System.out.println("耗时:"+(System.currentTimeMillis()-begintime));
		in.close();
		out.close();
		bis.close();
		bos.close();
	}
	
	/**
	 * ObjectInputStream 用法
	 * 序列化、反序列化 对象流
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void objectInputStream() throws Exception{
		//实例化一个类
		UserEntity user = new UserEntity();
		user.setId("1");
		user.setName("小李子");
		user.setScope("100");
		user.setSex("1");
		
		//使用文件输出流构造一个对象输出流
		ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("D:\\user.dat"));
		objOut.writeObject(user);
		
		//使用文件输入流,读取一个对象
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\user.dat"));
		Object readObject = in.readObject();
		
		System.out.println(readObject.toString());
		
	}
	/**
	 * java 管道流用法  主要用于线程间的通信
	 * 等会去看下管道流的实现细节
	 * @throws Exception
	 */
	@Test
	public void pipedInputStream() throws Exception{
		Receiver receiver = new Receiver();
		Sender sender = new Sender();
		PipedInputStream in = receiver.getIn();
		PipedOutputStream out = sender.getOut();
		//连接
		out.connect(in);
		//开启线程
		receiver.start();
		sender.start();
	}
	/**
	 * java io mark方法使用
	 * @throws Exception
	 */
	@Test
	public void stringBufferStream() throws Exception{
		FileInputStream fileIn = new FileInputStream("D:\\a.txt");
		FileOutputStream fileOut = new FileOutputStream("D:\\b.txt");
		for(int i=0;i<5;i++){
			int read = fileIn.read();
			System.out.print((char)read);
		}
		//fileIn.mark(1);
		System.out.println();
		for(int i=0;i<5;i++){
			int read = fileIn.read();
			System.out.print((char)read);
		}
		fileIn.close();
	}
	
	
	class Receiver extends Thread{
		private PipedInputStream in = new PipedInputStream();
		public PipedInputStream getIn() {
			return in;
		}

		@Override
		public void run() {
			byte[] b = new byte[1024];
			try {
				int ch = in.read(b);
				System.out.println("receive message:"+new String(b,0,ch));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	class Sender extends Thread{
		private PipedOutputStream out = new PipedOutputStream();
		String send = "hello,this is message";
		public PipedOutputStream getOut() {
			return out;
		}

		@Override
		public void run() {
			try {
				out.write(send.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
