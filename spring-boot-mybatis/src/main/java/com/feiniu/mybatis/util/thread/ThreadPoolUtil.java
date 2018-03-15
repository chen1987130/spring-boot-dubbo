package com.feiniu.mybatis.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {

	public static int index = 1;

	public static void main(String[] args) {
		ExecutorService Pool = Executors.newScheduledThreadPool(5);
		for (int i = 0; i < 100; i++) {
			Pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(index++);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

}
