package concurrentprogram.chapter4;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/9/7 20:58
 */
public class JoinTest2 {
	public static void main(String[] args) throws InterruptedException {
		Long l1 = System.currentTimeMillis();
		Runnable r = () -> {
			try {
				Thread.sleep(2000);
				System.out.println("线程A");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		};
		Thread t = new Thread(r);
		t.start();
		// millis是主线程等待的时间，如果大于t线程执行的时间，那么等待的时间是t线程执行的时间
		t.join(3000);
		System.out.println(System.currentTimeMillis() - l1);
	}
}
