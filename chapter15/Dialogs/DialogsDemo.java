import javax.swing.*;
import java.io.*;

public class DialogsDemo {
	public static void main(String[] args) {
		//JFrame frame = new JFrame("Hey!");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // is this line REDUNDANT?

		// instead of "null" there could be "frame" everywhere
		// (in that case a frame must be instantiated (see the 1st line))

		// 3-option dialog
		// with custom button text
		Object[] options = {"Yes, please", "No, thanks", "Something special"};
		int optionNumber = JOptionPane.showOptionDialog(null,
			"Do you want me to cockoo (or else I'll show a warning)?",
			"Three options",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[2]);

		if (optionNumber == 0) {
			// information dialog (one button, default title and icon)
			JOptionPane.showMessageDialog(null, "Coock-ckoo!");
		}
		if (optionNumber == 1) {
			// information dialog (one button, custom title, warning icon)
			JOptionPane.showMessageDialog(null, "I warn you!",
				"Nonsensical warning",
				JOptionPane.WARNING_MESSAGE); // it could alos be ERROR_MESSAGE, PLAIN_MESSAGE (no icon), INFORMATION_MESSAGE or QUESTION_MESSAGE
		}
		if (optionNumber == 2) {
			// information dialog (one button, custom title, custom icon)
			final ImageIcon icon = new ImageIcon("175x175bb.png");
			JOptionPane.showMessageDialog(null, "See this custom icon?",
				"Custom icon",
				JOptionPane.INFORMATION_MESSAGE, icon);
		}
	}
}