package observer;

public interface Subject {
    void notifyAllObservers();
    void attach(ObserverGeneric observerGeneric);
    String getState();
    void setState(String state);
}
