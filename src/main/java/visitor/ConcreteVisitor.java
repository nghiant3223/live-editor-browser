package visitor;

import adapter.ElementAdapter;
import adapter.TextNodeAdapter;
import adapter.VisitableAdapter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import util.CssParser;
import util.CssProcess;
import config.DefaultStyle;
import singleton.GlobalCssProvider;

import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConcreteVisitor implements Visitor {
    public boolean idHasExisted(Node node) {
        String nodeId = node.attributes().get("id");

        if (nodeId == "" || !GlobalCssProvider.getInstance().hasId(nodeId)) {
            GlobalCssProvider.getInstance().addId(nodeId);
            return false;
        }
        return true;
    }

    @Override
    public void visit(Element node, Pane parent, HashMap<String, String> inheritedStyle) {
        if (idHasExisted(node)) {
            throw new DuplicateFormatFlagsException("Id has existed");
        }

        String tagName = node.tagName();
        VBox block = new VBox();

        /* WARNING: DO NOT CHANGE THE ORDER OF putAll */
        HashMap<String, String> blockInheritedStyle = new HashMap<>() {
            {
                /* Default style */
                putAll(DefaultStyle.getDefaultStyle(tagName));

                /* Global tag style */
                HashMap<String, String> globalStyle = GlobalCssProvider.getInstance().getStyles(tagName);
                if (globalStyle != null) {
                    putAll(globalStyle);
                }

                /* Inherited style */
                putAll(inheritedStyle);

                /* Id style */
                HashMap<String, String> idStyle = GlobalCssProvider.getInstance().getStyles("#" + node.attributes().get("id"));
                if (idStyle != null) {
                    putAll(idStyle);
                }

                /* Class style */
                HashMap<String, String> classStyle = GlobalCssProvider.getInstance().getStyles("." + node.attributes().get("class"));
                if (classStyle != null) {
                    putAll(classStyle);
                }

                /* Inline style */
                putAll(CssParser.parseInlineCss(node.attributes().get("style")));
            }
        };

        /* Assign block suitable style and get actual style `blockStyleToSet` for later setStyle() */
        StringBuilder blockStyleToSet = new StringBuilder();
        HashMap<String, String> legacyStyle = CssProcess.assignCssProperty(block, blockInheritedStyle, blockStyleToSet);

        /* Visit its children */
        for (Node childNode : node.childNodes()) {
            VisitableAdapter visitableAdapter;

            if (childNode instanceof Element) {
                visitableAdapter = new ElementAdapter((Element) childNode);
                visitableAdapter.accept(this, block, legacyStyle);
            } else if (childNode instanceof TextNode) {
                visitableAdapter = new TextNodeAdapter((TextNode) childNode);
                visitableAdapter.accept(this, block, legacyStyle);
            }
        }

        /* Set actual style */
        block.setStyle(blockStyleToSet.toString());

        /* Add the block to its container */
        parent.getChildren().add(block);
    }

    @Override
    public void visit(TextNode node, Pane parent, HashMap<String, String> inheritedStyle) {
        String content = node.text();
        Pattern space = Pattern.compile("^[ \\t\\r\\n\\f]+$");
        Matcher spaceMatcher = space.matcher(content);

        /* If content is not space, tab, newline, return, ... */
        if (!spaceMatcher.find()) {
            Text text = new Text(content);

            /* Assign text suitable style and get actual style `textStyleToSet` for later setStyle() */
            StringBuilder textStyleToSet = new StringBuilder();
            CssProcess.assignCssProperty(text, inheritedStyle, textStyleToSet);

            /* Set actual style */
            text.setStyle(textStyleToSet.toString());

            /* Add the text to its container */
            parent.getChildren().add(text);
        }
    }
}
