package Visualizer;

import Parser.DataProject;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreeView extends JScrollPane {
    private int classesCounter;
    
    public TreeView() {
        this.init(new DefaultMutableTreeNode("Empty"));
    }
    
    public void draw(DataProject data) {
        try {
            RootNode node = new RootNode(data);
            classesCounter = node.getChildNodesCounter();
            this.init(node);
        } catch (Exception ex) {
            Logger.getLogger(TreeView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getClassesCounter(){
        return classesCounter;
    }
    
    private void init(DefaultMutableTreeNode node) {
        JTree tree = new JTree(node);
        
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);

        setViewportView(tree);
        setPreferredSize(new Dimension(180, 600));
    }
}