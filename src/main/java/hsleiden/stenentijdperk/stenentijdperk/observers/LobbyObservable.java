package hsleiden.stenentijdperk.stenentijdperk.observers;

public interface LobbyObservable {
    void register(LobbyObserver lo);
    void notifyAllObservers();
}
