import javax.swing.*;
import java.awt.*;

public class MyDrawPanel extends JPanel {

	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);

		Color randomColor = new Color(red, green, blue);
		g.setColor(randomColor);
		g.fillOval(100, 5, 90, 90);

		g.setColor(Color.ORANGE);
		g.fillRect(20, 50, 100, 100);

		Image image = new ImageIcon("lab511a.jpg").getImage();
		g.drawImage(image, 120, 150, this);

		Graphics2D g2d = (Graphics2D) g;

		red = (int) (Math.random() * 255);
		green = (int) (Math.random() * 255);
		blue = (int) (Math.random() * 255);

		Color endColor = new Color(red, green, blue);

		GradientPaint gradient = new GradientPaint(70, 70, randomColor, 150, 150, endColor);
		g2d.setPaint(gradient);
		g2d.fillOval(70, 70, 100, 100);
	}
}