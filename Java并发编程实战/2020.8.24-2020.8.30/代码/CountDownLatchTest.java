package concurrentprogram.chapter2;

import java.util.concurrent.CountDownLatch;

/**
 * @author sangshanchun
 * @brief CountDownLatch
 * @date 2020/8/29 9:54
 */
public class CountDownLatchTest {
	public static final int N = 10;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startSingle = new CountDownLatch(1);
		CountDownLatch downSingle = new CountDownLatch(N);
		for (int i = 1; i <= N; i++) {
			new Thread(new Worker(startSingle, downSingle, i)).start();
		}
		System.out.println("start");
		// startSingle信号减一，开始执行线程
		startSingle.countDown();
		// 等得所以有线程执行完毕，N减为0
		downSingle.await();
		System.out.println("end");
	}
}

class Worker implements Runnable {
	private final CountDownLatch startSingle;
	private final CountDownLatch downSingle;
	private int beginIndex;

	public Worker(CountDownLatch startSingle, CountDownLatch downSingle, int beginIndex) {
		this.startSingle = startSingle;
		this.downSingle = downSingle;
		this.beginIndex = beginIndex;
	}

	@Override
	public void run() {
		try {
			// 阻塞，等待开始
			startSingle.await();
			beginIndex = (beginIndex - 1) * 10 + 1;
			for (int i = beginIndex; i < beginIndex + 10; i++) {
				System.out.print(i + " ");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 执行完毕，信号释放一
			downSingle.countDown();
		}
	}
}
