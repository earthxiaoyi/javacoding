package com.javacoding.socket.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client implements Runnable{
	
	public void run() {
		//地址
		String host = "127.0.0.1";
		//端口
		int port = 8952;
		//与服务端建立连接
		try {
			while(true){
				Socket client = new Socket(host,port);
				Thread.sleep(1000);
				OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream(),"GBK");
				writer.write("你好,服务端!");
				writer.write("eof\n");
				//刷新缓存中的数据
				writer.flush();
				//写完后,再接受服务端的数据
				BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
				//设置10秒超时
				client.setSoTimeout(1000*10);
				String temp;
				int index;
				StringBuilder sb = new StringBuilder();
				while((temp = reader.readLine())!=null){
					if((index = temp.indexOf("eof"))==-1){
						sb.append(temp.substring(0,index));
					}
					sb.append(temp);
				}
				//打印数据
				System.out.println("from server:"+sb.toString());
				//reader.close();
				//writer.close();
				client.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
