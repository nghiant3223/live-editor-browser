package controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import observer.*;
import singleton.GlobalCssProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Container implements Initializable {
    @FXML
    private ObserverLineIndicatorVBox lineIndicatorVBox;

    @FXML
    private ObserverDisplayVBox observerDisplayVBox;

    @FXML
    private ObservableTextArea inputTextArea;

    @FXML
    private ObserverMiniMapTextArea observerMiniMapTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputTextArea.addObserver(observerDisplayVBox);
        inputTextArea.addObserver(lineIndicatorVBox);
        inputTextArea.addObserver(observerMiniMapTextArea);

        inputTextArea.textProperty().addListener(this::handleInputChanged);
        inputTextArea.addEventFilter(KeyEvent.KEY_PRESSED, this::handleTabTapped);

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

    private void handleInputChanged(ObservableValue<? extends String> ignoredParam1, String ignoreParam2, String ignoreParam3) {
        observerDisplayVBox.getChildren().clear();
        inputTextArea.notifyObservers();
    }

    private void handleTabTapped(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            String s = "    ";
            inputTextArea.insertText(inputTextArea.getCaretPosition(), s);
            event.consume();
        }
    }
}
