public class RunThreads implements Runnable {
	private String threadName;

	public String getName() {
		return threadName;
	}

	public void setName(String name) {
		threadName = name;
	}

	public static void main(String[] args) {
		RunThreads runner = new RunThreads();
		Thread alpha = new Thread(runner);
		alpha.setName("Alpha");
		Thread beta = new Thread(runner);
		beta.setName("Beta");
		alpha.start();
		beta.start();
	}

	public void run() {
		for (int i = 0; i < 25; i++) {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " thread is running. Iteration: " + (i + 1));
		}
	}
}