package observer;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ObserverMiniMapTextArea extends TextArea implements Observer<String> {
    @Override
    public void update(String state) {
        setText(state);
    }
}
