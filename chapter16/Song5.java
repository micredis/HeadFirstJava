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
		return "\n" + title + " : " + artist + " : " + rating + " : " + bpm;
	}
}