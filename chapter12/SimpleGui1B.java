import javax.swing.*;
import java.awt.event.*;

public class SimpleGui1B implements ActionListener {
	JButton button;
	MyDrawPanel figures;

	public static void main(String[] args) {
		SimpleGui1B gui = new SimpleGui1B();
		gui.go();
		gui.goo();
	}

	public void go() {
		JFrame frame = new JFrame();
		button = new JButton("click me");

		button.addActionListener(this);

		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	public void goo() {
		JFrame frame = new JFrame();
		figures = new MyDrawPanel();

		frame.getContentPane().add(figures);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		button.setText("I've been clicked!");
	}
}