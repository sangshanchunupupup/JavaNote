package concurrentprogram.chapter6;

/**
 * @author sangshanchun
 * @brief 模拟CAS操作
 * @date 2020/9/24 8:59
 */
public class SimulatedCAS {
	private int value;

	public int getValue() {
		return value;
	}

	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		// 取旧值
		int oldValue = value;
		// 期望值与旧值比较
		if (oldValue == expectedValue) {
			// 将期望值赋给当前value
			value = expectedValue;
		}
		return oldValue;
	}

	public synchronized boolean compareAndSet(int expectedValue, int newValue) {
		return expectedValue == compareAndSwap(expectedValue, newValue);
	}
}

class CasCounter {
	private SimulatedCAS value;

	public int getValue() {
		return value.getValue();
	}

	public int increment() {
		int v;
		do {
			v = getValue();
		// 如果其他线程修改计数器，循环条件会一直成立，循环会一直进行，直到不满足循环条件为止
		} while (v != value.compareAndSwap(v, v + 1));
		return v + 1;
	}
}
