package concurrentprogram.chapter3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/9/5 17:52
 */
public class ShutDownHookTest {
	public static void start() {
		System.out.println("The JVM is started");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// 关闭连接，释放资源操作
				// 三秒后输出
				System.out.println("The JVM is execute");
			}
		});
	}

	public static void main(String[] args) {
		start();
		System.out.println("doing something");
		// 制造OutOfMemoryError
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			new ArrayList<>(1024*1024*1024);
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/**
		 * The JVM is started
		 * doing something
		 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
		 * 	at java.util.ArrayList.<init>(ArrayList.java:153)
		 * 	at concurrentprogram.chapter3.ShutDownHookTest.main(ShutDownHookTest.java:30)
		 * The JVM is execute
		 */
	}
}
