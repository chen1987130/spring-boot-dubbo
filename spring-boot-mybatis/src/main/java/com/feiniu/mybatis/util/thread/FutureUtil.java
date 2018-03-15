package com.feiniu.mybatis.util.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureUtil {
	public static void main(String[] args) {
		ExecutorService Pool = Executors.newScheduledThreadPool(5);

		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				Thread.sleep(new Random().nextInt(1000));
				int i = new Random().nextInt(100);
				if (i < 99) {
					throw new IllegalArgumentException();
				}
				return i;
			}
		};
		Future<Integer> future1 = Pool.submit(callable);
		Future<Integer> future2 = Pool.submit(callable);

		try {
			System.out.println(future1.get());
		} catch (Exception e) {
			System.out.println("异常");
		}
		try {
			System.out.println(future2.get());
		} catch (Exception e) {
			System.out.println("异常");
		}
		Pool.shutdown();
	}
}
