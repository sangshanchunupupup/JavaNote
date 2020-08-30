package concurrentprogram.chapter2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sangshanchun
 * @brief CyclicBarrier
 * @date 2020/8/29 13:49
 */
public class CyclicBarrierTest {
	private static final int NUM = 5;

	public static void main(String[] args) {

		ExecutorService exec = Executors.newCachedThreadPool();

		// 参数一：参与线程的个数，参数二：到达线程要做的任务，所有线程到达后才做。
		final CyclicBarrier barrier = new CyclicBarrier(NUM, new Runnable() {
			@Override
			public void run() {
				System.out.println("全部完成任务");
			}
		});

		for (int i = 0; i < NUM; i++) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + "到达栅栏1");
						// 线程调用await()，表示自己已经到达栅栏
						barrier.await();
						System.out.println(Thread.currentThread().getName() + "冲破栅栏1");

						// 可循环使用
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + "到达栅栏2");
						// 线程调用await()，表示自己已经到达栅栏
						barrier.await();
						System.out.println(Thread.currentThread().getName() + "冲破栅栏2");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		exec.shutdown();
	}
}
