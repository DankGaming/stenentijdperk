package hsleiden.stenentijdperk.stenentijdperk.Helpers;

import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

import java.util.ArrayList;

public class Test {
    public Test() {
    }

    public void loadBoardView(ArrayList<PlayerModel> playerModels, PlayerModel playerModel) {
        System.out.println("Yayeet");
        ViewManager.loadBoardView(playerModels, playerModel);
        System.out.println("Yayeet");
    }
}
