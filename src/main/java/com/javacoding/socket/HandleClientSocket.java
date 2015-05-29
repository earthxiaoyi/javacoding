package com.javacoding.socket;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HandleClientSocket {

	private Socket client;

	public HandleClientSocket(Socket client) {
		this.client = client;
	}
	//处理  客户端 接收/发送 消息
	private void handleClient() throws Exception {
		// 开始写数据
		OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
		writer.write("Hello,this is client b!");
		writer.write("eof");
		// 刷新缓存中的数据
		writer.flush();
		// 写完后,再接受服务端的数据
		InputStreamReader reader = new InputStreamReader(client.getInputStream());
		char[] chars = new char[1024];
		int len = 0;
		String temp;
		int index;
		StringBuilder sb = new StringBuilder();
		
		while ((len = reader.read(chars)) != -1) {
			temp = new String(chars, 0, len);
			if ((index = temp.indexOf("eof")) == -1) {
				sb.append(temp.substring(0, index));
			}
			sb.append(temp);
		}
		
		// 打印数据
		System.out.println("from server:" + sb.toString());
		reader.close();
		writer.close();
		client.close();
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}
}
