package util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CssParser {
    private static Pattern tagPattern = Pattern.compile("([a-z0-9\\.\\-#]+)[ \\t\\r\\n\\f]*\\{[ \\t\\r\\n\\f]*(?:((?:[a-z\\-]+[ \\t\\r\\n\\f]*:[ \\t\\r\\n\\f]*[a-z0-9]+;[ \\t\\r\\n\\f]*)*[ \\t\\r\\n\\f]*(?:[a-z\\-]+[ \\t\\r\\n\\f]*:[ \\t\\r\\n\\f]*[a-z0-9]+;?))[ \\t\\r\\n\\f]*)?\\}");
    private static Pattern propertyPattern = Pattern.compile("([a-z\\-]+)[ \\t\\r\\n\\f]*:[ \\t\\r\\n\\f]*([a-z0-9\\-]+);?");

    public static HashMap<String, HashMap<String, String>> parseEmbeddedCss(String text) {
        HashMap<String, HashMap<String, String>> tagToPropertyMap = new HashMap<>();
        Matcher tagMatcher = tagPattern.matcher(text);

        while (tagMatcher.find()) {
            HashMap<String, String> propertyMap;

            /* If map already has tag name */
            if (!tagToPropertyMap.containsKey(tagMatcher.group(1))) {
                propertyMap = new HashMap<>();
                /* If map does not have tag name */
            } else {
                propertyMap = tagToPropertyMap.get(tagMatcher.group(1));
            }

            Matcher propertyMatcher = propertyPattern.matcher(tagMatcher.group(2));
            while (propertyMatcher.find()) {
                /* Put pair of (<property>, <value>) into property map */
                propertyMap.put(propertyMatcher.group(1), propertyMatcher.group(2));
            }

            /* Put pair of (<tag name>, (<property>, <value>)) into tagToPropertyMap */
            tagToPropertyMap.put(tagMatcher.group(1), propertyMap);
        }

        return tagToPropertyMap;
    }

    public static HashMap<String, String> parseInlineCss(String text) {
        HashMap<String, String> propertyMap;
        propertyMap = new HashMap<>();

        Matcher propertyMatcher = propertyPattern.matcher(text);
        while (propertyMatcher.find()) {
            /* Put pair of (<property>, <value>) into property map */
            propertyMap.put(propertyMatcher.group(1), propertyMatcher.group(2));
        }

        return propertyMap;
    }
}
