package gui;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;

public class BPButton extends JButton {
	private static final long serialVersionUID = 5682343925497243410L;
	
	public BPButton(String text) {
		super(text);
		customize(false);
	}
	
	public BPButton(String text, boolean big) {
		super(text);
		customize(big);
	}
	
	private void customize(boolean big) {
		if(big)
			setMargin(new Insets(30, 35, 30, 35));
		else
			setMargin(new Insets(10, 15, 10, 15));
		setBackground(Color.GRAY);
		setForeground(Color.WHITE);
	}

}
