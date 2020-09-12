package concurrentprogram.chapter4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sangshanchun
 * @brief tryLock()在获取不到锁的情况下会一直等待，使用tryLock(time, unit)可以避免死锁
 * @date 2020/9/12 13:49
 */
public class TryLockTest {
	private static final Lock lock1 = new ReentrantLock();
	private static final Lock lock2 = new ReentrantLock();

	public static void main(String[] args) {
		TryLockTest tryLockTest = new TryLockTest();
		Thread t1 = new Thread(() -> {
			try {
				tryLockTest.method1();
			} catch (InterruptedException e) {

			}
		});
		Thread t2 = new Thread(() -> {
			try {
				tryLockTest.method2();
			} catch (InterruptedException e) {

			}
		});
		t1.start();
		t2.start();
	}

	public void method1() throws InterruptedException {
		if (lock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
			Thread.sleep(2000);
			if (lock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
				System.out.println("method1运行完毕");
			} else {
				System.out.println("method1中lock2获取超时");
			}
		} else {
			System.out.println("method1中lock1获取超时");
		}
	}

	public void method2() throws InterruptedException {
		if (lock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
			Thread.sleep(2000);
			if (lock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
				System.out.println("method2运行完毕");
			} else {
				System.out.println("method2中lock1获取超时");
			}
		} else {
			System.out.println("method2中lock2获取超时");
		}
	}
}
