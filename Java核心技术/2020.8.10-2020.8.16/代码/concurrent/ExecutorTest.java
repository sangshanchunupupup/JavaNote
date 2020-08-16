package javacore.chapter4.concurrent;

import java.util.Hashtable;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sangshanchun
 * @brief 线程池
 * @date 2020/8/15 13:02
 */
public class ExecutorTest {
	/**
	 * 1、newCacheThreadPool
	 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	 * 2、newFixedThreadPool
	 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	 * 3、newScheduleThreadPool
	 * 创建一个定长线程池，支持定时及周期性任务执行。可设置延迟时间。
	 * 4、newSingleThreadPool
	 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
	 */
	private static ExecutorService executorService = Executors.newSingleThreadExecutor();
	private static ExecutorService threadPoolExecutor;
	private static int i = 0;
	private static final int SUM = 10;

	public static void main(String[] args) {
		Runnable r = () -> {
			while (i++ < SUM) {
				try {
					Thread.sleep(100);
					System.out.println(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		// 默认线程池执行
		executorService.execute(r);
		executorService.shutdown();
		// 自定义线程池执行
		getThreadPoolExecutor().execute(r);
		getThreadPoolExecutor().shutdown();
	}

	/**
	 * 自定义线程池
	 */
	public static ExecutorService getThreadPoolExecutor() {
		if (null == threadPoolExecutor) {
			threadPoolExecutor = new ThreadPoolExecutor
					(5,
							5,
							0L,
							TimeUnit.MILLISECONDS,
							new LinkedBlockingDeque<Runnable>(10),
							Executors.defaultThreadFactory());
		}
		return threadPoolExecutor;
	}
}
