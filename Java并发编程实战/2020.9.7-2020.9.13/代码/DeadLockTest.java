package concurrentprogram.chapter4;

/**
 * @author sangshanchun
 * @brief 顺序获取锁会造成死锁
 * @date 2020/9/12 9:36
 */
public class DeadLockTest {
	private static final Object o1 = new Object();
	private static final Object o2 = new Object();
	public static void main(String[] args) {
		new Module1(o1, o2).start();
		new Module2(o1, o2).start();
	}
}

class Module1 extends Thread {
	private Object object1;
	private Object object2;
	public Module1(Object object1, Object object2) {
		this.object1 = object1;
		this.object2 = object2;
	}
	@Override
	public void run() {
		synchronized (object1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			System.out.println("Module1获取object1");
			synchronized (object2) {
				System.out.println("Module1获取object2");
			}
		}
	}
}
class Module2 extends Thread {
	private Object object1;
	private Object object2;
	public Module2(Object object1, Object object2) {
		this.object1 = object1;
		this.object2 = object2;
	}
	@Override
	public void run() {
		synchronized (object2) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			System.out.println("Module2获取object2");
			synchronized (object1) {
				System.out.println("Module2获取object1");
			}
		}
	}
}