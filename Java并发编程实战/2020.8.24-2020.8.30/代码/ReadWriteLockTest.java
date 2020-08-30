package concurrentprogram.chapter2;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author sangshanchun
 * @brief 读写锁
 * @date 2020/8/28 21:09
 */
public class ReadWriteLockTest {
	public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
		Runnable r1 = () -> {
			readWriteLockTest.read();
		};

		Runnable r2 = () -> {
			readWriteLockTest.write();
		};
		new Thread(r1).start();
		new Thread(r2).start();
		// Thread-0 readlock locked
		// Thread-0 wake up
		// Thread-1 writelock locked
		// Thread-1 wake up

		new Thread(r1).start();
		new Thread(r1).start();
		// Thread-0 readlock locked
		// Thread-1 readlock locked
		// Thread-0 wake up
		// Thread-1 wake up

		new Thread(r2).start();
		new Thread(r2).start();
		// Thread-0 readlock locked
		// Thread-0 wake up
		// Thread-1 writelock locked
		// Thread-1 wake up

	}

	public void read() {
		try {
			lock.readLock().lock();
			System.out.println(Thread.currentThread().getName() + " readlock locked");
			Thread.sleep(4000);
		} catch (Exception e) {

		} finally {
			lock.readLock().unlock();
			System.out.println(Thread.currentThread().getName() + " wake up");
		}
	}

	public void write() {
		try {
			lock.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + " writelock locked");
			Thread.sleep(4000);
		} catch (Exception e) {

		} finally {
			lock.writeLock().unlock();
			System.out.println(Thread.currentThread().getName() + " wake up");
		}
	}
}
