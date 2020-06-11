package hsleiden.stenentijdperk.stenentijdperk.observers;

import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;

import java.util.ArrayList;

public interface BoardObservable {
    void register(BoardObserver boardobserver);

    void notifyAllObservers();

    int getTurn();
}
