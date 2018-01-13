import javax.swing.*;
import java.awt.event.*;
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
			String diceOutput = "";
			String selection = (String) numOfDice.getSelectedItem();
			int numOfDiceToRoll = Integer.parseInt(selection);
			int[] dices = new int[numOfDiceToRoll];
			for (int i = 0; i < numOfDiceToRoll; i++) {
				int r = (int) ((Math.random() * 6) + 1);
				dices[i] = r;
				diceOutput += (" " + r);
			}
			label.setText(diceOutput);
			DiceDrawPanel diceDrawPanel = new DiceDrawPanel(20, 90, 70, 70, dices);
			panel.add(BorderLayout.SOUTH, diceDrawPanel);
		}
	}

	// class for painting dices
	class DiceDrawPanel extends JPanel {

		private int x = 0;  // x position of the first (upper leftmost) dice
		private int y = 0;  // y position of the first (upper leftmost) dice
		private int wd = 0; // width of any dice
		private int ht = 0; // height of any dice
		private int[] dices = null;

		public DiceDrawPanel(int x, int y, int wd, int ht, int[] dices) {
			this.x = x;
			this.y = y;
			this.wd = wd;
			this.ht = ht;
			this.dices = dices;
		}

		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;

			// return a background color for clearing a region
			// from a previous tossing results
			g2.setColor(g2.getBackground());
			g2.fillRect(0, 0, 300, 300);

			for (int i = 0; i < dices.length; i++) {
				// paint blue square
				g2.setColor(Color.BLUE);
				g2.fillRect(x, y, wd, ht);

				// paint white dot(s)
				g2.setColor(Color.WHITE);
				switch(dices[i]) {
					case 1: g2.fillOval(x + 30, y + 30, 10, 10);
							break;
					case 2: g2.fillOval(x + 15, y + 15, 10, 10);
							g2.fillOval(x + 45, y + 45, 10, 10);
							break;
					case 3: g2.fillOval(x + 15, y + 15, 10, 10);
							g2.fillOval(x + 30, y + 30, 10, 10);
							g2.fillOval(x + 45, y + 45, 10, 10);
							break;
					case 4: g2.fillOval(x + 15, y + 15, 10, 10);
							g2.fillOval(x + 45, y + 15, 10, 10);
							g2.fillOval(x + 15, y + 45, 10, 10);
							g2.fillOval(x + 45, y + 45, 10, 10);
							break;
					case 5: g2.fillOval(x + 15, y + 15, 10, 10);
							g2.fillOval(x + 45, y + 15, 10, 10);
							g2.fillOval(x + 15, y + 45, 10, 10);
							g2.fillOval(x + 45, y + 45, 10, 10);
							g2.fillOval(x + 30, y + 30, 10, 10);
							break;
					case 6: g2.fillOval(x + 15, y + 15, 10, 10);
							g2.fillOval(x + 30, y + 15, 10, 10);
							g2.fillOval(x + 45, y + 15, 10, 10);
							g2.fillOval(x + 15, y + 45, 10, 10);
							g2.fillOval(x + 30, y + 45, 10, 10);
							g2.fillOval(x + 45, y + 45, 10, 10);
							break;
					default:break;
				}

				// find coordinates where to put the next square
				if (x < 170) {
					x += 80;
				} else {
					x = 20;
					y += 80;
				}
			}
		}
	}
}