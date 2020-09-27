package concurrentprogram.chapter6;

/**
 * @author sangshanchun
 * @brief 使用wait和notifyAll来实现可重新关闭的阀门，当处于close状态和迭代的版本相同时，就阻塞
 * @date 2020/9/23 20:31
 */
public class ThreadGate {
	private boolean isOpen;
	private int generation;

	public synchronized void close() {
		isOpen = false;
	}

	public synchronized void open() {
		++generation;
		isOpen = true;
		notifyAll();
	}

	public synchronized void await() throws InterruptedException {
		int arrivalGeneration = generation;
		if (!isOpen && arrivalGeneration == generation) {
			wait();
		}
	}
}


