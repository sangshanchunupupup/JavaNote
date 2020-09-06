package concurrentprogram.chapter3;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/9/5 20:10
 */
public class InterruptedTest {
	public static void main(String[] args) {
		String name1 = Thread.currentThread().getName();
		Thread.currentThread().interrupt();
		System.out.println(name1 + " " + Thread.currentThread().isInterrupted());
		Thread.interrupted();
		System.out.println(name1 + " " + Thread.currentThread().isInterrupted());
		Runnable r = () -> {
			String name = Thread.currentThread().getName();
			Thread.currentThread().interrupt();
			System.out.println(name + " " + Thread.currentThread().isInterrupted());
			Thread.interrupted();
			System.out.println(name + " " + Thread.currentThread().isInterrupted());
		};
		new Thread(r).start();
	}
}
