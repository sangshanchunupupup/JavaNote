package javacore.chapter4.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sangshanchun
 * @brief Lock
 * @date 2020/8/15 20:58
 */
public class LockTest {
	private static int SUM = 20;
	private static Lock lock = new ReentrantLock();
	public static void main(String[] args) {
		Runnable r = () -> {
			lock.lock();
			try {
				while(SUM > 10) {
					SUM--;
					System.out.println("第一个线程" + SUM);
				}
			} finally {
				lock.unlock();
			}
		};
		new Thread(r).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				try {
					while(SUM > 0) {
						SUM--;
						System.out.println("第二个线程" + SUM);
					}
				} finally {
					lock.unlock();
				}
			}
		}).start();
	}
}