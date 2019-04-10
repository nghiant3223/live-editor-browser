package adapter;

import javafx.scene.layout.Pane;
import org.jsoup.nodes.TextNode;
import visitor.Visitor;

import java.util.HashMap;

public class TextNodeAdapter implements VisitableAdapter {
    private TextNode textNode;

    public TextNodeAdapter(TextNode textNode) {
        this.textNode = textNode;
    }

    @Override
    public void accept(Visitor visitor, Pane parentPane, HashMap<String, String> inheritedStyle) {
        visitor.visit(textNode, parentPane, inheritedStyle);
    }
}
