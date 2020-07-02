package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Dobbelsteen;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Views.BoardView;
import hsleiden.stenentijdperk.stenentijdperk.Views.TableauView;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObserver;

import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private PlayerController playercontroller;
    private BoardModel boardmodel;
    // TODO naar boardmodel en dan firebase
    private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
    private int[] gegooideWorp;

    public BoardController() {
        // TODO all references naar temp players moet naar firebase vragen.
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
        gegooideWorp = new int[3];
    }

    public void registerObserver(BoardObserver boardobserver) {
        this.boardmodel.register(boardobserver);
    }

    public Kaart getKaart(int index) {
        return this.boardmodel.getKaart(index);
    }

    public StaticHut getHut(int stapel) {
        return this.boardmodel.getHut(stapel);
    }

    public List<StaticHut> getHutStapel(int stapel) {
        return this.boardmodel.getHutStapel(stapel);
    }


    /**
     * De functie handelt af op welke was geklikt en behandeld de stamleden.
     * 
     * @param index de button, dus positie, van de stamleden.
     * @param input de hoeveelheid stamleden die de speler wil plaatsen.
     * @see Resource
     * @see PlayerModel
     * @author Damien Hofman.
     */

    public void onResourceButtonClick(int index, int input) {
        if (!boardmodel.getPlaced() && boardmodel.requestCap(index) - boardmodel.requestVillagers(index) != 0
                && playercontroller.getPositie(boardmodel.getPlayer(), index) == 0) {
            // Dit veranderd de hoeveelheid stamleden van een speler
            boardmodel.decreaseVillagers(index, input);
            plaatsenStamleden(index, input);
        }
    }

    /**
     * Deze functie controleert of het ingevuld aantal stamleden wel een geldige hoeveelheid is
     * @param index de button, dus posities, waarop op de speler stamleden wil plaatsen
     * @param input de hoeveelheid stamleden die de speler heeft ingevoerd
     * @return true of false bepaald door te controleren of er nog ruimte is, en of de speler wel genoeg stamleden over heeft
     * @author Damien Hofman
     */

    public boolean stamledenCheck(int index, int input) {
        return (input > 0 && input <= playercontroller.getVillagers(boardmodel.getPlayer())
                && input <= (boardmodel.requestCap(index) - boardmodel.requestVillagers(index)));
    }

    // methode om de onderste buttons af te handelen. maakt de kaart/hut bezet en
    // zorgt dat je niet meer kan plaatsen.
    public List<Kaart> onKaartButtonClick(int index) {
        return (boardmodel.removeKaart(index)); // TODO dit moet naar acties verplaatst worden
    }

    /**
     * Deze functie controleert in welke phase van het spel we zitten.
     * @param index de button, dus positie,waarop op de speler heeft geklikt.
     * @see BoardModel
     * @author Damien Hofman
     */

    public void onButtonClick(int index) {
        if (vraagPhase() == 1) {
            buttonCheckPhase1(index);
        } else {
            buttonCheckPhase2(index);
        }
    }

    /**
     * De functie handeld af het rollen voor resources.
     * Eerste controllert het of de speler wel stamleden op die positie heeft.
     * Daarna wordt er een dobbelsteen object gemaakt om te rollen.
     * Dan wordt het tabbleau geopened als de speler tools beschikbaar heeft en kan ze dan gebruiken.
     * @param index welke resource het was.
     * @see Dobbelsteen
     * @see TableauView
     * @author Damien Hofman
     */
     

    public void resolveResource(int index) {
        gegooideWorp[0] = index;
        int stamleden = playercontroller.getPositie(boardmodel.getPlayer(), index);
        if (stamleden != 0) {
            Dobbelsteen roll = new Dobbelsteen(stamleden);
            roll.worp();
            roll.berekenTotaal();
            gegooideWorp[1] = roll.getTotaal();
            gegooideWorp[2] = stamleden;
            if (playercontroller.getTools(boardmodel.getPlayer()).size() != 0 && checkTools()) {
                ViewManager.loadPopupWindow(new TableauView(boardmodel.getPlayer(), this, gegooideWorp[1]).setScene());
            } else {
                toolsGebruiken(0);
            }
        }
    }
    
    /**
     * Deze functie handeld af het gebruik van tools en het geven van resources.
     * De tools worden opgeteld bij de roll.
     * Vervolgens wordt het totaal gedeeld door de waarde van de de resource.
     * Dit wordt dan aan de speler gegevenen.
     * @param waarde de waarde van de gebruikte tools.
     * @see Tool
     * @author Damien Hofman
     */

    public void toolsGebruiken(int waarde) {
        int index = gegooideWorp[0];
        int roltotaal = gegooideWorp[1] + waarde;
        int stamleden = gegooideWorp[2];
        int resources = roltotaal / boardmodel.getResource(index).getWaarde();
        if (resources > boardmodel.getResource(index).getHoeveelheid()) {
            resources = boardmodel.getResource(index).getHoeveelheid();
        }
        boardmodel.reduceResources(index, resources);
        playercontroller.setPositie(boardmodel.getPlayer(), index, 0);
        boardmodel.getPlayer().addResources(index, resources);
        boardmodel.getLocaties().get(index).reduceVillager(stamleden);
    }

    /**
     * Deze functie loop door de tools van een speler en controleerd of deze nog te gebruiken is.
     * @return dit returned of de speler nog tools kan gebruiken.
     * @see Tool
     * @author Damien Hofman
     */
    private boolean checkTools() {
        boolean toolsLeft = false;
        for (Tool tool : playercontroller.getTools(boardmodel.getPlayer())) {
            if (tool.getStatus()) {
                toolsLeft = true;
            }
        }
        return toolsLeft;
    }

    /**
     * Dit geeft de speler tools.
     * Als de speler minder dan 3 tools heeft dan krijgt de speler een nieuwe.
     * Anders wordt het niveau van de laagste level tool met 1 verhoogd.
     * @param index de locatie van de button.
     * @see BoardModel
     * @see Tool
     * @author Damien Hofman
     */

    private void gainTools(int index) {
        if ((playercontroller.getPositie(boardmodel.getPlayer(), index) != 0)) {
            playercontroller.setPositie(boardmodel.getPlayer(), index, 0);
            ArrayList<Tool> tools = playercontroller.getTools(boardmodel.getPlayer());
            if (tools.size() < 3) {
                playercontroller.addTool(boardmodel.getPlayer());
                playercontroller.setPositie(boardmodel.getPlayer(), index, 0);
            } else if (tools.get(2).getLevel() != 4) {
                for (int i = 0; i < 3; i++) {
                    if (tools.get(i).getLevel() == tools.get(2).getLevel()) {
                        tools.get(i).increaseLevel();
                        break;
                    }
                }
            }
        }
    }

    public void endTurn() {
        if (boardmodel.getPlaced()) { // checkt of de speler stamleden heeft geplaast.
            boolean villagersLeft = true;
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
                int turnCheck = (boardmodel.getTurn() - 1) % 4;
                boardmodel.setPlayer(players.get(turnCheck));
                // TODO Dit moet een soort pop up worden.
                System.out.println("Nu komen de acties");
            }
        }
    }
    

    public void EndTurnPhase2() {
        List<Integer> posities = playercontroller.vraagPosities(boardmodel.getPlayer());
        if (posities.stream().allMatch(n -> n == 0)) {
            int i = checkPlayer();
            if (i == 3) {
                boardmodel.setPlayer(players.get(0));
            } else {
                i++;
                boardmodel.setPlayer(players.get(i));
            }
            posities = playercontroller.vraagPosities(boardmodel.getPlayer());
        }

        if (posities.stream().allMatch(n -> n == 0)) {
            System.out.println("Einde Ronde");
            boardmodel.setPhase(1);
            for (PlayerModel player : players) {
                List<Integer> resources = playercontroller.vraagResources(player);
                int remaining = voedselBetalen(player);
                for (int j = 1; j < resources.size(); j++) {
                    if (remaining != 0 && !(resources.stream().allMatch(n -> n == 0))) {
                        for (int k = resources.get(j); k > 0; k--) {
                            if (remaining != 0) {
                                remaining -= 1;
                                playercontroller.reduceResource(player, j, 1);
                                boardmodel.addResources(j, 1);
                            } else {
                                break;
                            }
                        }
                    } else if (resources.stream().allMatch(n -> n == 0)) {
                        player.setPunten(player.getPunten() - 10);
                        break;
                    } else {
                        break;
                    }
                }
                playercontroller.setVillagers(player, playercontroller.getMaxVillagers(player));
            }
            if (checkWincondition()) {
                endGame();
            }  
        }
    }

    public int vraagPhase() {
        return boardmodel.getPhase();
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

    private void buttonCheckPhase1(int index) {
        if (locatieVrij(index) && !boardmodel.getPlaced()) {
            if (index == 6 && playercontroller.getVillagers(boardmodel.getPlayer()) >= 2) {
                plaatsenStamleden(index, 2);
            } else if (index != 6) {
                plaatsenStamleden(index, 1);
            }
        }
    }

    private void buttonCheckPhase2(int index) {
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
                hutActie(index-8);
                break;
            case 12:
            case 13:
            case 14:
            case 15:
                // TODO kaarten actie logica
                break;
        }
    }

    private void hutActie(int index) {
        if ((playercontroller.getPositie(boardmodel.getPlayer(), (index + 8)) != 0)) {
            playercontroller.setPositie(boardmodel.getPlayer(), (index + 8), 0);
            if (resourcesBetalen(this.boardmodel.getHut(index).getKosten())) {
                this.boardmodel.getPlayer()
                        .setPunten(this.boardmodel.getPlayer().getPunten() + this.boardmodel.getHut(index).getPunten());
                this.boardmodel.getPlayer().addHutjes(this.boardmodel.getHut(index));
                boardmodel.removeHut(index);
            } else {
                System.out.println("niet genoeg resources");
                // TODO deze else verbeteren
            }
        }
    }

    private int voedselBetalen(PlayerModel player) {
        int remaining = 0;
        int voedselNodig = playercontroller.getMaxVillagers(player) - playercontroller.vraagGraan(player);
        int voedselSpeler = playercontroller.vraagResources(player).get(0);
        if (voedselSpeler >= voedselNodig) {
            playercontroller.reduceResource(player, 0, voedselNodig);
            boardmodel.addResources(0, voedselNodig);
        } else {
            playercontroller.reduceResource(player, 0, voedselSpeler);
            remaining = voedselNodig - voedselSpeler;
            boardmodel.addResources(0, voedselSpeler);
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

    private boolean checkResourceKost(int resource, List<Integer> kost) {
        return boardmodel.getPlayer().getResource(resource + 1) >= kost.get(resource);
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

    private boolean resourcesBetalen(List<Integer> kost) {
        if (checkResourceKost(0, kost) && checkResourceKost(1, kost) && checkResourceKost(2, kost)
                && checkResourceKost(3, kost)) {
            int i = 1;
            for (Integer amount : kost) {
                playercontroller.reduceResource(boardmodel.getPlayer(), i, amount);
                boardmodel.changeHoeveelheid(i, amount);
                i++;
            }
            return true;
        } else {
            return false;
        }
    }

    private void endGame() {
        for (PlayerModel player : players) {
            finalPuntenCount(player);
        }
        
    }

    private boolean checkWincondition(){
        boolean endGame = false;
        for (int i = 0; i < 4; i++) {
            if (boardmodel.getHutStapel(i).size() == 0) {
                endGame = true;
            }
        }
        if (boardmodel.getKaarten().size() > 4) {
            endGame = true;
        }

        return endGame;
    }

    private void finalPuntenCount(PlayerModel player) {
        List<Integer> multipliers = playercontroller.getMultiplier(player);
        for (Tool tool : playercontroller.getTools(player)) {
            playercontroller.increasePunten(player, multipliers.get(0) * tool.getLevel());
        }
        playercontroller.increasePunten(player, multipliers.get(1) * playercontroller.getHutjes(player).size());
        playercontroller.increasePunten(player, multipliers.get(2) * playercontroller.getMaxVillagers(player));
        playercontroller.increasePunten(player, multipliers.get(3) *playercontroller.vraagGraan(player));
        playercontroller.increasePunten(player, 
            playercontroller.getTreasures(player).size() * playercontroller.getTreasures(player).size());
    }

    // TODO tijdelijk
    public ArrayList<PlayerModel> getPlayers() {
        return this.players;
    }

    public PlayerModel getPlayer() {
        return boardmodel.getPlayer();
    }
}
