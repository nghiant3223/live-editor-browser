package visitor;

import javafx.scene.layout.Pane;
import org.jsoup.nodes.Node;

public interface Visitor {
    void visit(Node node, Pane parentPane);
}
