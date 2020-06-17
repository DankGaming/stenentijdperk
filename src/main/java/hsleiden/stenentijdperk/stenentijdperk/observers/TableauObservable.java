package hsleiden.stenentijdperk.stenentijdperk.observers;

public interface TableauObservable {
    void register(TableauObserver lo);
    void notifyAllObservers();
}
