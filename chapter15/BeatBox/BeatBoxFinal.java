import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import java.net.*;
import javax.sound.midi.*;

public class BeatBoxFinal {

	JFrame theFrame;
	JPanel mainPanel;
	JList incomingList;
	JTextField userMessage;
	ArrayList<JCheckBox> checkboxList;
	int nextNum;
	Vector<String> listVector = new Vector<>();
	String userName;
	ObjectOutputStream out;
	ObjectInputStream in;
	HashMap<String, boolean[]> otherSeqsMap = new HashMap<>();

	Sequencer sequencer;
	Sequence sequence;
	// This variable seems REDUNDANT
	Sequence mySequence = null;
	Track track;

	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare",
								"Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas",
								"Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom",
								"High Agogo", "Open Hi Conga"};
	int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

	public static void main(String[] args) {
		if (args.length > 0 && args[0] != null) {
			new BeatBoxFinal().startUp(args[0]); // args[0] is your user ID/screen name
		} else {
			System.out.println("Please, provide your nickname: ");
			try {
				BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
				String nick = console.readLine();
				if (!nick.isEmpty()) {
					new BeatBoxFinal().startUp(nick);
				} else {
					System.out.println("The default nickname will be used");
					new BeatBoxFinal().startUp("default");
				}
				console.close();
			} catch (Exception ex) {
				// System.out.println("The default nickname will be used");
				// new BeatBoxFinal().startUp("default");
				ex.printStackTrace();
			}
		}
	}

	// Set up the networking, I/O, and make (and start) the reader thread
	public void startUp(String name) {
		userName = name;
		// open connection to the server
		try {
			Socket sock = new Socket("127.0.0.1", 4242);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			Thread remote = new Thread(new RemoteReader());
			remote.start();
		} catch (Exception ex) {
			System.out.println("couldn't connect - you'll have to play alone");
		}
		setUpMidi();
		buildGUI();
	}

	public void buildGUI() {

		if (!userName.isEmpty()) {
			theFrame = new JFrame("Cyber BeatBox -- " + userName);
		} else {
			theFrame = new JFrame("Cyber BeatBox -- noname");
		}
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		checkboxList = new ArrayList<JCheckBox>();

		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);

		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);

		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);

		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);

		JButton cleanCheckboxes = new JButton("Clean");
		cleanCheckboxes.addActionListener(new MyCleanCheckBoxes());
		buttonBox.add(cleanCheckboxes);

		JButton randomPattern = new JButton("Randomize!");
		randomPattern.addActionListener(new MyRandomPatternListener());
		buttonBox.add(randomPattern);

		JButton loadPattern = new JButton("Load");
		loadPattern.addActionListener(new MyLoadPatternListener());
		buttonBox.add(loadPattern);

		JButton savePattern = new JButton("Save");
		savePattern.addActionListener(new MySavePatternListener());
		buttonBox.add(savePattern);

		JButton sendIt = new JButton("Send It");
		sendIt.addActionListener(new MySendListener());
		buttonBox.add(sendIt);

		userMessage = new JTextField();
		buttonBox.add(userMessage);

		// JList is a component where the incoming messages are displayed.
		// You can select a message from the list to load and play the
		// attached beat pattern.
		incomingList = new JList();
		incomingList.addListSelectionListener(new MyListSelectionListener());
		incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane theList = new JScrollPane(incomingList);
		buttonBox.add(theList);
		incomingList.setListData(listVector); // no data to start with

		/*Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 16; i++) {
			nameBox.add(new Label(instrumentNames[i])); // TODO: JLabel should be used instead of Label
		}*/

		GridLayout gridNames = new GridLayout(16, 1);
		gridNames.setVgap(3);
		gridNames.setHgap(2);
		JPanel namePanel = new JPanel(gridNames);
		for (int i = 0; i < 16; i++) {
			namePanel.add(new JLabel(instrumentNames[i]));
		}

		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, namePanel);

		theFrame.getContentPane().add(background);
		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(3);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);

		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}

		theFrame.setBounds(300, 130, 350, 350);
		theFrame.pack();
		theFrame.setVisible(true);
	} // close buildGUI

	// Get the Sequencer, make a Sequence, and make a Track.
	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Build a track by walking through the checkboxes
	// to get their state, and mapping that to an instrument
	// (and making the MdidEvent for it).
	public void buildTrackAndStart() {
		ArrayList<Integer> trackList = null; // this will hold the instruments for each...
		sequence.deleteTrack(track);
		track = sequence.createTrack();

		for (int i = 0; i < 16; i++) {
			trackList = new ArrayList<Integer>();
			for (int j = 0; j < 16; j++) {
				JCheckBox jc = (JCheckBox) checkboxList.get(j + (i * 16));
				if (jc.isSelected()) {
					int key = instruments[i];
					trackList.add(new Integer(key));
				} else {
					trackList.add(null); // because this slot should be empty in the track
				}
			}
			makeTracks(trackList);
		}
		// track.add(makeEvent(192, 9, 1, 0, 15));
		track.add(makeEvent(176, 9, 120, 0, 16)); // - so we always go to full 16 bits
		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // close buildTrackAndStart

	// The GUI listeners

	public class MyStartListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			buildTrackAndStart();
		}
	}

	public class MyStopListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			sequencer.stop();
		}
	}

	public class MyUpTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor * 1.03));
		}
	}

	public class MyDownTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor * .97));
		}
	}

	public class MyCleanCheckBoxes implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			boolean[] selectedState = new boolean[256];
			changeSequence(selectedState); // put empty (default, i.e. false) values of array into checkboxes
			sequencer.stop();
		}
	}

	// Randomize checkboxes
	public class MyRandomPatternListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			// make an arraylist of randomly selected states of checkboxes
			boolean[] selectedState = new boolean[256];
			for (int i = 0; i < 256; i++) {
				boolean isSelected = (Math.random() < 0.05);
				selectedState[i] = isSelected;
			}
			changeSequence(selectedState);
			sequencer.stop();
			buildTrackAndStart();
		}
	}

	// Load saved checkboxes preset
	public class MyLoadPatternListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			int optionNumber = JOptionPane.showConfirmDialog(null,
						"Do you want to save the current pattern before proceeding?",
						"Save current pattern",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
			if (optionNumber == 0) {
				saveCurrentSelections();
			}

			JFileChooser fileOpen = new JFileChooser("."); // set the current directory as a path to open
			FileNameExtensionFilter filter = new FileNameExtensionFilter("BeatBox Patterns", "ser");
			fileOpen.setFileFilter(filter);
			fileOpen.showOpenDialog(theFrame);
			File fileToOpen = fileOpen.getSelectedFile();

			// if the option "Cancel" in the open dialog is chosen
			if (fileToOpen == null) return;

			boolean[] selectedState = null;

			try {
				FileInputStream fis = new FileInputStream(fileToOpen);
				ObjectInputStream ois = new ObjectInputStream(fis);
				selectedState = (boolean[]) ois.readObject();
				ois.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			changeSequence(selectedState);
			sequencer.stop();
			buildTrackAndStart();
		}
	}

	public class MySavePatternListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			saveCurrentSelections();
		}
	}

	// We serialize two objects (the string message and the beat pattern)
	// and write those two objects to the socket output stream (to the server).
	public class MySendListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			// make an arraylist of just the STATE of the checkboxes
			boolean[] checkboxState = new boolean[256];
			for (int i = 0; i < 256; i++) {
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				if (check.isSelected()) {
					checkboxState[i] = true;
				}
			}
			// The next line is probably REDUNDANT
			String messageToSend = null;
			try {
				out.writeObject(userName + nextNum++ + ": " + userMessage.getText());
				out.writeObject(checkboxState);
			} catch (Exception ex) {
				System.out.println("Sorry dude. Could not send it to the server.");
			}
			userMessage.setText("");
		}
	} // close MySendListener class

	// A ListSelectionListener tells us when the user made a selection on the list
	// of messages. When the user selects a message, after asking them if they
	// want to save their current pattern we load the associated beat pattern
	// (it's in the HashMap called otherSeqsMap) and start playing it.
	public class MyListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent le) {
			if (!le.getValueIsAdjusting()) {
				String selected = (String) incomingList.getSelectedValue();
				if (selected != null) {
					// ask if the user wants to save their pattern before
					// a pattern from the list of messages is loaded
					int optionNumber = JOptionPane.showConfirmDialog(null,
						"Do you want to save your pattern before proceeding?",
						"Save current pattern",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
					if (optionNumber == 0) {
						saveCurrentSelections();
					}

					// now go to the map, and change the sequence
					boolean[] selectedState = (boolean[]) otherSeqsMap.get(selected);
					changeSequence(selectedState);
					sequencer.stop();
					buildTrackAndStart();
				}
			}
		}
	} // close MyListSelectionListener class

	// This is the thread job -- read in data from the server.
	// 'data' -- meaning two serialized objects: the String message
	// and the beat pattern (an ArrayList of checkbox state values).
	//
	// When a message comes in, we read (deserialize) the two objects
	// (the message and the ArrayList of boolean checkbox state values)
	// and add it to the JList component. Adding to a JList is a two-step
	// thing: you you keep a Vector of the list data (Vector is synchronized
	// version of ArrayList), and then tell the JList to use that Vector
	// as its source for what t display in the list.
	public class RemoteReader implements Runnable {
		boolean[] checkboxState = null;
		String nameToShow = null;
		Object obj = null;
		public void run() {
			try {
				while((obj = in.readObject()) != null) {
					System.out.println("got an object from server");
					System.out.println(obj.getClass());
					String nameToShow = (String) obj;
					checkboxState = (boolean[]) in.readObject();
					otherSeqsMap.put(nameToShow, checkboxState);
					listVector.add(nameToShow);
					incomingList.setListData(listVector);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// This class seems REDUNDANT
	public class MyPlayMineListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			if (mySequence != null) {
				sequence = mySequence; // restore to my original
			}
		}
	}

	// Save current states of checkboxes into a specified *.ser file
	public void saveCurrentSelections() {
		boolean[] checkboxState = new boolean[256];
		for (int i = 0; i < 256; i++) {
			JCheckBox check = (JCheckBox) checkboxList.get(i);
			if (check.isSelected()) {
				checkboxState[i] = true;
			}
		}

		JFileChooser fileSave = new JFileChooser("."); // set the current directory as a path to open
		FileNameExtensionFilter filter = new FileNameExtensionFilter("BeatBox Patterns", "ser");
		fileSave.setFileFilter(filter);
		fileSave.showSaveDialog(theFrame);
		File fileToSave = fileSave.getSelectedFile();

		// if the option "Cancel" in the save dialog is chosen
		if (fileToSave == null) return;

		String extension = ".ser";
		if (fileToSave.toString().endsWith(extension)) {
			extension = "";
		}

		try {
			FileOutputStream fileStream = new FileOutputStream(fileToSave + extension);
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(checkboxState);
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} // close saveCurrentSelections method

	// This method is called when the user selects something
	// from the list. We IMEDIATELY change the pattern
	// to the one they selected.
	public void changeSequence(boolean[] checkboxState) {
		for (int i = 0; i < 256; i++) {
			JCheckBox check = (JCheckBox) checkboxList.get(i);
			if (checkboxState[i]) {
				check.setSelected(true);
			} else {
				check.setSelected(false);
			}
		}
	}

	public void makeTracks(ArrayList/*<Integer>*/ list) {
		Iterator it  = list.iterator();
		for (int i = 0; i < 16; i++) {
			Integer num = (Integer) it.next();
			if (num != null) {
				int numKey = num.intValue();
				track.add(makeEvent(144, 9, numKey, 100, i));
				track.add(makeEvent(128, 9, numKey, 100, i + 1));
			}
		}
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {			
		}
		return event;
	}
}