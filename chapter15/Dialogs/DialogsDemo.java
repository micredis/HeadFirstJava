import javax.swing.*;

public class DialogsDemo {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Hey!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // is this line REDUNDANT?
		JOptionPane.showMessageDialog(frame, "Coock-ckoo!");
	}
}