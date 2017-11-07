import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Math;

public class SimpleDotComGame {
	public static void main(String[] args) throws IOException {
		int numOfGuesses = 0;
		GameHelper helper = new GameHelper();
		SimpleDotCom theDotCom1 = new SimpleDotCom();
		SimpleDotCom theDotCom2 = new SimpleDotCom();
		SimpleDotCom theDotCom3 = new SimpleDotCom();
		theDotCom1.setName("Go2.com");
		theDotCom2.setName("Pets.com");
		theDotCom3.setName("AskMe.com");
		int randomNum = (int) (Math.random() * 5); //(int) (Math.random() * (4 - 0 + 1) + 0) --> [0; 4]
		int[] locations = {randomNum, randomNum + 1, randomNum + 2};
		theDotCom.setLocationCells(locations);
		boolean isAlive = true;
		while (isAlive) {
			String guess = helper.getUserInput("Input number");
			String result = theDotCom.checkYourself(guess);
			numOfGuesses++;
			if ("kill".equals(result)) {
				isAlive = false;
				System.out.println("You have used " + numOfGuesses + " guess(es)");
			}
		}
	}
}