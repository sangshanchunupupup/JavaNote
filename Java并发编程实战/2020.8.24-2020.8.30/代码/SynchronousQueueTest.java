package concurrentprogram.chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author sangshanchun
 * @brief SynchronousQueue
 * @date 2020/8/29 10:39
 */
public class SynchronousQueueTest {
	public static void main(String[] args) {
		BlockingQueue<Object> blockingQueue = new SynchronousQueue<>();
		SynchronousQueueTest synchronousQueueTest = new SynchronousQueueTest();
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Consumer(blockingQueue)).start();
	}
}

class Producer implements Runnable {
	private BlockingQueue<Object> blockingQueue;
	List list = Arrays.asList("one", "two", "three");

	public Producer(BlockingQueue<Object> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			for (Object str : list) {
				// 将产生的元素放入synchronousQueue中
				blockingQueue.put(str);
			}
			// 结束标志
			blockingQueue.put("end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	private BlockingQueue<Object> blockingQueue;

	public Consumer(BlockingQueue<Object> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		Object object;
		while (true) {
			try {
				// 判断结束标志
				if (!(object = blockingQueue.take()).equals("end")) {
					System.out.println(object);
					// 消费线程睡眠三秒，证明没有take操作时，put操作也无法执行
					Thread.sleep(3000);
				} else {
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}