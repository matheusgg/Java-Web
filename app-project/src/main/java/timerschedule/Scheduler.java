package timerschedule;

import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new MyScheduler(), 1000, 3000);
	}

}

class MyScheduler extends TimerTask {

	@Override
	public void run() {
		System.out.println("MyScheduler.run() " + Thread.currentThread());
	}

}
