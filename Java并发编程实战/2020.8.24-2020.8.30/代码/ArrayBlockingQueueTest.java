package concurrentprogram.chapter2;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author sangshanchun
 * @brief ArrayBlockingQueue
 * @date 2020/8/29 9:39
 */
public class ArrayBlockingQueueTest {
	public static void main(String[] args) {
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
		final Random random = new Random();

		Runnable r1 = () -> {
			while (true) {
				int num = random.nextInt(100);
				try {
					queue.put(num);
					Thread.sleep(1000);
					// 容量满的时候，自动阻塞
					if (queue.size() == 3) {
						System.out.println("full");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable r2 = () -> {
			while (true) {
				try {
					// 容量空的时候，自动阻塞
					System.out.println("取出：" + queue.take() + "剩余容量：" + queue.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		new Thread(r1).start();
		new Thread(r2).start();
	}
}
