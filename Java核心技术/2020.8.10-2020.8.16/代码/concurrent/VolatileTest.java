package javacore.chapter4.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sangshanchun
 * @brief volatile关键字
 * @date 2020/8/15 10:02
 */
public class VolatileTest {
	public Map<String, String> map = new HashMap<>();
	private static int i = 0;
	/**
	 * 变量经过volatile修饰后，对此变量进行写操作时，汇编指令中会有一个LOCK前缀指令，会引发两个步骤：
	 * 1、发生修改后强制将当前处理器缓存行的数据写回到系统内存。
	 * 2、这个写回内存的操作会使得在其他处理器缓存了该内存地址无效，重新从内存中读取。
	 * 看似保证原子性
	 */
	private static final int SUM = 500;

	public static void main(String[] args) {
		for(int j = 0; j< SUM; j++) {
			Runnable r = () -> {
				i++;
			};
			Thread t = new Thread(r);
			t.start();
		}
		// i < 500,说明volatile不保证原子性
		System.out.println(i);
		/**
		 *  1、线程读取i
		 *  2、temp = i + 1
		 *  3、i = temp
		 *  当 i=5 的时候A,B两个线程同时读入了 i 的值，
		 *  然后A线程执行了temp = i + 1的操作，
		 *  然后B线程也执行了temp = i + 1的操作，此时A，B两个线程保存的i的值都是5，temp的值都是6，
		 *  然后A线程执行了i = temp(6)的操作，此时i的值会立即刷新到主存并通知其他线程保存的i值失效，
		 *  此时B线程需要重新读取i的值那么此时B线程保存的i就是6，同时B线程保存的temp还仍然是6，
		 *  然后B线程执行i=temp(6)，所以导致了计算结果比预期少了1。
		 */
	}
}
