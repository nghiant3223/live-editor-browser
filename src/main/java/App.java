import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jsoup.nodes.Element;

public class App extends Application {
    private static final String FILE_NAME = "index.html";
    private static Element body;
    private String fxmlFilename = "InputUI.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
//        VBox root = new VBox();
//
//        VisitableAdapter visitableAdapter = new ElementAdapter(body);
//        visitableAdapter.accept(new ConcreteVisitor(), root, new HashMap<>());

        /* Create Scene and setting */

        Parent root = FXMLLoader.load(getClass().getResource(fxmlFilename));
        Scene scene = new Scene(root);
        primaryStage.setTitle("MiniBrowser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
//        try {
//            /* Read index.html */
//            File file = new File("./src/main/java/" + FILE_NAME);
//            Scanner sc = new Scanner(file);
//
//            StringBuilder fileContentBuilder = new StringBuilder();
//            while (sc.hasNextLine()) {
//                fileContentBuilder.append(sc.nextLine());
//            }
//            String fileContent = fileContentBuilder.toString();
//
//            /* Get `body` node */
//            Document document = Jsoup.parse(fileContent);
//            body = document.getElementsByTag("body").get(0);
//
//            /* Create global style holder */
//            GlobalCssProvider.createInstance(fileContent);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }



        launch(args);
    }
}
