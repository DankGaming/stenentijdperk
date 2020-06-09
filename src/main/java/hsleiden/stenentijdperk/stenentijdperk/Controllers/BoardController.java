package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import hsleiden.stenentijdperk.stenentijdperk.Views.BoardView;
import java.util.Scanner;

public class BoardController {
    private BoardModel boardmodel;
    private BoardView boardview;
    private String[] players;

    public BoardController() {
        boardmodel = new BoardModel();
        players = boardmodel.getPlayers();
        for (String i : players) {
            System.out.println(i);
        }
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

    public void onVillagerButtonClick() {
        if (boardmodel.getPlaceable()) {
            String input;
            do {
                input = scanner("Hoeveel stamleden?");
            } while (Integer.parseInt(input) > boardmodel.getVillagers());
            System.out.println("placed " + input + " villager(s)");
        } else {
            System.out.println("false");
        }
    }

    /*
     * public void endTurn(player){
     * 
     * }
     */

    public MainLoop(){
        while(!wincondition){
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
        }
    
        // if beschavinskaarten < 4 , einde spel
        // if (1 stapel hutten is leeg) , einde spel (na de ronde)
        // elke grondstof is 1 punt
        // aantal verschillende groene beschavingskaarten ^2 = aantal punten
        // aantal symbolen op zandkleurige beschavinskaarten * aantal bezittingen =
        // aantal punten
    }

}