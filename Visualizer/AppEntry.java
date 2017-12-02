package Visualizer;

import javax.swing.*;
import java.awt.*;

public class AppEntry {
    private static MainFrame mainFrame;
    private static TreeView treeView;
    private static JTextArea textArea;
    private static MainVisualizingPanel mainVisualizingPanel;
    
    public static MainFrame getMainFrame() {
        return AppEntry.mainFrame;
    }
    
    public static void setTreeView(TreeView treeView) {
        AppEntry.treeView = treeView;
    }
    
    public static TreeView getTreeView() {
        return AppEntry.treeView;
    }
    
    public static JTextArea getTextArea() {
        return AppEntry.textArea;
    }
    
    public static void init() {
        mainFrame = MainFrame.getMainFrameInstance();
        treeView = new TreeView();
        mainVisualizingPanel = new MainVisualizingPanel();
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(800, 30));
        
        mainFrame.add(treeView, BorderLayout.WEST);
        mainFrame.add(mainVisualizingPanel, BorderLayout.CENTER);
        mainFrame.add(textArea, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        AppEntry.init();
    }
}