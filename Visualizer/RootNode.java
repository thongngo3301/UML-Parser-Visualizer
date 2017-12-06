package Visualizer;

import Parser.DataProject;
import Parser.DataClass;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class RootNode extends DefaultMutableTreeNode {

    private final int childNodesCounter;

    public RootNode(DataProject data) {
        ArrayList<DataClass> classes = data.getDataClasses();
        childNodesCounter = classes.size();

        classes.forEach((dclass) -> {
            this.add(new ClassNode(dclass));
        });
    }

    public int getChildNodesCounter() {
        return childNodesCounter;
    }
}
