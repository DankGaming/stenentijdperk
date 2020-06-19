package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Dobbelsteen;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardController {
    private PlayerController playercontroller;
    private BoardModel boardmodel;
    private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();

    public BoardController() {
        // temp players
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
        boardmodel.setPlayer(players.get(0)); // Begin van het spel turn eerste speler bepalen.
        System.out.println(boardmodel.getPlayer().getNaam() + " is aan de beurt en heeft "
                + boardmodel.getPlayer().getVillagers() + ".");
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

    public String getKaartPath(int index) {
        return this.boardmodel.getKaartPath(index);
    }

    public void onResourceButtonClick(int location) {
        if (vraagPhase() == 1) {
            if (!boardmodel.getPlaced() && boardmodel.requestCap(location) - boardmodel.requestVillagers(location) != 0
                    && playercontroller.getPosities(boardmodel.getPlayer(), location) == 0) {
                String input;
                do {
                    input = scanner("Hoeveel stamleden?");
                    // hoeveel passen op de locatie
                } while (Integer.parseInt(input) <= 0
                        || Integer.parseInt(input) > playercontroller.getVillagers(boardmodel.getPlayer())
                        || Integer.parseInt(
                                input) > (boardmodel.requestCap(location) - boardmodel.requestVillagers(location)));
                // Dit veranderd de hoeveelheid stamleden van een speler
                boardmodel.changeVillagers(location, Integer.parseInt(input));
                plaatsenStamleden(location, Integer.parseInt(input));
            }
        } else {
            afhandelenResource(location);
        }
    }

    public void onButtonClick(int index){
        if (vraagPhase() == 1){
            if (locatieVrij(index) && !boardmodel.getPlaced()){
                if (index == 6) {
                    plaatsenStamleden(index, 2);
                } else {
                    plaatsenStamleden(index, 1);
                }
            }
        } else{
            switch (index){
                case 5:
                    akkerBouw(index);
                    break;
                case 6:
                    stamledenHut(index);
                    break;
                case 7:
                    // TODO tools krijgen
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                    // TODO hutje actie logica
                    break;
                case 12:
                case 13:
                case 14:
                case 15:
                    // TODO kaarten actie logica
                    break;
            }
        }
    }

    // Hier is het rollen voor resources.
    public void afhandelenResource(int index) {
        if (playercontroller.getPosities(boardmodel.getPlayer(), index) != 0) {
            Dobbelsteen roll = new Dobbelsteen(playercontroller.getPosities(boardmodel.getPlayer(), index));
            roll.worp();
            roll.berekenTotaal();
            int resources = roll.getTotaal() / boardmodel.getResource(index).getWaarde();
            if (resources > boardmodel.getResource(index).getHoeveelheid()){
                resources = boardmodel.getResource(index).getHoeveelheid();
            }
            boardmodel.getResource(index).reduceHoeveelheid(resources);
            playercontroller.setPosities(boardmodel.getPlayer(), index, 0);
            // Test voor krijgen resources
            boardmodel.getPlayer().addResources(index, resources);
            System.out.println(boardmodel.getPlayer().getResources(index));
        }
    }

    public void akkerBouw(int index){
        if (playercontroller.getPosities(boardmodel.getPlayer(), index) != 0 && playercontroller.vraagGraan(boardmodel.getPlayer()) != 10){
            playercontroller.addGraan(boardmodel.getPlayer());
            playercontroller.setPosities(boardmodel.getPlayer(), index, 0);

        }
    }

    public void stamledenHut(int index){
        if (playercontroller.getPosities(boardmodel.getPlayer(), index) != 0 && playercontroller.getMaxVillagers(boardmodel.getPlayer()) != 10){
            playercontroller.addMaxVillagers(boardmodel.getPlayer());
            playercontroller.setPosities(boardmodel.getPlayer(), index, 0);
        }
    }
    // TODO endturn aanpasses zodat het in phase 2 werkt.
    // List.matchAll stream
    public void endTurn() {
        if (boardmodel.getPlaced()) { // checkt of de speler stamleden heeft geplaast.
            System.out.println("einde beurt");
            boolean villagersLeft = true;
            int i = 0;
            for (int j = 0; j < 4; j++) {
                if (boardmodel.getPlayer().equals(players.get(j))) { // Bepaling welke player aan de beurt is
                    i = j;
                    break;
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
                boardmodel.setPhase(2);
                // TODO Dit moet een soort pop up worden.
                System.out.println("Nu komen de acties");
            }
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
                break;
            }
        }
        return found;
    }
    
    public boolean locatieVrij(int index){
        boolean status = true;
        for (PlayerModel player: players){
            if(player.getPosities(index) != 0){
                status = false;
            }
        }
        return status;
        
    }

    public void plaatsenStamleden(int index, int stamleden){
        boardmodel.setPlaced(true);
        playercontroller.setVillagers(boardmodel.getPlayer(),
            (playercontroller.getVillagers(boardmodel.getPlayer()) - stamleden));
        playercontroller.setPosities(boardmodel.getPlayer(), index, stamleden);
    }

    public void toolGebruiken(){
        // TODO
    }

    public int vraagPhase(){
        return boardmodel.getPhase();
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
