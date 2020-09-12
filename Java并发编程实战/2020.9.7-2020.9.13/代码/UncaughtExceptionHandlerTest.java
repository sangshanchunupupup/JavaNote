package concurrentprogram.chapter4;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/9/11 22:13
 */
public class UncaughtExceptionHandlerTest implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("重写捕获方法: " + t.getName());
		e.printStackTrace();
	}

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			int[] arr = new int[2];
			System.out.println(arr[2]);
		});
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandlerTest());
		t.start();
	}
}