package hsleiden.stenentijdperk.stenentijdperk.observers;

import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;

public interface BoardObservable {
    void register(BoardObserver boardobserver);

    void notifyAllObservers();

    int getTurn();

    BoardModel getCurrentBoard();
}
