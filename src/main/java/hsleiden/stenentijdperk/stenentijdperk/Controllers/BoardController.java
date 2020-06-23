package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Dobbelsteen;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObserver;

import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private PlayerController playercontroller;
    private BoardModel boardmodel;
    // TODO naar boardmodel en dan firebase
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
    }

    public void registerObserver(BoardObserver boardobserver) {
        this.boardmodel.register(boardobserver);
    }

    public Kaart getKaart(int index) {
        return this.boardmodel.getKaart(index);
    }

    public void onResourceButtonClick(int location, int input) {
        if (!boardmodel.getPlaced() && boardmodel.requestCap(location) - boardmodel.requestVillagers(location) != 0
                 && playercontroller.getPositie(boardmodel.getPlayer(), location) == 0) {
            // Dit veranderd de hoeveelheid stamleden van een speler
            boardmodel.changeVillagers(location, input);
            plaatsenStamleden(location, input);
        }
    }

    public boolean stamledenCheck(int location, int input) {
         return (input > 0
                && input <= playercontroller.getVillagers(boardmodel.getPlayer())
                && input <= (boardmodel.requestCap(location) - boardmodel.requestVillagers(location)));
    }

    // methode om de onderste buttons af te handelen. maakt de kaart/hut bezet en
    // zorgt dat je niet meer kan plaatsen.
    public ArrayList<Kaart> onKaartButtonClick(int index) {
        return (boardmodel.removeKaart(index)); // dit moet naar acties verplaatst worden
    }

    public void onButtonClick(int index) {
        if (vraagPhase() == 1) {
            if (locatieVrij(index) && !boardmodel.getPlaced()) {
                if (index == 6 && playercontroller.getVillagers(boardmodel.getPlayer()) >= 2) {
                    plaatsenStamleden(index, 2);
                } else if (index == 5 || index == 7) {
                    plaatsenStamleden(index, 1);
                }
            }
        } else {
            switch (index) {
                case 5:
                    moreAgriculture(index);
                    break;
                case 6:
                    moreVillagerHut(index);
                    break;
                case 7:
                    gainTools(index);
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
    public void resolveResource(int index) {
        if (playercontroller.getPositie(boardmodel.getPlayer(), index) != 0) {
            Dobbelsteen roll = new Dobbelsteen(playercontroller.getPositie(boardmodel.getPlayer(), index));
            roll.worp();
            roll.berekenTotaal();
            int resources = roll.getTotaal() / boardmodel.getResource(index).getWaarde();
            if (resources > boardmodel.getResource(index).getHoeveelheid()) {
                resources = boardmodel.getResource(index).getHoeveelheid();
            }
            boardmodel.getResource(index).reduceHoeveelheid(resources);
            playercontroller.setPositie(boardmodel.getPlayer(), index, 0);
            boardmodel.getPlayer().addResources(index, resources);
        }
    }

    private void moreAgriculture(int index) {
        if (playercontroller.getPositie(boardmodel.getPlayer(), index) != 0
                && playercontroller.vraagGraan(boardmodel.getPlayer()) != 10) {
            playercontroller.addGraan(boardmodel.getPlayer());
            playercontroller.setPositie(boardmodel.getPlayer(), index, 0);

        }
    }

    private void moreVillagerHut(int index) {
        if (playercontroller.getPositie(boardmodel.getPlayer(), index) != 0
                && playercontroller.getMaxVillagers(boardmodel.getPlayer()) != 10) {
            playercontroller.addMaxVillagers(boardmodel.getPlayer());
            playercontroller.setPositie(boardmodel.getPlayer(), index, 0);
        }
    }

    private void gainTools(int index) {
        if((playercontroller.getPositie(boardmodel.getPlayer(), index) != 0)){
            ArrayList<Tool> tools = playercontroller.getTools(boardmodel.getPlayer());  
            if (tools.size() < 3) {
                playercontroller.addTool(boardmodel.getPlayer());
            } else if (tools.get(2).getLevel() != 4) {
                for (int i = 0; i < 3; i++){
                    if (tools.get(i).getLevel() == tools.get(2).getLevel()) {
                        tools.get(i).increaseLevel();
                        playercontroller.setPositie(boardmodel.getPlayer(), index, 0);
                        break;
                    }
                }
            }
        }

    }

    public void endTurn() {
        if (boardmodel.getPlaced()) { // checkt of de speler stamleden heeft geplaast.
            boolean villagersLeft = true;
            System.out.println("Einde beurt");
            int i = checkPlayer();
            switch (i) { // Verschillede loops bepaalt door welke speler aan de beurt was
                case 0: // Spelers 1, 2 en 3
                case 1:
                case 2:
                    villagersLeft = loopPlayers(i, players);
                    boardmodel.setPlaced(false);
                    if (!villagersLeft) { // Als de vorige loop niks gevonden heeft dan komt deze pas
                        villagersLeft = loopPlayers(-1, players.subList(0, i + 1));
                    }
                    break;
                case 3:
                    villagersLeft = loopPlayers(i - 4, players);
                    boardmodel.setPlaced(false);
                    break;
            }
            if (!villagersLeft) {
                boardmodel.setPhase(2);
                int turnCheck = (boardmodel.getTurn() -1) % 4;
                boardmodel.setPlayer(players.get(turnCheck));
                // TODO Dit moet een soort pop up worden.
                System.out.println("Nu komen de acties");
            }
        }
    }

    public void EndTurnPhase2() {
        List<Integer> resources = playercontroller.vraagResources(boardmodel.getPlayer());
        if (resources.stream().allMatch(n -> n == 0)) {
            int i = checkPlayer();
            if (i == 3) {
                boardmodel.setPlayer(players.get(0));
            } else {
                i++;
                boardmodel.setPlayer(players.get(i));
            }
            resources = playercontroller.vraagResources(boardmodel.getPlayer());
        }
        if (resources.stream().allMatch(n -> n == 0)) {
            System.out.println("Einde Ronde");
            boardmodel.setPhase(1); 
            for (PlayerModel player : players){
                int remaining = voedselBetalen(player);
                for (int j = 0; j < resources.size(); j++) {
                    
                }
                playercontroller.setVillagers(player, playercontroller.getMaxVillagers(player));
            }
            
        }
        System.out.println("Eind actie beurt");
    }

    private int voedselBetalen(PlayerModel player){
        int remaining = 0;
        int voedselNodig = playercontroller.getMaxVillagers(player) - playercontroller.vraagGraan(player);
        int voedselSpeler = playercontroller.vraagResources(player).get(0);
                if (voedselSpeler > voedselNodig){
                    playercontroller.reduceResource(player, 0, voedselNodig);
                } else {
                    playercontroller.reduceResource(player, 0, voedselSpeler);
                    remaining = voedselNodig - voedselSpeler;
                }
        return remaining;
    }
    // Methode om door lijsten spelers te loopen.
    private boolean loopPlayers(int start, List<PlayerModel> player) {
        boolean found = false;
        for (int j = start + 1; j < player.size(); j++) {
            if (playercontroller.getVillagers(player.get(j)) != 0) {
                boardmodel.setPlayer(player.get(j)); // Veranderd de huidige speler
                found = true;
                break;
            }
        }
        return found;
    }

    private int checkPlayer() {
        int i = 0;
        for (int j = 0; j < 4; j++) {
            if (boardmodel.getPlayer().equals(players.get(j))) { // Bepaling welke player aan de beurt is
                i = j;
                break;
            }
        }
        return i;
    }

    private boolean locatieVrij(int index) {
        boolean status = true;
        for (PlayerModel player : players) {
            if (player.getPositie(index) != 0) {
                status = false;
            }
        }
        return status;

    }

    private void plaatsenStamleden(int index, int stamleden) {
        boardmodel.setPlaced(true);
        playercontroller.setVillagers(boardmodel.getPlayer(),
                (playercontroller.getVillagers(boardmodel.getPlayer()) - stamleden));
        playercontroller.setPositie(boardmodel.getPlayer(), index, stamleden);
    }

    private void betalenResources(List<Integer> kost){
        int i = 1;
        for (Integer resources: kost){
            boardmodel.getPlayer().reduceResources(i, resources);
            i ++;
        }
    }

    public void toolGebruiken() {
        // TODO tools stuff
        ArrayList<Tool> tools = playercontroller.getTools(boardmodel.getPlayer());
        for (Tool tool: tools){
            if (tool.getStatus()){
                // TODO Show tool in the pop up
            }
        }
    }

    public int vraagPhase() {
        return boardmodel.getPhase();
    }
}