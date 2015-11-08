package com.javacoding.io.protocol;

public class MsgBody {
	
	private MsgHeader header;
	
	private Object msg;

	public MsgHeader getHeader() {
		return header;
	}

	public void setHeader(MsgHeader header) {
		this.header = header;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	private MsgBody(MsgHeader header, Object msg) {
		super();
		this.header = header;
		this.msg = msg;
	}

	private MsgBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MsgBody [header=" + header + ", msg=" + msg + "]";
	}
}
