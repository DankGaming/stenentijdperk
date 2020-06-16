package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObserver;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Resource;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardController {
    private PlayerController playercontroller;
    private BoardModel boardmodel;
    private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();

    public BoardController() {
        PlayerModel matt = new PlayerModel("Matt");
        PlayerModel jake = new PlayerModel("Jake");
        PlayerModel lucas = new PlayerModel("Lucas");
        PlayerModel carlos = new PlayerModel("Carlos");
        players.add(matt);
        players.add(jake);
        players.add(lucas);
        players.add(carlos);
        playercontroller = new PlayerController();
        boardmodel = new BoardModel();
        boardmodel.setPlayer(matt); // Begin van het spel turn eerste speler bepalen.
        System.out.println(boardmodel.getPlayer().getNaam() + " is aan de beurt en heeft " +   boardmodel.getPlayer().getVillagers() + ".");
        /*
         * voedsel op jacht (53) hout op bos leem op leemgroeve steen op steengroeve
         * goud op rivier (68) gereedschap bij maker (18) vier beschavingskaarten (36)
         * per speler 1 stapel hutten (28) 0 op voedselspoor 0 op scorespoor 5 stamleden
         * 12 voedsel 5 extra stamleden
         */

    }

    public String scanner(String text) {
        Scanner myObj = new Scanner(System.in); // Create a Scanner object
        System.out.println(text);
        String input = myObj.nextLine(); // Read user input
        return input;
    }

    public void registerObserver(BoardObserver boardobserver) {
        this.boardmodel.register(boardobserver);
    }
    
    public void onResourceButtonClick(int location) {
        if (!boardmodel.getPlaced() && boardmodel.requestCap(location) - boardmodel.requestVillagers(location) != 0){
            String input;
            do {
                input = scanner("Hoeveel stamleden?");
            } while (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > playercontroller.getVillagers(boardmodel.getPlayer())
                    || Integer.parseInt(input) > (boardmodel.requestCap(location) - boardmodel.requestVillagers(location))); // hoeveel passen op de
                                                                                        // locatie
            playercontroller.setVillagers(boardmodel.getPlayer(),
                    (playercontroller.getVillagers(boardmodel.getPlayer()) - Integer.parseInt(input))); // Dit veranderd de hoeveelheid stamleden van een speler
            boardmodel.changeVillagers(location, Integer.parseInt(input)); // Hier wordt in het object Resource aangepast hoeveel villagers er staan.
            boardmodel.setPlaced(true);
            
        }
    }
    
    // methode om de onderste buttons af te handelen. maakt de kaart/hut bezet en zorgt dat je niet meer kan plaatsen.
    public void onKaartButtonClick(int index){
         if (!boardmodel.getPlaced()){
            if (index >= 4){  //&& !boardmodel.getStatus(index-4)){
                //boardmodel.setStatus(index-4, true);
                boardmodel.setPlaced(true);
                playercontroller.setVillagers(boardmodel.getPlayer(),(playercontroller.getVillagers(boardmodel.getPlayer()) - 1));
            } /*else if (!boardmodel.getStatus(index-4)){  ook tijdelijk hutjes bestaat nog niet
                //boardmodel.hutjes.get(index).setStatus(true);
                boardmodel.setPlaced(true);
            } */
         }
    }


    /* public void onVillageButtonClick(int index){
         if (!boardmodel.kaarten.get(index).getStatus() && !boardmodel.getplaced()){
            switch (index){
                case 0:
                    do something on agriculture
                case 1:
                    do something on increase villagers
                case 2:
                    do something on increase tools.
            }
    }*/

    public void endTurn() {
        if (boardmodel.getPlaced()) { // checkt of de speler stamleden heeft geplaast.
            System.out.println("einde beurt");
            boolean villagersLeft = true;
            int i = 0;
            for (int j = 0; j < 4; j++) {
                if (boardmodel.getPlayer().equals(players.get(j))) { // Bepaling welke player aan de beurt is
                    i = j;
                }
            }
            switch (i) { // Verschillede loops bepaalt door welke speler aan de beurt was
                case 0: // Spelers 1, 2 en 3
                case 1:
                case 2:
                    villagersLeft = loopPlayers(i, players);
                    System.out.println("Hi");
                    if (!villagersLeft) { // Als de vorige loop niks gevonden heeft dan komt deze pas
                        System.out.println("Yo");
                        villagersLeft = loopPlayers(-1, players.subList(0, i + 1));
                    }
                    break;
                case 3:
                    villagersLeft = loopPlayers(i - 4, players);
                    break;
            }
            if (!villagersLeft) {
                System.out.println("Nu komen de acties");
            }
        } else {
            System.out.println("plaats villagers");
        }
    }

    // Methode om door lijsten spelers te loopen.
    public boolean loopPlayers(int start, List<PlayerModel> player) {
        boolean found = false;
        for (int j = start + 1; j < player.size(); j++) {
            if (playercontroller.getVillagers(player.get(j)) != 0) {
                boardmodel.setPlayer(player.get(j)); // Veranderd de huidige speler
                boardmodel.setPlaced(false); // Reset het plaatsten
                found = true;
                System.out.println(boardmodel.getPlayer().getNaam() + " is aan de beurt en heeft "
                        + boardmodel.getPlayer().getVillagers() + " stamleden over.");
                break;
            }
        }
        return found;
    }

}

// public MainLoop(){
// while(!wincondition){
// stamleden plaatsen
// volgende speler aan de beurt
// loop totdat stamleden op zijn

// acties uitvoeren
// volgende speler aan de beurt
// einde

// stamleden voeden
// speler krijgt voedsel gelijk aan score op voedselspoor
// per stamlid -1 voedsel
// 1 grondstof = 1 voedsel
// if voedsel + grondstoffen < stamleden , -10 punten

// nieuwe ronden
// andere speler begint
// beschavingskaarten aanvullen
// gereedschap reset
// }

// if beschavinskaarten < 4 , einde spel
// if (1 stapel hutten is leeg) , einde spel (na de ronde)
// elke grondstof is 1 punt
// aantal verschillende groene beschavingskaarten ^2 = aantal punten
// aantal symbolen op zandkleurige beschavinskaarten * aantal bezittingen =
// aantal punten
// }
