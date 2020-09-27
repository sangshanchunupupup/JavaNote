package concurrentprogram.chapter6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sangshanchun
 * @brief 使用lock来实现信号量
 * @date 2020/9/23 21:19
 */
public class SemaphoreOnLock {
	private final Lock lock = new ReentrantLock();
	private final Condition con = lock.newCondition();
	private int permits;

	public SemaphoreOnLock(int initialPermits) {
		lock.lock();
		try {
			permits = initialPermits;
		} finally {
			lock.unlock();
		}
	}
	public void acquire() throws InterruptedException{
		lock.lock();
		try {
			while (permits <= 0) {
				// 在循环条件成立的条件下被通知后还会再次进入等待
				con.await();
			}
			--permits;
		} finally {
			lock.unlock();
		}
	}
	public void release() throws InterruptedException{
		lock.lock();
		try {
			++permits;
			con.signal();
		} finally {
			lock.unlock();
		}
	}
}
