package Visualizer;

import java.awt.*;
import javax.swing.*;
import Parser.*;
import com.mindfusion.drawing.*;
import com.mindfusion.diagramming.*;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainVisualizingPanel extends JScrollPane {
    private Diagram diagram;
    private DiagramView diagramView;
    
    private final LinkedList<TableNode> nodeContainer;
    private final LinkedList<DataClass> classContainer;

    public MainVisualizingPanel() {
        this.nodeContainer = new LinkedList<TableNode>();
        this.classContainer = new LinkedList<DataClass>();
    }
    
    public void draw(DataProject data) {
        this.diagram = new Diagram();
        this.diagramView = new DiagramView(this.diagram);
        
        this.setViewportView(this.diagramView);
        
        this.generateNodes(data);
        
        this.generateLinks();
        this.diagramView.setBehavior(Behavior.PanAndModify);
	this.diagramView.setModificationStart(ModificationStart.AutoHandles);
        
        this.diagram.resizeToFitItems(5);
        this.diagram.setAutoResize(AutoResize.AllDirections);
        
        this.diagram.setLinkRouter(new QuickRouter(diagram));
        this.diagram.setLinkCrossings(LinkCrossings.Cut);
        
        this.zoom();
    }
    
    private void generateNodes(DataProject data) {
        this.diagram.clearAll();
        data.getDataClasses().stream().map((DataClass aClass) -> {
            this.classContainer.add(aClass);
            TableNode node = MainVisualizingPanel.this.diagram.getFactory().createTableNode(0, 0, 1, 1);
            node.setAllowResizeColumns(false);
            node.setAllowResizeRows(false);
            node.redimTable(1, 0);
            node.setCellFrameStyle(CellFrameStyle.None);
            MainVisualizingPanel.this.createTitle(node, aClass.getNameClass());
            MainVisualizingPanel.this.createContent(node, aClass);
            return node;
        }).map((TableNode node) -> {
            node.resizeToFitText(true);
            node.setShadowBrush(new SolidBrush(Color.WHITE));
            return node;
        }).forEachOrdered((TableNode node) -> {
            this.nodeContainer.add(node);
            this.diagram.add(node);
        });
    }
    
    private void generateLinks() {
        TableNode parentNode, childNode;
        for (int i = 0; i < this.nodeContainer.size(); i++) {
            childNode = this.nodeContainer.get(i);
            if (!this.classContainer.get(i).getSuperClass().equals("no")) {
                String _parentName = this.classContainer.get(i).getSuperClass();
                for (int j = 0; j < this.classContainer.size(); j++) {
                    if (this.classContainer.get(j).getNameClass().equals(_parentName)) {
                        parentNode = this.nodeContainer.get(j);
                        this.styleHeadShape(parentNode, childNode, "IS-A-extends");
                        break;
                    }
                }
            }
            if (this.classContainer.get(i).getInterfaceClasses().size() > 0) {
                ArrayList<String> interfaceClasses = this.classContainer.get(i).getInterfaceClasses();
                for (int j = 0; j < this.classContainer.size(); j++) {
                    if (interfaceClasses.contains(this.classContainer.get(j).getNameClass())) {
                        parentNode = this.nodeContainer.get(j);
                        this.styleHeadShape(parentNode, childNode, "IS-A-implements");
                    }
                }
            }
        }
        
        TreeLayout layout = new TreeLayout();
        layout.arrange(this.diagram);
        
        for (int i = 0; i < this.nodeContainer.size(); i++) {
            parentNode = this.nodeContainer.get(i);
            if (this.classContainer.get(i).getDataHasAClasses().size() > 0) {
                ArrayList<String> dataHasAClasses = this.classContainer.get(i).getDataHasAClasses();
                for (int j = 0; j < this.classContainer.size(); j++) {
                    if (dataHasAClasses.contains(this.classContainer.get(j).getNameClass())) {
                        childNode = this.nodeContainer.get(j);
                        this.styleHeadShape(parentNode, childNode, "HAS-A");
                    }
                }
            }
        }
    }
    
    private void zoom() {
        this.diagramView.addMouseWheelListener((MouseWheelEvent e) -> {
            int wheelRotation = e.getWheelRotation();
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                float zoomFactor = diagramView.getZoomFactor();
                if (zoomFactor <= 30 && wheelRotation > 0) return;
                diagramView.setZoomFactor(zoomFactor - wheelRotation);
            }
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
            this.styleMemberProperties(node, attribute.toString().trim());
        });
        cell = node.getCell(0, node.addRow());
        this.styleClassMember(cell, "Methods");
        classContent.getDataMethodClasses().forEach((method) -> {
            this.styleMemberProperties(node, method.toString().trim());
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
        cell.setBrush(new SolidBrush(Color.WHITE));
    }
    
    private void styleHeadShape(TableNode parentNode, TableNode childNode, String relationship) {
        DiagramLink link = this.diagram.getFactory().createDiagramLink(parentNode, childNode);
        switch(relationship) {
            case "IS-A-extends":
                link.setBaseShape(ArrowHeads.Triangle);
                link.setHeadShape(ArrowHeads.None);
                link.setShadowBrush(new SolidBrush(Color.WHITE));
                break;
            case "IS-A-implements":
//                link.getPen().setDashStyle(DashStyle.Dash);
                link.setBaseShape(ArrowHeads.Arrow);
                link.setHeadShape(ArrowHeads.None);
                link.setShadowBrush(new SolidBrush(Color.WHITE));
                break;
            case "HAS-A":
                link.setAutoRoute(true);
                link.setBaseShape(ArrowHeads.Rhombus);
                link.setHeadShape(ArrowHeads.None);
                link.setShadowBrush(new SolidBrush(Color.WHITE));
                break;
            default:
                break;
        }
    }
}