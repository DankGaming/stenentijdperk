package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Stamlid;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TableauModel implements TableauObservable {
    ArrayList<TableauObserver> observers = new ArrayList<>();
    private List<Stamlid> stamleden;
    private PlayerModel player;
    private int[] gereedschap;
    private boolean[] gereedschapGebruikt;

    // TODO alle info moet uit playermodel
    public TableauModel() {
        stamleden = new LinkedList<>();
        gereedschap = new int[]{0, 0, 0};
    }

    public void ontvangStamlid(Stamlid stamlid) {
        stamleden.add(stamlid);
    }

    public void gebruikStamlid(Stamlid stamlid) {
        stamleden.remove(stamlid);
    }

    @Override
    public void register(TableauObserver to) {
        this.observers.add(to);
        notifyAllObservers();
    }

    @Override
    public void notifyAllObservers() {
        for (TableauObserver to : observers) {
            to.update(this);
        }
    }

    public void gebruikGereedschap() {
    }

    public void verhoogGereedschap() {
        int index = gereedschap.length - 1, totaal = 0;
        for (int i = gereedschap.length - 1; i >= 0; i--) {
            int aantal = gereedschap[i];
            totaal += aantal;
            if (gereedschap[index] >= aantal) {
                index = i;
            }
        }
        gereedschap[index]++;
        notifyAllObservers();
    }

    @Override
    public int[] getTools() {
        return this.gereedschap;
    }

    public int[] getGereedschap() {
        return gereedschap;
    }

    public boolean[] getGereedschapGebruikt() {
        return gereedschapGebruikt;
    }

    public List<Stamlid> getStamleden() {
        return stamleden;
    }
}
