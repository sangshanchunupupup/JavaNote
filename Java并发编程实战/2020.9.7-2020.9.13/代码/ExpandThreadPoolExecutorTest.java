package concurrentprogram.chapter4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author sangshanchun
 * @brief 扩展生命周期方法
 * @date 2020/9/11 22:13
 */
public class ExpandThreadPoolExecutorTest extends ThreadPoolExecutor {

	public static void main(String[] args) {
		ExpandThreadPoolExecutorTest t = new ExpandThreadPoolExecutorTest
				(10, 20, 0L,
						TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		Runnable r = () -> {
			System.out.println("线程执行任务");
		};
		t.execute(r);
		t.shutdown();
	}

	public ExpandThreadPoolExecutorTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.println("super.beforeExecute执行之前");
		super.beforeExecute(t, r);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		System.out.println("super.afterExecute之前");
		super.afterExecute(r, t);

	}

	@Override
	protected void terminated() {
		System.out.println("super.terminated之前");
		super.terminated();
	}

}