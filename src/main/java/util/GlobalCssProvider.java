package util;

import java.util.HashMap;

public class GlobalCssProvider {
    private HashMap<String, HashMap<String, String>> styles;
    private static GlobalCssProvider instance;

    private GlobalCssProvider(String document) {
        this.styles = CssParser.parseEmbeddedCss(document);
    }

    public static void createInstance(String styles) {
        if (instance == null) {
            instance = new GlobalCssProvider(styles);
        }
    }

    public HashMap<String, String> getStyles(String tagName) {
        return styles.get(tagName);
    }

    public static GlobalCssProvider getInstance() {
        return instance;
    }
}
