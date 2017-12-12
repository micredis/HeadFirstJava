import java.util.*;
import java.io.*;

public class Jukebox1 {
	private ArrayList<String> songList = new ArrayList<>();

	public static void main(String[] args) {
		new Jukebox1().go();
	}

	public void go() {
		getSongs();
		System.out.println("Unsorted: " + songList);
		Collections.sort(songList);
		System.out.println("Sorted:   " + songList);
	}

	void getSongs() {
		try {
			File file = new File("songs.txt");
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
		songList.add(tokens[0]);
	}

}