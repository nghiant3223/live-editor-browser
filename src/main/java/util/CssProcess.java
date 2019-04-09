package util;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class CssProcess {
    public static HashMap<String, String> assignCssProperty(Node node, HashMap<String, String> style) {
        HashMap<String, String> legacyStyle = new HashMap<>();

        for (Map.Entry<String, String> entry : style.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (node instanceof Text) {
                switch (key) {
                    case "color":
                        node.setStyle("-fx-fill: " + value);
                        break;
                    case "font-weight":
                        node.setStyle("-fx-" + key + ": " + value);
                        break;
                }
            } else if (node instanceof Pane) {
                switch (key) {
                    case "color":
                    case "font-weight":
                    case "font-size":
                        legacyStyle.put(key, value);
                        break;
                    case "padding":
                        node.setStyle("-fx-padding: " + value + " " + value + " " + value + " " + value);
                        break;
                    case "background-color":
                    case "border-width":
                    case "border-color":
                    case "border-style":
                        node.setStyle("-fx-" + key + ": " + value);
                        break;
                }
            }
        }

        return legacyStyle;
    }
}
