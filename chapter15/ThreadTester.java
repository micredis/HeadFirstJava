public class ThreadTester {

	public static void main(String[] args) {
		Runnable threadJob = new MyRunnable();
		Thread myThread = new Thread(threadJob);

		myThread.start();

		// The Thread.sleep() is optional.
		// It is put here to influence turn-taking
		// so the "back in main" part would
		// likely to be shown AFTER myThread dies
		// (i.e. after the "top o' the stack" message
		// is shown)

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("back in main");
	}
}