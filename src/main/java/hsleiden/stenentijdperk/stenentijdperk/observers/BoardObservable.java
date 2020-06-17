package hsleiden.stenentijdperk.stenentijdperk.observers;

public interface BoardObservable {
    void register(BoardObserver boardobserver);

    void notifyAllObservers();

    int getTurn();
}
