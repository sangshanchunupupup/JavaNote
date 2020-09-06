package concurrentprogram.chapter3;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/9/5 19:58
 */
public class PrimeGenerator {
	private final List<BigInteger> primes = new ArrayList<>();
	/**
	 * 	使用volatile类型的域来保存取消状态
 	 */
	private volatile boolean cancelled;

	Runnable r = () -> {
		BigInteger p = BigInteger.ONE;
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
			}
		}
	};

	public void cancel() {
		cancelled = true;
	}

	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}

	public static void main(String[] args) {
		PrimeGenerator primeGenerator = new PrimeGenerator();
		new Thread(primeGenerator.r).start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {

		} finally {
			primeGenerator.cancel();
		}

		System.out.println(primeGenerator.get());
	}

}
