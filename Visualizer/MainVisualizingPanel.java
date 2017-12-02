package Visualizer;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import com.mindfusion.drawing.*;
import com.mindfusion.diagramming.*;
import Parser.*;
import java.util.ArrayList;

public class MainVisualizingPanel extends JScrollPane {
    private Diagram diagram;
    private DiagramView diagramView;
    
    public void draw(DataProject data) {
        diagram = new Diagram();
        diagramView = new DiagramView(diagram);
        
        setViewportView(diagramView);
      
        generate(data);
        
        TreeLayout layout = new TreeLayout();
        layout.setLinkStyle(TreeLayoutLinkType.Straight);
        layout.setLevelDistance(50);
        layout.setNodeDistance(50);
        layout.arrange(diagram);
        
        diagramView.setBehavior(Behavior.PanAndModify);
	diagramView.setModificationStart(ModificationStart.AutoHandles);
        
        diagram.resizeToFitItems(10);
        diagram.setAutoResize(AutoResize.AllDirections);
    }
    
    private void generate(DataProject data) {
        diagram.clearAll();
        for (DataClass aClass : data.getDataClasses()) {
            TableNode node = diagram.getFactory().createTableNode(0, 15, 0, 0);
            
            node.redimTable(1, 0);
            node.setCellFrameStyle(CellFrameStyle.None);
            
            createTitle(node, aClass.getNameClass());
            
            createContent(node, aClass);
            
            node.resizeToFitText(true);
            
            diagram.add(node);
        }
    }
    
    private void createTitle(TableNode node, String title) {
        node.setCaption(title);
        node.setCaptionHeight(10);
        node.setFont(new Font("Arial", Font.BOLD, 5));
    }
    
    private void createContent(TableNode node, DataClass classContent) {
        Cell cell = node.getCell(0, node.addRow());
        styleClassMember(cell, "Attributes");
        for (DataAttribute attribute : classContent.getDataAttributeClasses()) {
            styleMemberProperties(node, attribute.getNameAttribute());
        }
        cell = node.getCell(0, node.addRow());
        styleClassMember(cell, "Methods");
        for (DataMethod method : classContent.getDataMethodClasses()) {
            styleMemberProperties(node, method.getNameMethod());
        }
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