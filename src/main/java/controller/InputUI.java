package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import observer.ObservableTextArea;
import observer.ObserverGeneric;
import observer.ObserverTextArea;
import observer.Subject;
import org.jsoup.nodes.Element;
import java.net.URL;
import java.util.ResourceBundle;

public class InputUI implements Initializable {
    @FXML
    private VBox vboxRoot;
    @FXML
    private TextArea InputTxt;
    @FXML
    private Button btnCompile;

    private static Element body;
    private static Subject observableTextArea;
    private static ObserverGeneric observerGeneric;


    @Override
    public void initialize(URL localion, ResourceBundle resources){


        InputTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                onChangeInputTxtEvent();
            }
        });
        observableTextArea = new ObservableTextArea();
        observerGeneric = new ObserverTextArea(observableTextArea, this.vboxRoot);
    }
    public InputUI(){

    }

    public void onChangeInputTxtEvent(){
        vboxRoot.getChildren().clear();
        observableTextArea.setState(InputTxt.getText());
    }


}
