package hsleiden.stenentijdperk.stenentijdperk.observers;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;

import java.util.ArrayList;
import java.util.List;

public interface PlayerObservable {
    void registerObserver(PlayerObserver po);

    void notifyAllObservers();

    ArrayList<Tool> getTools();
    int getVillagers();
    List<Integer> getResources();
    List<String> getTreasures();
    List<Integer> getMultiplier();
}
