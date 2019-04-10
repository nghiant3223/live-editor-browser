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

        Visitor concreteVisitor = new ConcreteVisitor();

        concreteVisitor.visit(body, root, new HashMap<>());

        // Create Scene and setting.
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("MiniBrowser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Read .html file, parse and visit
        try {
            File file = new File("./src/main/java/" + FILE_NAME);
            Scanner sc = new Scanner(file);

            StringBuilder fileContentBuilder = new StringBuilder();
            while (sc.hasNextLine()) {
                fileContentBuilder.append(sc.nextLine());
            }

            String fileContent = fileContentBuilder.toString();
            Document document = Jsoup.parse(fileContent);
            body = document.getElementsByTag("body").get(0);
            GlobalCssProvider.createInstance(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Start UI
        launch(args);
    }
}
