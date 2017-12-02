package Visualizer;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuBar extends JMenuBar {
    private static MenuBar menu = new MenuBar();
    
    private JMenu fileMenu = new JMenu("File");

    private JMenuItem openProject = new JMenuItem("Open...");
    
    private MenuBar() {
        openProject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(new java.io.File("."));

                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                if (chooser.showOpenDialog(AppEntry.getMainFrame()) == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    String selectedPath = f.getAbsolutePath();
                    
                    AppEntry.getMainFrame().remove(AppEntry.getTreeView());
                    AppEntry.setTreeView(new TreeView(selectedPath));
                    AppEntry.getMainFrame().add(AppEntry.getTreeView(), BorderLayout.WEST);
                    AppEntry.getTextArea().append("Loaded " + AppEntry.getTreeView().getClassesCounter()
                                                         + " file(s) from " + selectedPath + "\n");
                    AppEntry.getMainFrame().revalidate();
                    AppEntry.getMainFrame().repaint();
                }
            }
        });
        
        fileMenu.add(openProject);

        this.add(fileMenu);
    }

    public static MenuBar getMenuBarInstance() {
        return menu;
    }
}