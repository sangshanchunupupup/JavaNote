package javacore.chapter4.concurrent;

import java.util.concurrent.*;

/**
 * @author sangshanchun
 * @brief 线程局部变量
 * @date 2020/8/15 18:01
 */
public class ThreadLocalTest {
	private static final ThreadLocal<String> localVar = new ThreadLocal<>();
	public static void printInfo(String str) {
		// 打印当前线程中本地内存中本地变量的值
		System.out.println(str + " :" + localVar.get());
		// 清除本地内存中的本地变量
		localVar.remove();
	}

	public static void main(String[] args) {
		Runnable r1 = () -> {
			// 设置线程1中本地变量的值
			localVar.set("localVar1");
			printInfo("thread1");
			// 打印本地变量
			System.out.println("after remove : " + localVar.get());
		};
		Runnable r2 = () -> {
			// 设置线程2中本地变量的值
			localVar.set("localVar2");
			printInfo("thread2");
			// 打印本地变量
			System.out.println("after remove : " + localVar.get());
		};
		new Thread(r1).start();
		new Thread(r2).start();
	}
}
