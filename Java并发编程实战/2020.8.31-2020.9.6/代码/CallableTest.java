package concurrentprogram.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/9/5 17:20
 */
public class CallableTest {
	private static final int NUM = 10;
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<String>> list = new ArrayList<>();
		for (int i = 0; i < NUM; i++) {
			list.add(exec.submit(new Counter("Thread", i)));
		}
		for (Future<String> future : list) {
			System.out.println(future.isDone());
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} finally {
				exec.shutdown();
			}
		}
	}
}
class Counter implements Callable<String> {
	private String name;
	private int num;

	public Counter(String name, int num) {
		this.name = name;
		this.num = num;
	}

	@Override
	public String call() throws Exception {
		return name + num;
	}
}