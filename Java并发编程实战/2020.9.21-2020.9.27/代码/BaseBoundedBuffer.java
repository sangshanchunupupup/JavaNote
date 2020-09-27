package concurrentprogram.chapter6;

/**
 * @author sangshanchun
 * @brief 自定义同步工具
 * @date 2020/9/23 17:41
 */
public class BaseBoundedBuffer<T> {
	private final T[] buf;
	private int tail;
	private int head;
	private int count;

	protected BaseBoundedBuffer(int capacity) {
		this.buf = (T[]) new Object[capacity];
	}

	protected synchronized final void doPut(T t) {
		buf[tail] = t;
		if (++tail == buf.length) {
			tail = 0;
		}
		++count;
	}

	protected synchronized final T doGet() {
		T t = buf[head];
		buf[head] = null;
		if (++head == buf.length) {
			head = 0;
		}
		--count;
		return t;
	}

	protected synchronized final boolean isFull() {
		return count == buf.length;
	}

	protected synchronized final boolean isEmpty() {
		return count == 0;
	}
}
