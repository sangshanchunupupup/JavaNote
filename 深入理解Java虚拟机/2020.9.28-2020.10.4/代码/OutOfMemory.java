package jvm.memoryoverflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/9/29 10:53
 */
public class OutOfMemory {
	static class A {

	}

	private int a = 1;

	public static void main(String[] args) throws Throwable {
		OutOfMemory outOfMemory = new OutOfMemory();
		try {
			outOfMemory.add();
		} catch (Throwable e) {
			System.out.println(outOfMemory.a);
			throw e;
		}
		List<A> list = new ArrayList<>();
		// 堆内存溢出
		while (true) {
			list.add(new A());
		}


	}

	/**
	 * 栈溢出，java.lang.StackOverflowError
	 */
	public void add() {
		a++;
		add();
	}

	/**
	 * 
	 */
	public void method() {
		while (true) {
			Runnable r = () -> {
				while (true) {

				}
			};
			new Thread(r).start();
		}
	}

	/**
	 * 运行时常量池内存溢出
	 */
	public void method2() {
		Set<String> set = new HashSet<String>();
		short i = 0;
		while (true) {
			set.add(String.valueOf(i++).intern());
		}
	}
}
