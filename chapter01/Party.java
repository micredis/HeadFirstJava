import java.awt.*;

import java.awt.event.*;

class Party {
	public void buildInvite() {

		Frame f = new Frame();
		Label l = new Label("Tim's Party");
		Button b = new Button("Your bet");
		Button c = new Button("Reset");
		Panel p = new Panel();
		p.add(l);

	} // other code

	public static void main(String[] args) {
		Party p = new Party();
		p.buildInvite();
	}

}