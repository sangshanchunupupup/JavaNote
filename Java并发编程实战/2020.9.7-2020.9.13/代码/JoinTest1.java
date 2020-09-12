package concurrentprogram.chapter4;

import com.sun.org.apache.xpath.internal.objects.XObject;

/**
 * @author sangshanchun
 * @brief 验证调用t.join()的线程必须获得t的锁
 * @date 2020/9/7 20:33
 */
public class JoinTest1 {
	public static void main(String[] args) throws InterruptedException {
		JoinTest1 test = new JoinTest1();
		long l1 = System.currentTimeMillis();
		A a = new A();
		// 线程启动后，将待执行任务加入线程组后就释放锁？
		a.start();
		// 下面两行语句中是否存在竞争锁的条件，
		new B(a).start();
		a.join();
		System.out.println("Main线程结束");
		System.out.println(System.currentTimeMillis() - l1);
	}
}

class A extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("线程A开始");
			Thread.sleep(2000);
			System.out.println("线程A结束");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class B extends Thread {
	private Thread t;

	public B(Thread t) {
		this.t = t;
	}

	@Override
	public void run() {
		synchronized (t) {
			try {
				System.out.println("B线程开始");
				Thread.sleep(5000);
				System.out.println("B线程结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}