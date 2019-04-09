package visitor;

import javafx.scene.layout.Pane;
import org.jsoup.nodes.Node;

import java.util.HashMap;

public interface Visitor {
    void visit(Node node, Pane parentPane, HashMap<String, String> inheritedStyle);
}
