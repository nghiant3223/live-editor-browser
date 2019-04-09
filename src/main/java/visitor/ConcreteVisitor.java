package visitor;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
        for (Node childNode : node.childNodes()) {
            if (childNode instanceof Element) {
                String tagName = ((Element) childNode).tagName();
                switch (tagName) {
                    case "div":
                    case "h1":
                        VBox block = new VBox();

                        HashMap<String, String> h1InheritedStyle = new HashMap<>() {
                            {
                                putAll(DefaultStyle.getDefaultStyle(tagName));
                                HashMap<String, String> globalStyle = GlobalCssProvider.getInstance().getStyles(tagName);
                                if (globalStyle != null) {
                                    putAll(globalStyle);
                                }
                                putAll(inheritedStyle);
                                putAll(CssParser.parseInlineCss(childNode.attributes().get("style")));
                            }
                        };

                        CssProcess.assignCssProperty(block, h1InheritedStyle);
                        visit(childNode, block, h1InheritedStyle);
                        parent.getChildren().add(block);
                        break;
                }
            } else if (childNode instanceof TextNode) {
                String text = ((TextNode) childNode).text();
                Label label = new Label(text);

                if (inheritedStyle.containsKey("font-size")) {
                    Pattern fontSizePattern = Pattern.compile("([0-9]+)px");
                    Matcher fontSizeMatcher = fontSizePattern.matcher(inheritedStyle.get("font-size"));

                    System.out.println(inheritedStyle.get("font-size"));
                    if (fontSizeMatcher.find()) {
                        label.setFont(Font.font("Ubuntu", Double.parseDouble(fontSizeMatcher.group(1))));
                    } else {
                        label.setFont(Font.font("Ubuntu", 16));
                    }
                } else {
                    label.setFont(Font.font("Ubuntu", 16));
                }

                CssProcess.assignCssProperty(label, inheritedStyle);
                parent.getChildren().add(label);
            }
        }
    }
}
