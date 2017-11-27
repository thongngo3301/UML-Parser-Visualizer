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

                if (chooser.showOpenDialog(AppRun.getMainFrame()) == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    String selectedPath = f.getAbsolutePath();
                    
                    AppRun.getMainFrame().remove(AppRun.getTreeView());
                    AppRun.setTreeView(new TreeView(selectedPath));
                    AppRun.getMainFrame().add(AppRun.getTreeView(), BorderLayout.WEST);
                    AppRun.getTextArea().append("Loaded " + AppRun.getTreeView().getClassesCounter()
                                                         + " file(s) from " + selectedPath + "\n");
                    AppRun.getMainFrame().revalidate();
                    AppRun.getMainFrame().repaint();
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