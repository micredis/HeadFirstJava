import javax.swing.*;
import java.awt.*;

public class Panel1 {

	public static void main(String[] args) {
		Panel1 gui = new Panel1();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame("Layouts testing");
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Font bigFont = new Font("serif", Font.BOLD, 28);
		JButton bigButton = new JButton("Click This!");
		bigButton.setFont(bigFont);
		frame.getContentPane().add(BorderLayout.NORTH, bigButton);

		JButton button = new JButton("shock me");
		JButton buttonTwo = new JButton("bliss");
		JButton buttonThree = new JButton("huh?");
		panel.add(button);
		panel.add(buttonTwo);
		panel.add(buttonThree);

		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.setSize(250, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}