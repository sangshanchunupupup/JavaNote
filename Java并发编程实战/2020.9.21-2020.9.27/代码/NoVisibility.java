package concurrentprogram.chapter6;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sangshanchun
 * @brief 指令重排序、可见性测试
 * @date 2020/9/24 15:45
 */
public class NoVisibility {
	private boolean isReady = false;
	private int number;
	private static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10000; i++) {
			NoVisibility noVisibility = new NoVisibility();
			Runnable r = () -> {
				// 可能持续循环
				while (!noVisibility.isReady) {
					// doNothing
				}
				// 可能输出0
				list.add(noVisibility.number);
			};
			Thread.sleep(1);
			noVisibility.number = 42;
			noVisibility.isReady = true;
		}
		list.forEach(v -> {
			if (v == 0) {
				System.out.println(v);
			}
		});
	}
}
