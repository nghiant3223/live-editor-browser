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
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFilename));
        Scene scene = new Scene(root);
        primaryStage.setTitle("MiniBrowser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
