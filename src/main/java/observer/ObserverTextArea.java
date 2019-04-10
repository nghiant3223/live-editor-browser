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

public class ObserverTextArea extends  ObserverAbstract{

    private VBox vboxRoot;
    private Element body;

    public ObserverTextArea(ObservableTextArea subject, VBox vboxroot){
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

    public void requestUpdate(String state){
        this.subject.setState(state);
    }
}
