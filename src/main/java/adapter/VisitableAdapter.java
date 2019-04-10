package adapter;

import javafx.scene.layout.Pane;
import visitor.Visitor;

import java.util.HashMap;

public interface VisitableAdapter {
    void accept(Visitor visitor, Pane parentPane, HashMap<String, String> inheritedStyle);
}
