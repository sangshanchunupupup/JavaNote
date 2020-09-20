package concurrentprogram.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sangshanchun
 * @brief Lock的Condition
 * @date 2020/9/18 10:22
 */
public class ConditionTest {
	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		Condition condition = lock.newCondition();
		Runnable r1 = () -> {
			String name = Thread.currentThread().getName();
			lock.lock();
			try {
				System.out.println(name + "获取到锁，等待信号");
				// 释放锁并在此等待，被signal通知返回，返回前获取锁
				condition.await();
				System.out.println(name + "获取到信号");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		};
		Runnable r2 = () -> {
			String name = Thread.currentThread().getName();
			lock.lock();
			try {
				System.out.println(name + "获取到锁");
				condition.signal();
				System.out.println(name + "发出信号");
			} finally {
				lock.unlock();
			}
		};
		new Thread(r1).start();
		new Thread(r2).start();
	}
}
