package com.javacoding.socket.demo;

public class SocketEntity {
	//连接名称
	private String name;
	//ip
	private String ip;
	//端口
	private int port;
	//是否保持连接
	private boolean isKeepConn;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isKeepConn() {
		return isKeepConn;
	}
	public void setKeepConn(boolean isKeepConn) {
		this.isKeepConn = isKeepConn;
	}
	private SocketEntity(String name, String ip, int port, boolean isKeepConn) {
		super();
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.isKeepConn = isKeepConn;
	}
	private SocketEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SocketEntity [name=" + name + ", ip=" + ip + ", port=" + port
				+ ", isKeepConn=" + isKeepConn + "]";
	}
}
