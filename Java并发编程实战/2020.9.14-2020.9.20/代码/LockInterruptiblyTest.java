package concurrentprogram.chapter5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sangshanchun
 * @brief lockInterruptibly
 * @date 2020/9/18 8:59
 */
public class LockInterruptiblyTest {
	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException{
		Runnable r1 = () -> {
			doBusiness();
		};
		Runnable r2 = () -> {
			doBusiness();
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		Thread.sleep(10);
		t2.start();
		Thread.sleep(200);
		t2.interrupt();
		/**
		 * Thread-0开始获取锁，等待中...
		 * Thread-0得到锁，开始工作
		 * Thread-0 1
		 * Thread-1开始获取锁，等待中...
		 * Thread-1被中断
		 * Thread-1被中断运行结束
		 * Thread-0 2
		 * Thread-0 3
		 * Thread-0 4
		 * Thread-0 5
		 * Thread-0释放锁
		 */
	}

	public static void doBusiness() {
		long begin = System.currentTimeMillis();
		String name = Thread.currentThread().getName();
		try {
			System.out.println(name + "开始获取锁，等待中...");
			lock.lockInterruptibly();
			try {
				System.out.println(name + "得到锁，开始工作");
				for (int i = 0; i < 5; i++) {
					System.out.println(name + " " + (i + 1));
					Thread.sleep(1000);
				}
			} finally {
				lock.unlock();
				System.out.println(name + "释放锁");
			}
		} catch (InterruptedException e) {
			System.out.println(name + "被中断");
		} finally {
			long time = System.currentTimeMillis() - begin;
			if (time < 5000) {
				System.out.println(name + "被中断运行结束");
			}
		}
	}
}
