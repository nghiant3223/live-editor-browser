package visitor;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import util.CssParser;
import util.CssProcess;
import config.DefaultStyle;
import util.GlobalCssProvider;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConcreteVisitor implements Visitor {
    @Override
    public void visit(Element node, Pane parent, HashMap<String, String> inheritedStyle) {
        String tagName = node.tagName();
        switch (tagName) {
            case "body":
            case "div":
            case "h1":
            case "h2":
            case "h3":
            case "h4":
            case "h5":
            case "h6":
                VBox block = new VBox();

                /* WARNING: DO NOT CHANGE THE ORDER OF putAll */
                HashMap<String, String> blockInheritedStyle = new HashMap<>() {
                    {
                        /* Default style */
                        putAll(DefaultStyle.getDefaultStyle(tagName));

                        /* Global style */
                        HashMap<String, String> globalStyle = GlobalCssProvider.getInstance().getStyles(tagName);
                        if (globalStyle != null) {
                            putAll(globalStyle);
                        }

                        /* Inherited style */
                        putAll(inheritedStyle);

                        /* Inline style */
                        putAll(CssParser.parseInlineCss(node.attributes().get("style")));
                    }
                };

                StringBuilder blockStyleToSet = new StringBuilder();
                HashMap<String, String> legacyStyle = CssProcess.assignCssProperty(block, blockInheritedStyle, blockStyleToSet);

                for (Node childNode : node.childNodes()) {
                    if (childNode instanceof Element) {
                        visit(((Element) childNode), block, legacyStyle);
                    } else if (childNode instanceof TextNode) {
                        visit(((TextNode) childNode), block, legacyStyle);
                    }
                }

                block.setStyle(blockStyleToSet.toString());
                parent.getChildren().add(block);
                break;
        }
    }

    @Override
    public void visit(TextNode node, Pane parent, HashMap<String, String> inheritedStyle) {
        String content = node.text();
        Pattern space = Pattern.compile("^[ \\t\\r\\n\\f]+$");
        Matcher spaceMatcher = space.matcher(content);

        /* If content is not space, tab, newline, return, ... */
        if (!spaceMatcher.find()) {
            Text text = new Text(content);

            StringBuilder textStyleToSet = new StringBuilder();
            CssProcess.assignCssProperty(text, inheritedStyle, textStyleToSet);

            text.setStyle(textStyleToSet.toString());
            parent.getChildren().add(text);
        }
    }
}
