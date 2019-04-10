package observer;

import java.util.*;

public class ObservableTextArea implements Subject {
    private List<ObserverGeneric> observers = new ArrayList<ObserverGeneric>();
    private String state;


    @Override
    public String getState(){
        return this.state;
    }

    @Override
    public void setState(String state){
        this.state = state;
        notifyAllObservers();
    }

    public  void attach(ObserverGeneric observerTextArea){
        this.observers.add(observerTextArea);
    }

    public  void notifyAllObservers(){
        for(ObserverGeneric observer: this.observers){
            observer.update();
        }
    }
}
