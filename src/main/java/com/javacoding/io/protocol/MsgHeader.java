package com.javacoding.io.protocol;

public class MsgHeader {
	private byte type;//消息类型
	private int length;//消息长度
	private int sessionId;//会话id
	private byte priority;//优先级
	
	
	private MsgHeader() {
		super();
	}
	
	private MsgHeader(byte type, int length, int sessionId, byte priority) {
		super();
		this.type = type;
		this.length = length;
		this.sessionId = sessionId;
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "MsgHeader [type=" + type + ", length=" + length
				+ ", sessionId=" + sessionId + ", priority=" + priority + "]";
	}
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public byte getPriority() {
		return priority;
	}
	public void setPriority(byte priority) {
		this.priority = priority;
	}
	
	
	
	
}
