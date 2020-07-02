package hsleiden.stenentijdperk.stenentijdperk.observers;

import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;

public interface BoardObservable {
    void register(BoardObserver boardobserver);

    void notifyAllObservers();

    void updatePunten();

    void removeKaarten(int index);

    void removeHutten(int index);

    int getTurn();
}
