import javax.sound.midi.*;
// import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniMusicService implements Service {
	
	MyDrawPanel myPanel;

	// The service method! All it does is display a button
	// and the drawing service (where the rectangles will
	// eventually be painted):
	public JPanel getGuiPanel() {
		JPanel mainPanel = new JPanel();
		myPanel = new MyDrawPanel();
		JButton playItButton = new JButton("Play it");
		playItButton.addActionListener(new PlayItListener());
		mainPanel.add(myPanel);
		mainPanel.add(playItButton);
		return mainPanel;
	}

	public class PlayItListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				Sequencer sequencer = MidiSystem.getSequencer();
				sequencer.open();
				sequencer.addControllerEventListener(myPanel, new int[] {127});
				Sequence seq = new Sequence(Sequence.PPQ, 4);
				Track track = seq.createTrack();

				for (int i = 0; i < 100; i += 4) {
					int rNum = (int) (Math.random() * (80 - 30) + 30); // pitch
					int freq = (int) (Math.random() * 100); // how often should notes be played
					if (freq < 75) {	// so now only do it 75% of the time
						track.add(makeEvent(ShortMessage.CONTROL_CHANGE, 1, 127, 0, i)); // track.add(makeEvent(144, 1, rNum, 100, i));
						track.add(makeEvent(ShortMessage.NOTE_ON, 1, rNum, 100, i)); // track.add(makeEvent(176, 1, 127, 0, i));
						track.add(makeEvent(ShortMessage.NOTE_OFF, 1, rNum, 100, i + 2)); // track.add(makeEvent(128, 1, rNum, 100, i + 2));
					}
				}

				sequencer.setSequence(seq);
				sequencer.start();
				sequencer.setTempoInBPM(220);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	} // close inner class

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

	class MyDrawPanel extends JPanel implements ControllerEventListener {
		// only if we got an event do we want to paint
		boolean msg = false;

		public void controlChange(ShortMessage event) {
			msg = true;
			repaint();
		}

		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}

		public void paintComponent(Graphics g) {
			if (msg) {
				Graphics2D g2 = (Graphics2D) g;

				int r = (int) (Math.random() * 250);
				int gr = (int) (Math.random() * 250);
				int b = (int) (Math.random() * 250);

				g.setColor(new Color(r, gr, b));

				int ht = (int) ((Math.random() * 120) + 10);
				int width = (int) ((Math.random() * 120) + 10);

				int x = (int) ((Math.random() * 170) + 10);
				int y = (int) ((Math.random() * 170) + 10);

				g.fillRect(x, y, ht, width);
				msg = false;
			}
		}
	} // close inner class
}