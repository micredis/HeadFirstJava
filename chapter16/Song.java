public class Song implements Comparable<Song> {
	private String title;
	private String artist;
	private String rating;
	private String bmp;

	Song(String t, String a, String r, String b) {
		title = t;
		artist = a;
		rating = r;
		bmp = b;
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
		return bmp;
	}

	public String toString() {
		return title;
	}

	public int compareTo(Song s) {
		return this.getTitle().compareTo(s.getTitle());
	}
}