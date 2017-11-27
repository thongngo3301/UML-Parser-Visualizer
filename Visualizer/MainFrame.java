package Visualizer;

import javax.swing.*;

public class MainFrame extends JFrame {
	private static MainFrame window = new MainFrame();

	private MainFrame() {
            super("UML Visualizer");

            this.setJMenuBar(MenuBar.getMenuBarInstance());
            this.setSize(1400, 800);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
	}

	public static MainFrame getMainFrameInstance() {
		return window;
	}
}
