public class Song5 {
	private String title;
	private String artist;
	private String rating;
	private String bpm;

	Song5(String t, String a, String r, String b) {
		title = t;
		artist = a;
		rating = r;
		bpm = b;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getRating() {
		return rating;
	}

	public String getBpm() {
		return bpm;
	}

	public String toString() {
		// "%-15s" means 15-symbol string (s) that is left justified (-)
		return String.format("\n%-15s : %-15s : %-2s : %-4s", title, artist, rating, bpm);
	}
}