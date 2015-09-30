package com.javacoding.socket.p2p;

public class P2PMain {
	public static void main(String[] args) {
		Thread server = new Thread(new Server());
		Thread client = new Thread(new Client());
		client.start();
		server.start();
	}
}
