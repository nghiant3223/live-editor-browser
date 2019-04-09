package util;

import java.util.HashMap;

public class GlobalCssProvider {
    private HashMap<String, HashMap<String, String>> styles;
    private static GlobalCssProvider instance;

    private GlobalCssProvider(String document) {
        this.styles = CssParser.parseEmbeddedCss(document);
    }

    public static void createIntance(String styles) {
        if (instance == null) {
            instance = new GlobalCssProvider(styles);
        }
    }

    public HashMap<String, String> getStyles(String tagname) {
        return styles.get(tagname);
    }

    public static GlobalCssProvider getInstance() {
        return instance;
    }
}
