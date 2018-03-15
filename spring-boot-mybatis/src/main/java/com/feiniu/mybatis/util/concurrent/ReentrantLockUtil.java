package com.feiniu.mybatis.util.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUtil {
	public static int i = 4;

	public static void main(String[] args) {
		final ReentrantLock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();

		for (int i = 0; i < 4; i++)
			new ReentrantThread(lock, condition).start();
	}

	static class ReentrantThread extends Thread {
		private ReentrantLock lock;
		private Condition condition;

		public ReentrantThread(ReentrantLock lock, Condition condition) {
			this.lock = lock;
			this.condition = condition;
		}

		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println("开始线程" + name);
			lock.lock();
			try {
				i--;
				System.out.println(i);
				if (i == 0) {
					condition.signalAll();
				} else {
					condition.await();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.unlock();
			System.out.println("结束线程" + name);
		}
	}
}
