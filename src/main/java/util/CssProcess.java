package util;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class CssProcess {
    public static HashMap<String, String> assignCssProperty(Node node, HashMap<String, String> style, StringBuilder nodeStyleToSet) {
        HashMap<String, String> legacyStyle = new HashMap<>();

        for (Map.Entry<String, String> entry : style.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (node instanceof Text) {
                switch (key) {
                    case "color":
                        nodeStyleToSet.append("-fx-fill: " + value + ";");
                        break;
                    /* Includes font-family, font-size, font-weight */
                    default:
                        nodeStyleToSet.append("-fx-" + key + ": " + value + ";");
                        break;
                }
            } else if (node instanceof Pane) {
                switch (key) {
                    case "text-align":
                        switch (value) {
                            case "center":
                                nodeStyleToSet.append("-fx-alignment: center;");
                                break;
                            default:
                                nodeStyleToSet.append("-fx-alignment: center-" + value + ";");
                        }
                    case "color":
                    case "font-weight":
                    case "font-size":
                        legacyStyle.put(key, value);
                        break;
                    case "padding":
                        nodeStyleToSet.append("-fx-padding: " + value + " " + value + " " + value + " " + value + ";'");
                        break;
                    /* Includes opacity, background-color, border-width, border-color, border-style */
                    default:
                        nodeStyleToSet.append("-fx-" + key + ": " + value + ";");
                        break;
                }
            }
        }

        return legacyStyle;
    }
}
