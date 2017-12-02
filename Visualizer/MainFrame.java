package Visualizer;

import javax.swing.*;

public class MainFrame extends JFrame {
	private static final MainFrame window = new MainFrame();

	private MainFrame() {
            super("UML Visualizer");
            
            setJMenuBar(MenuBar.getMenuBarInstance());
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
	}

	public static MainFrame getMainFrameInstance() {
		return window;
	}
}
