package concurrentprogram;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author sangshanchun
 * @brief Timer
 * @date 2020/8/22 10:54
 */
public class TimerTaskTest {
	public static void main(String[] args) {
		new Reminder(3);
	}

}
class Reminder {
	private Timer timer;
	public Reminder(int sec) {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("start");
				// 不cancel会一直运行下去
				timer.cancel();
			}
		}, sec * 1000);
	}
}
