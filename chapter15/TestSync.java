/** The "Lost Update" problem
* a classic concurrency problem.
* Actually, IT DOESN'T WORK as expected!
*/

public class TestSync implements Runnable {

	private int balance;

	// each thread runs 50 times,
	// increasing the balance on
	// each iteration
	public void run() {
		for (int i = 0; i < 50; i++) {
			increment();
		}
	}

	// try to comment/uncomment 'synchronized'
	// and compare the results
	public synchronized void increment() {
		int i = balance;
		// Here is the crucial part! We increment the balance by
		// adding 1 to whatever the value of balance was AT THE
		// TIME WE READ IT (rather than adding 1 to whatever
		// the CURRENT value is)
		// (this operation is deliberately made non-atomic
		// (although it still not be atomic even in case of:
		// ...increment() { balance++; } ))
		balance = i + 1;
		System.out.println("balance is " + balance + ". " + Thread.currentThread().getName());
	}
}