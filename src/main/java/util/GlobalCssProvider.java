package util;

import java.util.HashMap;
import java.util.HashSet;

public class GlobalCssProvider {
    private HashMap<String, HashMap<String, String>> styles;
    private HashSet<String> idSet;

    private static GlobalCssProvider instance;

    private GlobalCssProvider(String document) {
        this.styles = CssParser.parseEmbeddedCss(document);
        this.idSet = new HashSet<>();
    }

    public static void createInstance(String styles) {
        if (instance == null) {
            instance = new GlobalCssProvider(styles);
        }
    }

    public static GlobalCssProvider getInstance() {
        return instance;
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
