package javacore.chapter4.concurrent;

/**
 * @author sangshanchun
 * @brief synchronized
 * @date 2020/8/15 21:20
 */
public class SynchronizedTest {
	private static int SUM = 5;
	public static void main(String[] args) {
		SynchronizedTest synchronizedTest = new SynchronizedTest();
		Runnable r1 = () -> {
			for(int i = 0; i < 10; i++) {
				synchronizedTest.increase();
			}
		};
		Runnable r2 = () -> {
			for(int i = 0; i < 10; i++) {
				synchronizedTest.decrease();
			}
		};
		// 使用了对象锁，导致顺序输出
		new Thread(r1).start();
		new Thread(r2).start();
	}
	public synchronized void increase() {
		while(SUM == 5) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		SUM++;
		notify();
		System.out.println("increase数字成功:" + SUM);
	}
	public synchronized void decrease() {
		while(SUM == 0) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		SUM--;
		notify();
		System.out.println("decrease数字成功:" + SUM);
	}
}