package concurrentprogram.chapter5;

import java.util.Random;

/**
 * @author sangshanchun
 * @brief 分段锁
 * @date 2020/9/16 9:40
 */
public class SegmentLock {
	private final Object[] locks;
	private static final int N_LOCKS = 16;
	private final Node[] nodes;

	public SegmentLock(int n) {
		locks = new Object[N_LOCKS];
		for (int i = 0; i < N_LOCKS; i++) {
			locks[i] = new Object();
		}
		nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node();
		}
	}

	public void method() {
		for (int i = 0; i < nodes.length; i++) {
			// 为不同的节点分配不同的锁
			synchronized (locks[i % N_LOCKS]) {
				Node node = nodes[i];
				node.setNum(i);
			}
		}
	}

	public static void main(String[] args) {
		SegmentLock segmentLock = new SegmentLock(20);
		segmentLock.method();
		System.out.println(segmentLock.nodes[new Random().nextInt(19)].getNum());
	}
	private static class Node {
		int num;
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
	}
}

