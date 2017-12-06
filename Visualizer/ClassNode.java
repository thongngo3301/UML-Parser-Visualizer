package Visualizer;

import Parser.DataClass;
import javax.swing.tree.DefaultMutableTreeNode;

public class ClassNode extends DefaultMutableTreeNode {

    public ClassNode(DataClass currClass) {
        super(currClass.getNameClass());
    }
}
