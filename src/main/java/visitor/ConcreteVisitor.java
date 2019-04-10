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
    public void visit(Node node, Pane parent, HashMap<String, String> inheritedStyle) {
        if (node instanceof Element) {
            String tagName = ((Element) node).tagName();
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

                    HashMap<String, String> legacyStyle = CssProcess.assignCssProperty(block, blockInheritedStyle);

                    for (Node childNode : node.childNodes()) {
                        visit(childNode, block, legacyStyle);
                    }

                    parent.getChildren().add(block);
                    break;
            }
        } else if (node instanceof TextNode) {
            String content = ((TextNode) node).text();
            Pattern space = Pattern.compile("^[ \\t\\r\\n\\f]+$");
            Matcher spaceMatcher = space.matcher(content);

            /* If content is not space, tab, newline, return, ... */
            if (!spaceMatcher.find()) {
                Text text = new Text(content);

                CssProcess.assignCssProperty(text, inheritedStyle);
                parent.getChildren().add(text);
            }
        }
    }
}
