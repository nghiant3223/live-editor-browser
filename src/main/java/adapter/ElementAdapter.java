package adapter;

import javafx.scene.layout.Pane;
import org.jsoup.nodes.Element;
import visitor.Visitor;

import java.util.HashMap;

public class ElementAdapter implements VisitableAdapter {
    private Element element;

    public ElementAdapter(Element element) {
        this.element = element;
    }

    @Override
    public void accept(Visitor visitor, Pane parentPane, HashMap<String, String> inheritedStyle) {
        visitor.visit(element, parentPane, inheritedStyle);
    }
}
