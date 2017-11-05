import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Math;

public class SimpleDotComGame {
	public static void main(String[] args) throws IOException {
		int numOfGuesses = 0;
		String result = "";
		SimpleDotCom game = new SimpleDotCom();
		int startIndex = (int) (Math.random() * 5); //(int) (Math.random() * (4 - 0 + 1) + 0) --> [0; 4]
		int[] locs = {startIndex, startIndex + 1, startIndex + 2};
		game.setLocationCells(locs);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (!"kill".equals(result)) {
			System.out.print("Input number ");
			String guess = br.readLine();
			numOfGuesses++; 
			result = game.checkYourself(guess);
		}
		System.out.println("You have used " + numOfGuesses);
		System.out.println("guess(es)");
		br.close();
	}
}