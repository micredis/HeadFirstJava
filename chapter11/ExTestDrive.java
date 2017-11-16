class MyEx extends Exception {
}

public class ExTestDrive {
	
	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Provide an argument: yes/no");
			return;
		}

		String test = args[0];

		System.out.print("t");

		try {
			doRisky(test);
			System.out.print("r");
			System.out.print("o");
		} catch (MyEx e) {
			System.out.print("a");
		} finally {
			System.out.print("w");
			System.out.println("s");
		}
	}

	static void doRisky(String t) throws MyEx {

		System.out.print("h");

		if ("yes".equals(t)) {
			throw new MyEx();
		}
	}
}