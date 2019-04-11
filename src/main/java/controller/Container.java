package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import observer.ObservableTextArea;
import observer.ObserverVBox;
import singleton.GlobalCssProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Container implements Initializable {
    private ObserverVBox outputVBox;

    @FXML
    private VBox lineIndicatorVBox;

    @FXML
    private VBox rootVBox;

    @FXML
    private ObservableTextArea inputTextArea;

    public Container() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        outputVBox = new ObserverVBox(this.rootVBox);
        inputTextArea.addObserver(outputVBox);
        inputTextArea.textProperty().addListener((inputTextAreaValue, s, s2) -> onChangeInputTxtEvent());

        try {
            /* Read index.html */
            File file = new File("./src/main/java/index.html");
            Scanner sc = new Scanner(file);

            StringBuilder fileContentBuilder = new StringBuilder();

            while (sc.hasNextLine()) { fileContentBuilder.append(sc.nextLine() + "\n"); }

            fileContentBuilder.append("\b");
            String fileContent = fileContentBuilder.toString();
            inputTextArea.setText(fileContent);
            GlobalCssProvider.updateStyle(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onChangeInputTxtEvent() {
        rootVBox.getChildren().clear();
        inputTextArea.notifyObservers();
    }
}
