package com.feiniu.mybatis.util.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;

public class FileUtil {

	private void t1() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("D:/data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(256);

		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}

	private void t2() throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("D:/data/fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("D:/data/toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();
		fromChannel.transferTo(position, count, toChannel);
	}

	private void t3() throws IOException {
		RandomAccessFile file = new RandomAccessFile("D:/data/file.txt", "rw");
		FileChannel channel = file.getChannel();

		String newData = "New String to write to file..." + System.currentTimeMillis();

		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();

		while (buf.hasRemaining()) {
			channel.write(buf);
		}
		channel.close();
	}

	private void t4() throws IOException {
		Pipe pipe = Pipe.open();
		Pipe.SinkChannel sinkChannel = pipe.sink();
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer writeBuf = ByteBuffer.allocate(48);
		writeBuf.clear();
		writeBuf.put(newData.getBytes());
		writeBuf.flip();

		while (writeBuf.hasRemaining()) {
			sinkChannel.write(writeBuf);
		}
		Pipe.SourceChannel sourceChannel = pipe.source();
		ByteBuffer readBuf = ByteBuffer.allocate(48);

		int bytesRead = sourceChannel.read(readBuf);
		while (bytesRead != -1) {
			readBuf.flip();
			while (readBuf.hasRemaining()) {
				System.out.print((char) readBuf.get());
			}
			readBuf.clear();

			bytesRead = sourceChannel.read(readBuf);
		}

	}

	public static void main(String[] args) throws IOException {
		FileUtil util = new FileUtil();
		util.t4();
	}
}
