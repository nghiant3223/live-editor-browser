package observer;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ObserverLineIndicatorVBox implements Observer<String> {
    private VBox vBox;

    public ObserverLineIndicatorVBox(VBox vBox) {
        this.vBox = vBox;
    }

    @Override
    public void update(String state) {
        int newlineCount = 0;

        vBox.getChildren().clear();
        for (int i = 1; i <= state.split(System.getProperty("line.separator")).length; i++) {
            ++newlineCount;
            String format = (newlineCount < 10) ? (" " + newlineCount) : Integer.toString(newlineCount);
            vBox.getChildren().add(new Text(format));
        }
    }
}
