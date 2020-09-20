package concurrentprogram.chapter5;

import concurrentprogram.chapter4.SelfDefineExecutorTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author sangshanchun
 * @brief ReadWriteMap
 * @date 2020/9/15 8:26
 */

public class ReadWriteMap<K, V> {
	private final Map<K, V> map;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock r = lock.readLock();
	private final Lock w = lock.writeLock();

	public static void main(String[] args) {
		ReadWriteMap<String, String> readWriteMap = new ReadWriteMap<>(new HashMap<>(10));
		ExecutorService exec = SelfDefineExecutorTest.getSelfDefineExecutor();
		Runnable r1 = () -> {
			try {
				Thread.sleep(1000);
				readWriteMap.put("1", "1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Runnable r2 = () -> {
			try {
				Thread.sleep(1000);
				readWriteMap.put("2", "2");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Runnable r3 = () -> {
			try {
				Thread.sleep(1000);
				readWriteMap.get("1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Runnable r4 = () -> {
			try {
				Thread.sleep(1000);
				readWriteMap.get("2");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		exec.execute(r1);
		exec.execute(r2);
		exec.execute(r3);
		exec.execute(r4);
		// 等所有任务执行完毕后关闭线程池
		exec.shutdown();
	}

	public ReadWriteMap(Map<K, V> map) {
		this.map = map;
	}

	public V get(K k) {
		r.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " get操作获取锁");
			return map.get(k);
		} finally {
			r.unlock();
			System.out.println(Thread.currentThread().getName() + " get操作释放锁");
		}
	}

	public void put(K k, V v) {
		w.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " put操作获取锁");
			map.put(k, v);
		} finally {
			w.unlock();
			System.out.println(Thread.currentThread().getName() + " put操作释放锁");
		}
	}
}
