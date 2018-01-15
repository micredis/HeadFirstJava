// to run with assertions
// just type:
// java -ea AssertionsTest
// where "-ea", probably,
// stands for "enable assertions"

// to test each of the 3 assertions represented below
// you have to comment all preceeding assertions
// to that one you'd like to test

public class AssertionsTest {
	public static void main(String[] args) {
		// in case assertions are enabled:
		// if true, program continues normally
		// if false, throw an AssertionError
		// (see examples below)

		//assert (false) : "Assertions are enabled";

		int i = 0;
		for (; i < 25; i++) {

			//assert (i != 5) : "Wow, i == " + i + " !";

		}

		assert (i >= 30) : "There were less than 30 iterations. Amazing!";

		System.out.println("That's all, folks!");
	}
}