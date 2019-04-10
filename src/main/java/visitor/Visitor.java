package visitor;

import javafx.scene.layout.Pane;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

import java.util.HashMap;

public interface Visitor {
    void visit(Element node, Pane parentPane, HashMap<String, String> inheritedStyle);
    void visit(TextNode node, Pane parentPane, HashMap<String, String> inheritedStyle);
}
