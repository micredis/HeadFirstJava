import javax.swing.*;
import java.awt.event.*;
// import java.io.*;
import java.awt.*;

public class DiceService implements Service {

	JLabel label;
	JComboBox numOfDice;
	DiceDrawPanel diceDrawPanel;

	// Here's the one important method! The method of the
	// Service interface--the one the client's gonna call when
	// this service is selected and loaded. You can do whatever
	// you want in the getGuiPanel() method, as long as you return
	// a JPanel, so it builds the actual dice-rolling GUI.
	public JPanel getGuiPanel() {
		JPanel panel = new JPanel();
		JButton button = new JButton("Roll 'em!");
		String[] choices = {"1", "2", "3", "4", "5"};
		numOfDice = new JComboBox(choices);
		label = new JLabel("dice values here");
		button.addActionListener(new RollEmListener());
		diceDrawPanel = new DiceDrawPanel();
		panel.add(numOfDice);
		panel.add(button);
		panel.add(label);
		panel.add(BorderLayout.SOUTH, diceDrawPanel);
		return panel;
	}

	class RollEmListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// roll the dice
			String diceOutput = "";
			String selection = (String) numOfDice.getSelectedItem();
			int numOfDiceToRoll = Integer.parseInt(selection);
			for (int i = 0; i < numOfDiceToRoll; i++) {
				int r = (int) ((Math.random() * 6) + 1);
				diceOutput += (" " + r);
			}
			label.setText(diceOutput);
		}
	}

	// class for painting dices
	class DiceDrawPanel extends JPanel {
		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.drawRect(70, 70, 100, 100);
		}
	}
}