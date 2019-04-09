package config;

import java.util.HashMap;

public class DefaultStyle {
    public static HashMap<String, String> getDefaultStyle(String tagname) {
        switch (tagname) {
            case "h1":
                return new HashMap<>() {
                    {
                        put("font-weight", "bold");
                        put("font-size", "32px");
                    }
                };
            default:
                return new HashMap<>();
        }
    }
}
