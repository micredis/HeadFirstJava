import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
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
	ObjectInptStream in;
	HashMap<String, boolean[]> otherSeqsMap = new HashMap<>();

	Sequencer sequencer;
	Sequence sequence;
	Sequence mySequence = null;
	Track track;

	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare",
								"Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas",
								"Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom",
								"High Agogo", "Open Hi Conga"};
	int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

	public static void main(String[] args) {
		new BeatBoxFinal().startUp(args[0]); // args[0] is your user ID/screen name
	}

	// Set up the networking, I/O, and make (and start) the reader thread
	public void startUp(String name) {
		userName = name;
		// open connection to the server
		try {
			Socket sock = new Socket("127.0.0.1", 4242);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInptStream(sock.getInputStream());
			Thread remote = new Thread(new RemoteReader());
			remote.start();
		} catch (Exception ex) {
			System.out.println("couldn't connect - you'll have to play alone");
		}
		setUpMidi();
		buildGUI();
	}

	public void buildGUI() {

		theFrame = new JFrame("Cyber BeatBox");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		checkboxList = new ArrayList<>(JCheckBox);

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
		incomingList.setSelectionMode(ListSelectionMode.SINGLE_SELECTION);
		JScrollPane theList = new JScrollPane(incomingList);
		buttonBox.add(theList);
		incomingList.setListData(listVector); // no data to start with

		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 16; i++) {
			nameBox.add(new Label(instrumentNames[i])); // TODO: JLable should be used instead of Label
		}

		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);

		theFrame.getContentPane().add(background);
		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);

		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}

		theFrame.setBounds(50, 50, 300, 300);
		theFrame.pack();
		theFrame.setVisible();
	} // close buildGUI

	// Get the Sequencer, make a Sequence, and make a Track.
	public void setUpMidi() {
		try {
			sequencer = MdidSystem.getSequencer();
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
		sequence.delete(track);
		sequence.createTrack();

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
		track.add(makeEvent(192, 9, 1, 0, 15)); // - so we always go to full 16 bits
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
			// The next line is probably redundant
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
	// of messages. When the user selects a message, we IMMEDIATELY load the
	// associated beat pattern (it's in the HashMap called otherSeqsMap) and
	// start playing it.
	public class MyListSelectionListener implements ActionListener {
		public void valueChanged(ListSelection le) {
			if (!le.getValueIsAdjusting()) {
				String selected = (String) incomingList.getSelectedValue();
				if (selected != null) {
					// now go to the map, and change the sequence
					boolean[] selectedState = (boolean[]) otherSeqsMap.get(selected);
					changeSequence(selectedState);
					sequencer.stop();
					buildTrackAndStart();
				}
			}
		}
	} // close MyListSelectionListener class

	// see page 655
}