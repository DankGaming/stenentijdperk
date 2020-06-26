package hsleiden.stenentijdperk.stenentijdperk.observers;

public interface DobbelsteenObservable {
    void register(DobbelsteenObserver lo);

    void notifyAllObservers();

    int getId();
}
