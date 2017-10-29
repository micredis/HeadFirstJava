public class BeerSong {
	public static void main(String[] args) {
		int beerNum = 99;
		String word = "bottles";

		while (beerNum > 1) {
			System.out.println(beerNum + " " + word + " of beer on the wall, " + beerNum + " " + word + " of beer.");
			beerNum--;
			if (beerNum == 1) word = "bottle";
			System.out.println("Take one down and pass it around, " + beerNum + " " + word + " of beer on the wall.");
			System.out.println();
		}

		System.out.println("1 bottle of beer on the wall, 1 bottle of beer.");
		System.out.println("Take one down and pass it around, no more bottles of beer on the wall.");
		System.out.println();
		System.out.println("No more bottles of beer on the wall, no more bottles of beer.");
		System.out.println("Go to the store and buy some more, 99 bottles of beer on the wall.");

	}
}