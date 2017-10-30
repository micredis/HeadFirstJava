public class GuessGame {
	private Player p1;
	private Player p2;
	private Player p3;

	void startGame() {
		while (true) {
			int targetNumber = (int) (Math.random() * 10);
			System.out.println("I'm thinking of a number between 0 and 9...");
			System.out.println("Number to guess is " + targetNumber);

			p1 = new Player();
			p2 = new Player();
			p3 = new Player();

			p1.guess();
			p2.guess();
			p3.guess();

			int p1Number = p1.getNumber();
			int p2Number = p2.getNumber();
			int p3Number = p3.getNumber();

			boolean p1IsRight = p1Number == targetNumber;
			boolean p2IsRight = p2Number == targetNumber;
			boolean p3IsRight = p3Number == targetNumber;

			if (p1IsRight || p2IsRight || p3IsRight) {
				System.out.println("We have a winner!");
				System.out.println("Player one got it right? " + p1IsRight);
				System.out.println("Player two got it right? " + p2IsRight);
				System.out.println("Player three got it right? " + p3IsRight);
				System.out.println("Game is over.");
				break;
			} else {
				System.out.println("Players will have to try again.");
			}
		}
	}
}