package concurrentprogram.chapter5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sangshanchun
 * @brief ReentrantLock是可重入锁，同一个线程可以嵌套式获得同一个锁
 * @date 2020/9/17 15:09
 */
public class LockTest {
	private static final Lock lock = new ReentrantLock();
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				lock.tryLock();
				try {
					System.out.println("Thread");
					method();
				} finally {
					lock.unlock();
				}
			}
		}.start();
	}
	public static void method() {
		lock.tryLock();
		try {
			System.out.println("method");
		} finally {
			lock.unlock();
		}
	}
}
