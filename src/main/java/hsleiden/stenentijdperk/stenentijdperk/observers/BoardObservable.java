package hsleiden.stenentijdperk.stenentijdperk.observers;

public interface BoardObservable {
    void register(BoardObserver boardobserver);

    void notifyAllObservers();

    void updatePunten();

    void removeKaarten(int index);

    void removeHutten(int index);

    int getTurn();
}
