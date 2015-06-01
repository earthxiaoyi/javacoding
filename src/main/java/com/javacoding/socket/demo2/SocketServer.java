package com.javacoding.socket.demo2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
			final Socket socket = server.accept();
			//实例化线程池,处理客户端请求
			ExecutorService service = Executors.newFixedThreadPool(10);
			service.execute(new HandleServerSocket(socket));
		}

	}

}
