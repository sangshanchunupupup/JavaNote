package concurrentprogram.chapter5;

import learnjava.concurrent.base.SynchronizedDemo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author sangshanchun
 * @brief 减少锁的竞争
 * @date 2020/9/17 10:37
 */
public class ReduceLockCompete {
}
class A {
	private final Map<String, String> map = new HashMap<>();
	public synchronized void method(String key) {
		//...
		String value = map.get(key);
		//...
	}
}
class A1 {
	private final Map<String, String> map = new HashMap<>();
	public void method(String key) {
		//...减少同步代码块中的代码量，使得锁被持有的时间减少
		synchronized (this) {
			String value = map.get(key);
		}
		//...
	}
}
class B {
	private final Set<String> set1 = new HashSet<>();
	private final Set<String> set2 = new HashSet<>();
	public synchronized void addSet1(String value) {
		set1.add(value);
	}
	public synchronized void addSet2(String value) {
		set2.add(value);
	}
	public synchronized void removeSet1(String value) {
		set1.remove(value);
	}
	public synchronized void removeSet2(String value) {
		set2.remove(value);
	}
}
class B1 {
	private final Set<String> set1 = new HashSet<>();
	private final Set<String> set2 = new HashSet<>();
	// 对锁进行分解后可以同时对不同的set进行操作，降低锁的竞争
	public void addSet1(String value) {
		synchronized (set1) {
			set1.add(value);
		}
	}
	public void addSet2(String value) {
		synchronized (set2) {
			set2.add(value);
		}
	}
}