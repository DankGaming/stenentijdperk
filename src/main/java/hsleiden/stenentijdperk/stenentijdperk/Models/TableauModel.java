package hsleiden.stenentijdperk.stenentijdperk.Models;


import hsleiden.stenentijdperk.stenentijdperk.Helpers.Stamlid;
import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;

import java.util.LinkedList;
import java.util.List;

public class TableauModel {
    private List<Stamlid> stamleden;
    private PlayerModel player;
    private int[] gereedschap;
    private boolean[] gereedschapGebruikt;

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

    }
}
