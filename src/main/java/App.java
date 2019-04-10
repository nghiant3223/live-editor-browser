import adapter.ElementAdapter;
import adapter.VisitableAdapter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import util.GlobalCssProvider;
import visitor.ConcreteVisitor;
import visitor.Visitor;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class App extends Application {
    private static final String FILE_NAME = "index.html";
    private static Element body;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();

        VisitableAdapter visitableAdapter = new ElementAdapter(body);
        visitableAdapter.accept(new ConcreteVisitor(), root, new HashMap<>());

        /* Create Scene and setting */
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("MiniBrowser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            /* Read index.html */
            File file = new File("./src/main/java/" + FILE_NAME);
            Scanner sc = new Scanner(file);

            StringBuilder fileContentBuilder = new StringBuilder();
            while (sc.hasNextLine()) {
                fileContentBuilder.append(sc.nextLine());
            }
            String fileContent = fileContentBuilder.toString();

            /* Get `body` node */
            Document document = Jsoup.parse(fileContent);
            body = document.getElementsByTag("body").get(0);

            /* Create global style holder */
            GlobalCssProvider.createInstance(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        launch(args);
    }
}
