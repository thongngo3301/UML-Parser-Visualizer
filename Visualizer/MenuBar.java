package Visualizer;

import Parser.DataProject;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuBar extends JMenuBar {
    private static final MenuBar menu = new MenuBar();
    
    private final JMenu fileMenu = new JMenu("File");

    private final JMenuItem openProject = new JMenuItem("Open...");
    
    private MenuBar() {
        openProject.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser(new java.io.File("."));
            
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            
            if (chooser.showOpenDialog(AppEntry.getMainFrame()) == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                String path = f.getAbsolutePath();
                
                AppEntry.getMainFrame().remove(AppEntry.getTreeView());
                try {
                    AppEntry.setDataProject(new DataProject(path));
                } catch (Exception ex) {
                    Logger.getLogger(MenuBar.class.getName()).log(Level.SEVERE, null, ex);
                }
                AppEntry.getTreeView().draw(AppEntry.getDataProject());
                AppEntry.getMainFrame().add(AppEntry.getTreeView(), BorderLayout.WEST);
                AppEntry.getTextArea().append("Loaded " + AppEntry.getTreeView().getClassesCounter()
                        + " file(s) from " + path + "\n");
                AppEntry.getMainVisualizingPanel().draw(AppEntry.getDataProject());
                AppEntry.getMainFrame().revalidate();
                AppEntry.getMainFrame().repaint();
            }
        });
        
        fileMenu.add(openProject);

        this.add(fileMenu);
    }

    public static MenuBar getMenuBarInstance() {
        return menu;
    }
}