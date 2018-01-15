// Test of Anonymous class

import java.awt.event.*;
import javax.swing.*;

public class TestAnon {
	JFrame frame;
	JButton button;

	public static void main(String[] args) {
		new TestAnon().go();
	}

	public void go() {
		// We made a frame and added a button, and now we
		// need to register an action listener with the button.
		// Except we never made a class that implements the
		// ActionListener interface...
		frame = new JFrame();
		button = new JButton("click");
		frame.getContentPane().add(button);
		// Normally we'd do something like this--passing in
		// a reference to an instance of an inner class... an
		// inner class that implements ActionListener (and
		// the actionPerformed() method):
		// button.addActionListener(quitListener);

		// But now instead of passing in an object
		// reference, we pass in _the whole new class
		// definition_. In other words, we write the
		// class that implements ActionListener
		// *right here where we need it*.
		// The syntax also creates an instance
		// of the class automatically:
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		// Notice that we say "new ActionListener()" even
		// though ActionListener is and interface and so you
		// can't make an instance of it! But this syntax
		// really means, "create a new class (with no name)
		// that implements the ActionListener interface,
		// and by the way, here's the implementation of
		// the interface method .actionPerformed()".
	}
}