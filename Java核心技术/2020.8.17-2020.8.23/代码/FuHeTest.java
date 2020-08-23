package concurrentprogram;

import java.util.Vector;

/**
 * @author sangshanchun
 * @brief 复合操作、竞态条件
 * @date 2020/8/18 21:27
 */
public class FuHeTest {
	private static final int SUM = 10000;
	private static final String VECTOR = "vector";

	public static void main(String[] args) {
		Vector<String> vector = new Vector<>();
		Runnable r = () -> {
			if (!vector.contains(VECTOR)) {
				vector.add(VECTOR);
			}
		};
		for (int i = 0; i < SUM; i++) {
			new Thread(r).start();
		}
		// 输出的值存在大于1的情况，说明即使vector的contains是原子操作，
		// add方法被synchronized修饰，也是原子操作，
		// 但是多个原子操作组合在一起就存在竞态条件
		System.out.println(vector.size());
	}
}
