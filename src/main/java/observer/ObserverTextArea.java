package observer;

import adapter.ElementAdapter;
import adapter.VisitableAdapter;
import javafx.scene.layout.VBox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import util.GlobalCssProvider;
import visitor.ConcreteVisitor;
import java.util.HashMap;

public class ObserverTextArea implements ObserverGeneric{

    private VBox vboxRoot;
    private Element body;
    private Subject subject;

    public ObserverTextArea(Subject subject, VBox vboxroot){
        this.subject = subject;
        this.subject.attach(this);
        this.vboxRoot = vboxroot;
    }

    @Override
    public void update(){
        String content = this.subject.getState();
        Document document = Jsoup.parse(content);
        this.body = document.getElementsByTag("body").get(0);
        GlobalCssProvider.createInstance(content);
        VisitableAdapter visitableAdapter = new ElementAdapter(body);
        visitableAdapter.accept(new ConcreteVisitor(), this.vboxRoot, new HashMap<>());
    }


}
