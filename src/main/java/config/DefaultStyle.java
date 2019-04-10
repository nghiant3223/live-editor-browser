package config;

import java.util.HashMap;

public class DefaultStyle {
    public static HashMap<String, String> getDefaultStyle(String tagname) {
        switch (tagname) {
            case "h1":
                return new HashMap<>() {
                    {
                        put("font-weight", "bold");
                        put("font-size", "24px");
                    }
                };

            case "h2":
                return new HashMap<>() {
                    {
                        put("font-weight", "bold");
                        put("font-size", "22px");
                    }
                };

            case "h3":
                return new HashMap<>() {
                    {
                        put("font-weight", "bold");
                        put("font-size", "20px");
                    }
                };

            case "h4":
                return new HashMap<>() {
                    {
                        put("font-weight", "bold");
                        put("font-size", "16px");
                    }
                };

            case "h5":
                return new HashMap<>() {
                    {
                        put("font-weight", "bold");
                        put("font-size", "12px");
                    }
                };

            case "h6":
                return new HashMap<>() {
                    {
                        put("font-weight", "bold");
                        put("font-size", "10px");
                    }
                };

            default:
                return new HashMap<>();
        }
    }
}