package hsleiden.stenentijdperk.stenentijdperk.observers;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;

import java.util.ArrayList;

public interface PlayerObservable {
    void registerObserver(PlayerObserver po);

    void notifyAllObservers();

    ArrayList<Tool> getTools();
}
