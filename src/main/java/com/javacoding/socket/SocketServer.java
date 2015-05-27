package com.javacoding.socket;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket 编程 服务端
 * 
 * @author JM
 *
 */
public class SocketServer {

	public static void main(String[] args) throws Exception {
		// 端口
		int port = 8952;
		// 定义个ServerSocket监听在8952端口上面
		ServerSocket server = new ServerSocket(port);
		// 尝试接受其他socket端的连接请求,server的accept方法是阻塞的
		// 接受多个客户端传递的信息
		while (true) {
			//在循环里面 一直阻塞等待客户端连接(一直阻塞等待)
			Socket socket = server.accept();
			StringBuilder sb = new StringBuilder();
			char[] chars = new char[1024];
			int len = 0;
			String temp;
			int index;
			// 获取客户端传递过来的数据
			InputStreamReader in = new InputStreamReader(
					socket.getInputStream());
			while ((len = in.read(chars)) != -1) {
				temp = new String(chars, 0, len);
				if ((index = temp.indexOf("eof")) != -1) {
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(temp);
			}
			System.out.println("from client:" + sb);

			// 读完后,客户端再回写一条数据
			OutputStreamWriter out = new OutputStreamWriter(
					socket.getOutputStream());
			out.write("Hello client!");
			out.write("eof");
			out.flush();
			// 关闭连接
			out.close();
			in.close();
			socket.close();
			//server.close();
		}

	}

}
