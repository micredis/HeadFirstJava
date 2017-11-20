import javax.swing.*;
import java.awt.*;

public class SimpleAnimation {

	JFrame frame;
	InnerDrawPanel ball;
	int x;
	int y;

	public static void main(String[] args) throws InterruptedException {
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}

	public void go() throws InterruptedException {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ball = new InnerDrawPanel();
		
		frame.getContentPane().add(ball);
		frame.setSize(600, 600);
		frame.setVisible(true);

		while (x < (frame.getSize().width - 100) && y < (frame.getSize().height - 100)) {
			frame.repaint();
			x++;
			y++;
			Thread.sleep(3);
		}
	}

	class InnerDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.ORANGE);
			g.fillOval(x, y, 100, 100);
		}
	}
}