package hsleiden.stenentijdperk.stenentijdperk.Models;


import hsleiden.stenentijdperk.stenentijdperk.Helpers.Stamlid;
import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TableauModel {
    private List<Stamlid> stamleden;
    private PlayerModel player;
    private int[] gereedschap;
    private boolean[] gereedschapGebruikt;
    ArrayList<TableauObserver> observers = new ArrayList<>();

    public TableauModel(){
        stamleden = new LinkedList<>();
        gereedschap = new int[] { 0, 0, 0 };
    }

    public TableauModel(PlayerModel player){
        stamleden = new LinkedList<>();
        gereedschap = new int[] { 0, 0, 0 };
    }

    public void ontvangStamlid(Stamlid stamlid) {
        stamleden.add(stamlid);
    }

    public void gebruikStamlid(Stamlid stamlid) {
        stamleden.remove(stamlid);
    }

    public List<Stamlid> getStamleden(){
        return stamleden;
    }

    public void notifyAllObservers() {
        for(TableauObserver lo : observers) {
            lo.update((TableauObservable) this);
        }
    }

    public int[] getGereedschap() {
        return gereedschap;
    }

    public boolean[] getGereedschapGebruikt() {
        return gereedschapGebruikt;
    }

    public void gebruikGereedschap(){
    }

    public void verhoogGereedschap() {
        int index = gereedschap.length - 1, totaal = 0;
            for (int i = gereedschap.length - 1; i >= 0; i--){
                int aantal = gereedschap[i];
                totaal += aantal;
                if (gereedschap[index] >= aantal){
                    index = i;
                }
            }
            gereedschap[index]++;
            notifyAllObservers();
    }
}
