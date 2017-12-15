import java.util.*;
import java.io.*;

public class Jukebox3 {
	private ArrayList<Song> songList = new ArrayList<Song>();

	public static void main(String[] args) {
		new Jukebox3().go();
	}

	public void go() {
		getSongs();
		System.out.println("Unsorted: " + songList);
		Collections.sort(songList);
		System.out.println("Sorted:   " + songList);
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
		songList.add(new Song(tokens[0], tokens[1], tokens[2], tokens[3]));
	}

}