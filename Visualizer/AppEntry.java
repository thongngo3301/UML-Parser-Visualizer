package Visualizer;

import Parser.DataProject;
import javax.swing.*;
import java.awt.*;

public class AppEntry {

    private static MainFrame mainFrame;
    private static TreeView treeView;
    private static JTextArea textArea;
    private static MainVisualizingPanel mainVisualizingPanel;
    private static DataProject project;

    public static void setMainFrame(MainFrame mainFrame) {
        AppEntry.mainFrame = mainFrame;
    }

    public static MainFrame getMainFrame() {
        return AppEntry.mainFrame;
    }

    public static void setTreeView(TreeView treeView) {
        AppEntry.treeView = treeView;
    }

    public static TreeView getTreeView() {
        return AppEntry.treeView;
    }

    public static void setTextArea(JTextArea textArea) {
        AppEntry.textArea = textArea;
    }

    public static JTextArea getTextArea() {
        return AppEntry.textArea;
    }

    public static void setMainVisualizingPanel(MainVisualizingPanel mainVisualizingPanel) {
        AppEntry.mainVisualizingPanel = mainVisualizingPanel;
    }

    public static MainVisualizingPanel getMainVisualizingPanel() {
        return AppEntry.mainVisualizingPanel;
    }

    public static void setDataProject(DataProject project) {
        AppEntry.project = project;
    }

    public static DataProject getDataProject() {
        return AppEntry.project;
    }

    public static void init() {
        mainFrame = MainFrame.getMainFrameInstance();
        treeView = new TreeView();
        mainVisualizingPanel = new MainVisualizingPanel();
        textArea = new JTextArea();
        textArea.setSize(new Dimension(800, 20));

        mainFrame.add(treeView, BorderLayout.WEST);
        mainFrame.add(mainVisualizingPanel, BorderLayout.CENTER);
        mainFrame.add(textArea, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        AppEntry.init();
    }
}
