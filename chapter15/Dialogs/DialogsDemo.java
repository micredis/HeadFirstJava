import javax.swing.*;
import java.io.*;

public class DialogsDemo {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Hey!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // is this line REDUNDANT?
		
		// information dialog (one button, default title and icon)
		JOptionPane.showMessageDialog(frame, "Coock-ckoo!");

		// information dialog (one button, custom title, warning icon)
		JOptionPane.showMessageDialog(frame, "I warn you!",
			"Nonsensical warning",
			JOptionPane.WARNING_MESSAGE); // it could alos be ERROR_MESSAGE, PLAIN_MESSAGE (no icon), INFORMATION_MESSAGE or QUESTION_MESSAGE

		// information dialog (one button, custom title, custom icon)
		final ImageIcon icon = new ImageIcon("175x175bb.png");
		JOptionPane.showMessageDialog(null, "See this custom icon?",
			"Custom icon",
			JOptionPane.INFORMATION_MESSAGE, icon);

		// 3-option dialog
		// with custom button text
		Object[] options = {"Yes, please", "No, thanks", "Something special"};
		int n = JOptionPane.showOptionDialog(frame,
			"Do you want me to cockoo or show a warning?",
			"Three options",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[2]);
	}
}