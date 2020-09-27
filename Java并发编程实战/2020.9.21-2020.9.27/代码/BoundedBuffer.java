package concurrentprogram.chapter6;

/**
 * @author sangshanchun
 * @brief 条件队列实现有界缓存
 * @date 2020/9/23 17:55
 */
public class BoundedBuffer<T> extends BaseBoundedBuffer<T> {
	public BoundedBuffer(int size) {
		super(size);
	}
	public synchronized void put(T t) throws InterruptedException{
		while (isFull()) {
			wait();
		}
		doPut(t);
		notifyAll();
	}
	public synchronized T get() throws InterruptedException{
		while (isEmpty()) {
			wait();
		}
		T t = doGet();
		notifyAll();
		return t;
	}
}
