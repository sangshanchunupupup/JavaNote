package concurrentprogram.chapter2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author sangshanchun
 * @brief SimpleDateFormat非线程安全测试
 * @date 2020/8/25 22:01
 */
public class SimpleDateFormatTest {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd, HHmmss");

	public static void main(String[] args) {
		for (; ; ) {
			Runnable r = () -> {
				System.out.println(sdf.format(new Date(Math.abs(new Random().nextLong()))));
			};
			new Thread(r).start();
		}
		/**
		 * 输出有异常
		 * 2385468010326, 233657
		 * Exception in thread "Thread-111564" java.lang.ArrayIndexOutOfBoundsException
		 * 852856670517, 083919
		 */
	}
}

