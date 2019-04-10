package observer;

import java.util.*;

public class ObservableTextArea {
    private List<ObserverTextArea> observers = new ArrayList<ObserverTextArea>();
    private String state;

    public String getState(){
        return this.state;
    }
    public void setState(String state){
        this.state = state;
        notifyAllObservers();
    }

    public  void attach(ObserverTextArea observerTextArea){
        this.observers.add(observerTextArea);
    }
    public  void notifyAllObservers(){
        for(ObserverTextArea observer: this.observers){
            observer.update();
        }
    }
}
