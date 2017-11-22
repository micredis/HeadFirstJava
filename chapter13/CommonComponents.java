import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class CommonComponents {
	
	JFrame frame;
	JPanel panel;
	JLabel label;
	JTextField edit;
	JTextArea memo;
	JScrollPane memoScroll;
	JButton button;
	JCheckBox checkBox;
	JList list;
	JScrollPane listScroll;

	public static void main(String[] args) {
		CommonComponents gui = new CommonComponents();
		gui.go();
	}

	public void go() {
		frame = new JFrame("Testing Components");
		panel = new JPanel();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label = new JLabel("Dog's first name:");
		panel.add(label);

		edit = new JTextField(20);
		edit.setText("Frodo");
		panel.add(edit);
		edit.selectAll();

		memo = new JTextArea(10, 20);
		memoScroll = new JScrollPane(memo);
		memo.setLineWrap(true);
		memoScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		memoScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(memoScroll);
		memo.setText("Not all who are lost are wandering");

		String[] listEntries = {"alpha", "beta", "gamma", "delta",
								"epsilon", "zeta", "eta", "theta"};
		list = new JList(listEntries);
		listScroll = new JScrollPane(list);
		listScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(listScroll);
		list.setVisibleRowCount(4);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListListener());

		button = new JButton("add text");
		panel.add(button);
		button.addActionListener(new ButtonListener());

		checkBox = new JCheckBox("Check me!");
		panel.add(checkBox);
		//checkBox.setSelected(true);
		checkBox.addItemListener(new CheckBoxListener());

		frame.getContentPane().add(panel);
		frame.setSize(400, 250);
		frame.setVisible(true);
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			memo.append("button clicked\n");
		}
	}

	class CheckBoxListener implements ItemListener {
		public void itemStateChanged(ItemEvent ie) {
			if (checkBox.isSelected()) {
				memo.append("checkbox selected\n");
			} else {
				memo.append("checkbox unselected\n");
			}
		}
	}

	class ListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lse) {
			if (!lse.getValueIsAdjusting()) {
				String selection = (String) list.getSelectedValue();
				memo.append(selection.substring(0, 1).toUpperCase() + selection.substring(1) + "!\n");
			}
		}
	}

}