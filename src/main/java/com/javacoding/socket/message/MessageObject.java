package com.javacoding.socket.message;

import java.text.NumberFormat;

/**
 * 报文对象 
 * 
 * 我们今天要谈的报文主要友以下几个部分组成：
 * 
 * 3位同步校验位+8位报文长度+报文头+报文体+32位MD5校验位
 * 
 * 基本格式如下：
 * 
 * 0X110X120X1300000232<?xml version="1.0" encoding="GBK"?><ROOT>
 * <Code>0204</Code><Date>20141223</Date><No>141223010008152</No>
 * <Code>17010001</Code><Name>张三</Name></ROOT>B251AB76B11114DB176023A0AA27A524
 * 
 * @author JM
 */
public class MessageObject {
	private String bodyLen;	//8位长度
	private String body;	//内容
	private String syncStr;	//同步字符串
	private String md5;		//md5校验
	public String getBodyLen() {
		return bodyLen;
	}
	public void setBodyLen(String bodyLen) {
		this.bodyLen = bodyLen;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSyncStr() {
		return syncStr;
	}
	public void setSyncStr(String syncStr) {
		this.syncStr = syncStr;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	public byte[] getByteStream() throws Exception{
		//把数据编码成gbk
		byte[] bodyBytes = this.body.getBytes("gbk");
		//数据长度
		int bodyLenth = bodyBytes.length;
		
		//消息传递长度
		int msgObjectLen = 3+bodyLenth+8+32;//传递消息报文
		
		//存放数据的字节数组
		byte[] soc = new byte[msgObjectLen];
		
		//添加数据
		int index = 0;
		soc[0]=0x11;
		soc[1]=0x12;
		soc[2]=0x13;
		index+=3;
		
		//添加8位报文长度
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMinimumIntegerDigits(8);
		numberFormat.setGroupingUsed(false);
		byte[] num = numberFormat.format(msgObjectLen).getBytes();
		
		for(int i=0;i<8;i++){
			soc[index++]=num[i];
		}
		
		//添加body内容
		for(int i=0;i<bodyLenth;i++){
			soc[index++]=bodyBytes[i];
		}
		
		//添加md5校验码
		byte[] md5Bytes = this.md5.getBytes();
		for(int i=0;i<num.length;i++){
			soc[index++]=md5Bytes[i];
		}
		
		return soc;
	}
	
	//字节转报文
	
	/**
	 * 字节数组转化成string
	 * @param bytes 字节数组
	 * @param start 开始位置
	 * @param end	结束位置
	 * @return	转换后的字符串
	 */
	public String bytesToString(byte[] bytes,int start,int end){
		String str = "";
		if(bytes.length<end-start){
			return str;
		}
		byte[] bs = new byte[end-start];
		for(int i=0;i<end-start;i++){
			//从开始位置转换
			bs[i]=bytes[start++];
		}
		str = new String(bs);
		return str;
	}
	
	
}
