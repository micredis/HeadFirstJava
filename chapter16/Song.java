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
		// it's not needed to override compareTo() method
		// to compare Song objects. We just use the realization
		// of compareTo() method for String objects
		// i.e. we're comparing 2 strings here:
		return this.getTitle().compareTo(s.getTitle());
		// or as the authors of the book say:
		// return title.compareTo(s.getTitle());
	}
}