package com.javacoding.socket;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {
	public static void main(String[] args) throws Exception {
		//地址
		String host = "127.0.0.1";
		//端口
		int port = 8952;
		//与服务端建立连接
		Socket client = new Socket(host,port);
		//开始写数据
		OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
		writer.write("Hello,this is client b!");
		writer.write("eof");
		//刷新缓存中的数据
		writer.flush();
		
		//写完后,再接受服务端的数据
		InputStreamReader reader = new InputStreamReader(client.getInputStream());
		char[] chars = new char[1024];
		int len=0;
		String temp;
		int index;
		StringBuilder sb = new StringBuilder();
		while((len = reader.read(chars))!=-1){
			temp = new String(chars,0,len);
			if((index = temp.indexOf("eof"))==-1){
				sb.append(temp.substring(0,index));
			}
			sb.append(temp);
		}
		//打印数据
		System.out.println("from server:"+sb.toString());
		
		writer.close();
		client.close();
	}
}
