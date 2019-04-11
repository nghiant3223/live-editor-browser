package observer;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class ObservableTextArea extends TextArea implements Observable {
    List<Observer> observers;

    public ObservableTextArea() {
        this.observers = new ArrayList<>();
    }

    public ObservableTextArea(String s) {
        super(s);
        this.observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(getText());
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
