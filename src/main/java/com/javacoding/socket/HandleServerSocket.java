package com.javacoding.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 处理客户端请求类
 * @author JM
 *
 */
public class HandleServerSocket implements Runnable{

	private Socket socket;

	public void run() {
		// TODO Auto-generated method stub
		try {
			this.handleSocket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理客户端请求
	 */
	private void handleSocket() throws Exception {
		StringBuilder sb = new StringBuilder();
		String temp;
		int index;
		// 获取客户端传递过来的数据
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
		while ((temp = reader.readLine()) != null) {
			if ((index = temp.indexOf("eof")) != -1) {
				sb.append(temp.substring(0, index));
				break;
			}
			sb.append(temp);
		}
		System.out.println("from client:" + sb);

		// 读完后,客户端再回写一条数据
		OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
		writer.write("你好,客户端!");
		writer.write("eof\n");
		writer.flush();
		// 关闭连接
		writer.close();
		reader.close();
		socket.close();
	}

	
	
	public HandleServerSocket(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
