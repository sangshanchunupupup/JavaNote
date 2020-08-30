package concurrentprogram.chapter2;

import java.util.concurrent.Semaphore;

/**
 * @author sangshanchun
 * @brief Semaphore
 * @date 2020/8/29 10:58
 */
public class SemaphoreTest {
	private static Semaphore s1 = new Semaphore(1);
	private static Semaphore s2 = new Semaphore(0);
	private static Semaphore s3 = new Semaphore(0);

	public static void main(String[] args) {
		Runnable r1 = () -> {
			while (true) {
				try {
					s1.acquire();
					System.out.println("s1");
					s2.release();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable r2 = () -> {
			while (true) {
				try {
					s2.acquire();
					System.out.println("s2");
					s3.release();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable r3 = () -> {
			while (true) {
				try {
					s3.acquire();
					System.out.println("s3");
					s1.release();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		new Thread(r1).start();
		new Thread(r2).start();
		new Thread(r3).start();
	}
}
