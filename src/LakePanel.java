import javax.swing.*;
import java.awt.GridLayout;

public class LakePanel extends JPanel {
    private Graph graph;

    //Constructors
    public LakePanel() {
        this(new Graph());
    }

    public LakePanel(Graph graph) {
        this.graph = graph;
    }

    //Returns a copy of the graph in the lake panel
    public Graph graph() {
        return this.graph;
    }

    //Displays the graph of the panel in graphics form

}
