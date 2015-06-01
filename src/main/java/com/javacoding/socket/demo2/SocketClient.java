package com.javacoding.socket.demo2;

import java.io.BufferedReader;
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
		
		OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
		writer.write("hello server");
		writer.write("eof\n");
		//刷新缓存中的数据
		writer.flush();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
		String temp;
		while((temp = reader.readLine())!=null){
			System.out.println(temp);
		}
	}
}
