package util;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class CssProcess {
    public static void assignCssProperty(Node node, HashMap<String, String> style) {
        for (Map.Entry<String, String> entry : style.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (node instanceof Label) {
                switch (key) {
                    case "color":
                        node.setStyle("-fx-text-fill: " + value);
                        break;
                    case "font-weight":
                        node.setStyle("-fx-" + key + ": " + value);
                        break;
                }
            } else if (node instanceof Pane) {
                switch (key) {
                    case "background-color":
                    case "padding":
                    case "border-width":
                    case "border-color":
                    case "border-style":
                        node.setStyle("-fx-" + key + ": " + value);
                }
            }
        }
    }
}
