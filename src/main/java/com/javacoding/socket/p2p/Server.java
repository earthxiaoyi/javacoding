package com.javacoding.socket.p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.javacoding.socket.HandleServerSocket;

public class Server implements Runnable{

	public void run() {
		// 端口
		int port = 8952;
		// 定义个ServerSocket监听在8952端口上面
		ServerSocket server=null;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 尝试接受其他socket端的连接请求,server的accept方法是阻塞的
		// 接受多个客户端传递的信息
		while (true) {
			//在循环里面 一直阻塞等待客户端连接(一直阻塞等待)
			Socket socket = null;
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//实例化线程池,处理客户端请求
			ExecutorService service = Executors.newFixedThreadPool(10);
			service.execute(new HandleServerSocket(socket));
		}
	}

}
