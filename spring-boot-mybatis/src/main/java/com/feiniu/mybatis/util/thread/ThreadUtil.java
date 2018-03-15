package com.feiniu.mybatis.util.thread;

import java.util.Random;

public class ThreadUtil {

	class NewThread implements Runnable {

		private int i;

		public NewThread(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(new Random().nextInt(2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("当前线程" + i);
		}

	}

	public static void main(String[] args) throws InterruptedException {
		// Thread thread = new Thread(() -> {
		// int i = 0;
		//
		// while (!Thread.interrupted()) {
		// System.out.println(i);
		// i++;
		// }
		// });
		// thread.start();
		// Thread.sleep(10);
		// // 一段时间以后
		// thread.interrupt();
		ThreadUtil util = new ThreadUtil();

		Thread t1 = new Thread(util.new NewThread(1));
		Thread t2 = new Thread(util.new NewThread(2));
		Thread t3 = new Thread(util.new NewThread(3));
		t1.start();
		t2.start();
		t3.start();

		System.out.println("开始执行");

		t1.join();
		t2.join();
		t3.join();

		System.out.println("执行完毕");
	}

}
