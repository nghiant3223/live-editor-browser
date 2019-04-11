package singleton;

import util.CssParser;

import java.util.HashMap;
import java.util.HashSet;

public class GlobalCssProvider {
    private static GlobalCssProvider instance;
    private HashMap<String, HashMap<String, String>> styles;
    private HashSet<String> idSet;

    private GlobalCssProvider(String document) {
        this.styles = CssParser.parseEmbeddedCss(document);
        this.idSet = new HashSet<>();
    }

    public static GlobalCssProvider getInstance() {
        return instance;
    }

    public static void updateStyle(String styles) {
        if (instance == null) {
            instance = new GlobalCssProvider(styles);
        } else {
            instance.styles = CssParser.parseEmbeddedCss(styles);
        }
    }

    public HashMap<String, String> getStyles(String key) {
        return styles.get(key);
    }

    public void addId(String id) {
        this.idSet.add(id);
    }

    public boolean hasId(String id) {
        return idSet.contains(id);
    }
}
