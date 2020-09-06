package concurrentprogram.chapter3;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/9/5 20:55
 */
public class PrimeProducer extends Thread{
	private final BlockingQueue<BigInteger> queue;

	public PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		BigInteger b = BigInteger.ONE;
		while (!Thread.currentThread().isInterrupted()) {
			try {
				queue.put(b = b.nextProbablePrime());
			} catch (InterruptedException e) {
				e.printStackTrace();
				// 如果不添加break, 虽然停止任务并捕捉到异常，但是jvm一直运行
				break;
			}
		}
	}
	public void cancel() {
		interrupt();
		System.out.println(Thread.currentThread().isInterrupted());
	}

	public static void main(String[] args) {
		BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<BigInteger>(1024);
		PrimeProducer p = new PrimeProducer(queue);
		p.start();
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p.cancel();
		System.out.println(queue);
	}
}
