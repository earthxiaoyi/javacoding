package com.javacoding.io.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileDemo1 {
	
	private static final int BSIZE = 100;
	
	public static void main(String[] args) throws Exception {
		File target = new File("D:\\target.txt");
		File cpfile = new File("D:\\cpfile.txt");
		RandomAccessFile rf = new RandomAccessFile(target,"r");
		
		RandomAccessFile cp = new RandomAccessFile(cpfile,"rw");
		
		FileChannel fc = rf.getChannel();
		FileChannel fcp = cp.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		int len=0;
		int sum = 0;
		while((len = fc.read(buffer))!=-1){
			//System.out.println("还有多少没有读完:"+(fc.size()-(sum = filepos+sum)));
			buffer.flip();
			fcp.write(buffer);
			buffer.clear();
			
		}
		fc.close();
		fcp.close();
	}
	
}
