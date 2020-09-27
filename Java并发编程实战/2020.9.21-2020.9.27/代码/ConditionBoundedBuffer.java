package concurrentprogram.chapter6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sangshanchun
 * @brief 使用显式条件变量的有界缓存
 * @date 2020/9/23 21:08
 */
public class ConditionBoundedBuffer<T> {
	protected final Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	private final T[] buf = (T[]) new Object[20];
	private int tail, head, count;
	public void put(T t) throws InterruptedException {
		lock.lock();
		try {
			while (count == buf.length) {
				notFull.await();
			}
			buf[tail] = t;
			if (++tail == buf.length) {
				tail = 0;
			}
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	public T get() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				notEmpty.await();
			}
			T t = buf[head];
			buf[head] = null;
			if (++head == buf.length) {
				head = 0;
			}
			--count;
			notFull.signal();
			return t;
		} finally {
			lock.unlock();
		}
	}
}
