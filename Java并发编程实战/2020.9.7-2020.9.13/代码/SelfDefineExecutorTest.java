package concurrentprogram.chapter4;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * @author sangshanchun
 * @brief 自定义线程池，更改默认拒绝策略，使用默认的线程工厂
 * @date 2020/9/11 21:09
 */
public class SelfDefineExecutorTest {
	private static final int NUM1 = 10;
	private static final int NUM2 = 20;
	private static final int CAPACITY = 10;
	public static ThreadPoolExecutor getSelfDefineExecutor() {
		return new ThreadPoolExecutor(NUM1, NUM2,
				0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(CAPACITY), new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
			}
		});
	}

}
