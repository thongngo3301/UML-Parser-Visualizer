package Visualizer;

import javax.swing.*;

public class MainFrame extends JFrame {
	private static final MainFrame window = new MainFrame();

	private MainFrame() {
            super("UML Visualizer");
            
            this.setJMenuBar(MenuBar.getMenuBarInstance());
            this.setSize(800, 600);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
	}

	public static MainFrame getMainFrameInstance() {
		return window;
	}
}
