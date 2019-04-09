package visitor;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class ConcreteVisitor implements Visitor {
    @Override
    public void visit(Node node, Pane parentPane) {
        for (Node childNode : node.childNodes()) {
            if (childNode instanceof Element) {
                switch (((Element) childNode).tagName()) {
                    case "div":
                        VBox div = new VBox(0);

                        visit(childNode, div);
                        div.setStyle("-fx-border-width: 5px");
                        div.setStyle("-fx-border-color: black;");

                        parentPane.getChildren().add(div);
                        break;

                    case "h1":
                        VBox h1 = new VBox(0);

                        visit(childNode, h1);
                        h1.setStyle("-fx-border-width: 5px");
                        h1.setStyle("-fx-border-color: blue;");

                        parentPane.getChildren().add(h1);
                        break;
                }
            } else if (childNode instanceof TextNode) {
                String text = ((TextNode) childNode).text();
                Label label = new Label(text);
                parentPane.getChildren().add(label);
            }
        }
    }
}
