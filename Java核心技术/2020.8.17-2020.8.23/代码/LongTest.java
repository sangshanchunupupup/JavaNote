package concurrentprogram;

/**
 * @author sangshanchun
 * @brief long、double非同步测试
 * @date 2020/8/19 20:29
 */
public class LongTest {
	public long num;
	private static final int SUM = 10000;

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public static void main(String[] args) {
		LongTest longTest = new LongTest();
		Runnable r1 = () -> {
			longTest.setNum(100L);
		};
		Runnable r2 = () -> {
			long temp = longTest.getNum();
			if (temp != 100L) {
				System.out.println(temp);
			}
		};
		for (int i = 0; i < SUM; i++) {
			new Thread(r1).start();
			new Thread(r2).start();
		}
	}
}
