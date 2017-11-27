package Visualizer;

import javax.swing.*;
import java.awt.*;

public class AppRun {
    private static MainFrame mainFrame;
    private static TreeView treeView;
    private static JTextArea textArea;
    private static MainVisualizer mainVisualizer;
    
    public static MainFrame getMainFrame() {
        return AppRun.mainFrame;
    }
    
    public static void setTreeView(TreeView treeView) {
        AppRun.treeView = treeView;
    }
    
    public static TreeView getTreeView() {
        return AppRun.treeView;
    }
    
    public static JTextArea getTextArea() {
        return AppRun.textArea;
    }
    
    public static void init() {
        mainFrame = MainFrame.getMainFrameInstance();
        treeView = new TreeView();
        mainVisualizer = new MainVisualizer();
        textArea = new JTextArea();
        
        textArea.setPreferredSize(new Dimension(1400, 30));
        
        mainFrame.add(treeView, BorderLayout.WEST);
        mainFrame.add(mainVisualizer, BorderLayout.CENTER);
        mainFrame.add(textArea, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        AppRun.init();
    }
}