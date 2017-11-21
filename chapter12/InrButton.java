import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InrButton {

	JFrame frame;
	JButton b;

	public static void main(String[] args) {
		InrButton gui = new InrButton();
		gui.go();
	}

	public void go() {
		frame = new JFrame("Exercise: Fix the Class");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b = new JButton("A");
		b.addActionListener(new BListener());

		frame.getContentPane().add(BorderLayout.SOUTH, b);
		frame.setSize(200, 100);
		frame.setVisible(true);
	}

	class BListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (b.getText().equals("A")) {
				b.setText("B");
			} else {
				b.setText("A");
			}
		}
	}
}