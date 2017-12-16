import java.util.*;
import java.io.*;

public class Jukebox5 {
	private ArrayList<Song5> songList = new ArrayList<Song5>();

	public static void main(String[] args) {
		new Jukebox5().go();
	}

	public void go() {
		int option = 1;
		Comparator compareCriterion;
		
		System.out.println("Choose the option you'd like the list to be sorted:");
		System.out.println("1 -- title,  2 -- artist,  3 -- rating,  4 -- bpm");

		try {
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			String sOption = "";
			while (!(sOption = console.readLine()).matches("[1-4]")) {
				System.out.println("The number must be within the range 1 to 4");
			}
			option = Integer.valueOf(sOption);
			console.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		switch (option) {
			case 1: compareCriterion = new TitleCompare();
					break;
			case 2: compareCriterion = new ArtistCompare();
					break;
			case 3: compareCriterion = new RatingCompare();
					break;
			case 4: compareCriterion = new BPMCompare();
					break;
			default: compareCriterion = new TitleCompare();
					break;
		}

		getSongs();
		System.out.println("UNSORTED: " + songList);
		System.out.println("----------------------------------------------");
		// invoke sort(), passing it the list and
		// a reference to the new custom Comparator object
		Collections.sort(songList, compareCriterion);
		System.out.println("SORTED:   " + songList);
	}

	void getSongs() {
		try {
			File file = new File("songs_detailed.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				addSong(line);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	void addSong(String lineToParse) {
		String[] tokens = lineToParse.split("/");
		songList.add(new Song5(tokens[0], tokens[1], tokens[2], tokens[3]));
	}

	// create a new inner class that implements Comparator
	// (note that its type parameter matches the type we're
	// going to compare--in this case Song5 objects)
	class TitleCompare implements Comparator<Song5> {
		public int compare(Song5 one, Song5 two) {
			// we're letting the String variables (for title)
			// do the actual comparison, since Strings already
			// know how to alphabetize themselves
			return one.getTitle().compareTo(two.getTitle());
		}
	}

	class ArtistCompare implements Comparator<Song5> {
		public int compare(Song5 one, Song5 two) {
			return one.getArtist().compareTo(two.getArtist());
		}
	}

	class RatingCompare implements Comparator<Song5> {
		public int compare(Song5 one, Song5 two) {
			return one.getRating().compareTo(two.getRating());
		}
	}

	// we must convert Strings into ints
	// to compare the numbers (bpms) properly
	class BPMCompare implements Comparator<Song5> {
		public int compare(Song5 one, Song5 two) {
			int oneBpm = Integer.valueOf(one.getBpm());
			int twoBpm = Integer.valueOf(two.getBpm());
			return (oneBpm < twoBpm) ? -1 : (oneBpm > twoBpm) ? 1 : 0;
		}
	}

}