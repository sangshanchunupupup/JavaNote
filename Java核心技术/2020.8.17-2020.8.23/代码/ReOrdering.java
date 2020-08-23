package concurrentprogram;

/**
 * @author sangshanchun
 * @brief 重排序
 * @date 2020/8/18 22:21
 */
public class ReOrdering {
	private static boolean ready;
	private static int number;
	// 没有异常输出
	public static void main(String[] args) {
		Runnable r = () -> {
			while (!ready) {
				Thread.yield();
			}
			System.out.println(number);
		};
		new Thread(r).start();
		number = 42;
		ready = true;
	}
}
