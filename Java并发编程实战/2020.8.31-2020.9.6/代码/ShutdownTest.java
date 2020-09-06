package concurrentprogram.chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/9/5 21:49
 */
public class ShutdownTest {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		try {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("前");
					try {
						Thread.sleep(3000);
						System.out.println("后");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		} finally {
			executorService.shutdown();
			// 检测executorService是否已经关闭
			System.out.println(executorService.awaitTermination(1000, TimeUnit.MILLISECONDS));
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/**
		 * 前
		 * false
		 * 后
		 * true
		 */
		System.out.println(executorService.awaitTermination(1000, TimeUnit.MILLISECONDS));
	}
}
