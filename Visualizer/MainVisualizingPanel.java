package Visualizer;

import java.awt.*;
import javax.swing.*;
import com.mindfusion.drawing.*;
import com.mindfusion.diagramming.*;
import Parser.*;

public class MainVisualizingPanel extends JScrollPane {
    private Diagram diagram;
    private DiagramView diagramView;
    
    public void draw(DataProject data) {
        diagram = new Diagram();
        diagramView = new DiagramView(diagram);
        
        this.setViewportView(diagramView);
        
        this.generateNodes(data);
        
        TreeLayout layout = new TreeLayout();
        layout.arrange(diagram);
        
        diagramView.setBehavior(Behavior.PanAndModify);
	diagramView.setModificationStart(ModificationStart.AutoHandles);
        
        diagram.resizeToFitItems(5);
        diagram.setAutoResize(AutoResize.AllDirections);
    }
    
    private void generateNodes(DataProject data) {
        diagram.clearAll();
        data.getDataClasses().stream().map((DataClass aClass) -> {
            TableNode node = diagram.getFactory().createTableNode(10, 10, 50, 100);
            node.redimTable(1, 0);
            node.setCellFrameStyle(CellFrameStyle.None);
            this.createTitle(node, aClass.getNameClass());
            this.createContent(node, aClass);
            return node;
        }).map((TableNode node) -> {
            node.resizeToFitText(true);
            return node;
        }).forEachOrdered((node) -> {
            diagram.add(node);
        });
    }
    
    private void createTitle(TableNode node, String title) {
        node.setCaption(title);
        node.setCaptionHeight(10);
        node.setFont(new Font("Arial", Font.BOLD, 5));
    }
    
    private void createContent(TableNode node, DataClass classContent) {
        Cell cell = node.getCell(0, node.addRow());
        this.styleClassMember(cell, "Attributes");
        classContent.getDataAttributeClasses().forEach((attribute) -> {
            this.styleMemberProperties(node, attribute.toString());
        });
        cell = node.getCell(0, node.addRow());
        this.styleClassMember(cell, "Methods");
        classContent.getDataMethodClasses().forEach((method) -> {
            this.styleMemberProperties(node, method.toString());
        });
    }
    
    private void styleClassMember(Cell cell, String member) {
        cell.setTextColor(Color.white);
        cell.setFont(new Font("Arial", Font.BOLD, 4));
        cell.setText(member);
        cell.setTextFormat(new TextFormat(Align.Center, Align.Center));
        cell.setBrush(new SolidBrush(Color.GRAY));
    }
    
    private void styleMemberProperties(TableNode node, String prop) {
        Cell cell = node.getCell(0, node.addRow());
        cell.setFont(new Font("Arial", Font.PLAIN, 4));
        cell.setText(prop);
        cell.setTextFormat(new TextFormat(Align.Near, Align.Near));
        cell.setBrush(new SolidBrush(Color.WHITE));
    }
    
    
}