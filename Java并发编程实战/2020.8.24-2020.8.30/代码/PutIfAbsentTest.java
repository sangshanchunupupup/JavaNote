package concurrentprogram.chapter2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * @author sangshanchun
 * @brief 多种方式在原有的集合上添加一个原子操作
 * @date 2020/8/28 21:54
 */
public class PutIfAbsentTest {

}

class BetterVector<E> extends Vector<E> {
	public synchronized boolean puIfAbsent(E x) {
		boolean absent = !contains(x);
		if (absent) {
			add(x);
		}
		return absent;
	}
}

class BetterList<E> {
	public List<E> list = Collections.synchronizedList(new ArrayList<>());

	public boolean putIfAbsent(E x) {
		// 在list上加锁，保证线程安全性
		synchronized (list) {
			boolean absent = !list.contains(x);
			if (absent) {
				list.add(x);
			}
			return absent;
		}
	}
}

class BetterList2<E> {
	// 在list的父类上加锁
	public List<E> list = Collections.synchronizedList(new ArrayList<>());

	// synchronizedList(java.util.List<E>, java.lang.Object)非public方法，
	// 所以不能将BetterList2对象作为锁对象传给list的mutex
	// public List<E> list2 = Collections.synchronizedList(new ArrayList<>(), this);

	// 在BetterList2对象上加锁，由于加锁对象不同，所以不能保证线程安全性
	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !list.contains(x);
		if (absent) {
			list.add(x);
		}
		return absent;
	}
}