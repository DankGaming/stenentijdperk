package hsleiden.stenentijdperk.stenentijdperk.Helpers;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class Stamlid {
    private PlayerModel player;

    public Stamlid(PlayerModel player) {
        this.player = player;
    }

    public PlayerModel getSpeler() {
        return player;
    }
}
