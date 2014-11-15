package gui;

import java.awt.*;

import javax.swing.*;
 
 
public class MyFrame {
 
	public static final void main(final String[] args) throws Exception {
 		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
 
 		final JFrame fullscreenFrame = new JFrame();
 		fullscreenFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 		fullscreenFrame.setUndecorated(true);
 		fullscreenFrame.setResizable(false);
 		fullscreenFrame.add(new JLabel("Press ALT+F4 to exit fullscreen.", SwingConstants.CENTER), BorderLayout.CENTER);
 		fullscreenFrame.validate();
 
 		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(fullscreenFrame);
 	}
    
}
