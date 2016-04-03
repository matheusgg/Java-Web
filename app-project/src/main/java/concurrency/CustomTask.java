package concurrency;

public class CustomTask implements Runnable {

	@Override
	public void run() {
		System.out.println("Task");
	}

}
