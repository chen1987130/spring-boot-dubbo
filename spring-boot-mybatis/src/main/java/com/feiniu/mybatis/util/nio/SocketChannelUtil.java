package com.feiniu.mybatis.util.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

public class SocketChannelUtil {

	private int size = 1024;
	private ByteBuffer byteBuffer;
	private SocketChannel socketChannel;

	public void connectServer() throws IOException {
		socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8998));
		byteBuffer = ByteBuffer.allocate(size);
		byteBuffer.order(ByteOrder.BIG_ENDIAN);
		send("hello world!".getBytes("utf-8"));
		receive();
	}

	private void receive() throws IOException {
		while (true) {
			int count;
			byteBuffer.clear();
			while ((count = socketChannel.read(byteBuffer)) > 0) {
				byteBuffer.flip();
				while (byteBuffer.hasRemaining()) {
					System.out.print((char) byteBuffer.get());
				}
				byteBuffer.clear();
			}
		}
	}

	private void send(byte[] data) throws IOException {
		byteBuffer.clear();
		byteBuffer.put(data);
		byteBuffer.flip();
		socketChannel.write(byteBuffer);
	}

	public static void main(String[] args) throws IOException {
		new SocketChannelUtil().connectServer();
	}
}
