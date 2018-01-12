import javax.swing.*;
import java.awt.event.*;
// import java.io.*;
import java.awt.*;

public class DiceService implements Service {

	BorderLayout layout;
	JPanel panel;
	JLabel label;
	JComboBox numOfDice;

	// Here's the one important method! The method of the
	// Service interface--the one the client's gonna call when
	// this service is selected and loaded. You can do whatever
	// you want in the getGuiPanel() method, as long as you return
	// a JPanel, so it builds the actual dice-rolling GUI.
	public JPanel getGuiPanel() {
		layout = new BorderLayout();
		panel = new JPanel(layout);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box controlsBox = new Box(BoxLayout.X_AXIS);

		String[] choices = {"1", "2", "3", "4", "5"};
		numOfDice = new JComboBox(choices);
		controlsBox.add(numOfDice);

		JButton button = new JButton("Roll 'em!");
		button.addActionListener(new RollEmListener());
		controlsBox.add(button);

		label = new JLabel("dice values here");
		controlsBox.add(label);

		panel.add(BorderLayout.NORTH, controlsBox);
		return panel;
	}

	class RollEmListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// roll the dice
			DiceDrawPanel diceDrawPanel;
			int x = 20;
			int y = 20;
			String diceOutput = "";
			String selection = (String) numOfDice.getSelectedItem();
			int numOfDiceToRoll = Integer.parseInt(selection);
			for (int i = 0; i < numOfDiceToRoll; i++) {
				int r = (int) ((Math.random() * 6) + 1);
				diceOutput += (" " + r);
				diceDrawPanel = new DiceDrawPanel(x, y, 70, 70, r);
				panel.add(BorderLayout.SOUTH, diceDrawPanel);
				if (x < 180) {
					x += 80;
				} else {
					x = 20;
					y += 80;
				}
			}
			label.setText(diceOutput);
		}
	}

	// class for painting dices
	class DiceDrawPanel extends JPanel {

		private int x = 0;
		private int y = 0;
		private int wd = 0;
		private int ht = 0;
		private int nmbr = 0;

		public DiceDrawPanel() {
		}

		public DiceDrawPanel(int x, int y, int wd, int ht, int nmbr) {
			this.x = x;
			this.y = y;
			this.wd = wd;
			this.ht = ht;
			this.nmbr = nmbr;
		}

		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLUE);
			g2.fillRect(x, y, wd, ht);
			//g2.fillRect( 20,  20, 70, 70);
			//g2.fillRect(100,  20, 70, 70);
			//g2.fillRect(180,  20, 70, 70);
			//g2.fillRect( 20, 100, 70, 70);
			//g2.fillRect(100, 100, 70, 70);

			g2.setColor(Color.WHITE);
			g2.fillOval(x + 15, y + 15, 10, 10);
			//g2.fillOval(35, 35, 10, 10);
			//g2.fillOval(65, 35, 10, 10);
			//g2.fillOval(35, 65, 10, 10);
			//g2.fillOval(65, 65, 10, 10);
			//g2.fillOval(50, 50, 10, 10);
		}
	}
}