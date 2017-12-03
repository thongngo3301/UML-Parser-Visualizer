package Visualizer;

import Parser.DataProject;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuBar extends JMenuBar {
    private static final MenuBar menu = new MenuBar();
    
    private final JMenu mainMenu;
    
    private MenuBar() {
        mainMenu = new JMenu("File");
        this.add(createdMainMenu(mainMenu));
    }
    
    private JMenu createdMainMenu(JMenu menu) {
        menu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem openProjectItem = new JMenuItem("Open Project...");
        openProjectItem = createdOpenProjectItem(openProjectItem);
        menu.add(openProjectItem);
        
        menu.addSeparator();
        
        JMenuItem exitAppItem = new JMenuItem("Exit");
        exitAppItem = createdExitAppItem(exitAppItem);
        menu.add(exitAppItem);
        
        return menu;
    }
    
    private JMenuItem createdOpenProjectItem(JMenuItem item) {
        item.setMnemonic(KeyEvent.VK_O);
        KeyStroke openProjectItemShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        item.setAccelerator(openProjectItemShortcut);
        this.handleOpenProjectItem(item);
        
        return item;
    }
    
    private void handleOpenProjectItem(JMenuItem item) {
        item.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            
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
    }
    
    private JMenuItem createdExitAppItem(JMenuItem item) {
        item.setMnemonic(KeyEvent.VK_X);
        this.handleExitAppItem(item);
        
        return item;
    }
    
    private void handleExitAppItem(JMenuItem item) {
        item.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }
    
    public static MenuBar getMenuBarInstance() {
        return menu;
    }
}