package concurrentprogram;

/**
 * @author sangshanchun
 * @brief 验证重入锁
 * @date 2020/8/22 11:04
 */
public class ReLoadLock {
	public static void main(String[] args) {
		new Apple().method1();
	}
}
class Fruit {
	public synchronized void method() {
		System.out.println(Thread.currentThread().getName() + "父类同步方法");
	}
}
class Apple extends Fruit {
	public synchronized void method1() {
		method2();
		System.out.println(Thread.currentThread().getName() + "子类同步方法1");
	}
	public synchronized void method2() {
		super.method();
		System.out.println(Thread.currentThread().getName() + "子类同步方法2");
	}
}
