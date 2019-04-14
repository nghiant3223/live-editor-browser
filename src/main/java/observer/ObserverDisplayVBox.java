package observer;

import adapter.ElementAdapter;
import adapter.VisitableAdapter;
import javafx.scene.layout.VBox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import singleton.GlobalCssProvider;
import visitor.ConcreteVisitor;
import java.util.HashMap;

public class ObserverDisplayVBox extends VBox implements Observer<String> {
    @Override
    public void update(String state){
        Document document = Jsoup.parse(state);
        Element body = document.getElementsByTag("body").get(0);
        VisitableAdapter bodyVisitableAdapter = new ElementAdapter(body);

        GlobalCssProvider.updateStyle(state);
        bodyVisitableAdapter.accept(new ConcreteVisitor(), this, new HashMap<>());
    }
}
