package hsleiden.stenentijdperk.stenentijdperk.Controllers;

public class BoardController {
    private String[] players;
    private int food;
    private int wood;
    private int clay;
    private int rock;
    private int gold;
    private int tools;
    private int huts;

    public BoardController() {
        this.players={"Matt","Jake"};
        this.food = 53;
        this.wood = 17;
        this.clay = 17;
        this.rock = 17;
        this.gold = 17;
        this.tools = 18;
        this.huts = 28;

        for (String i : this.players) {

        }
        /*
         * voedsel op jacht (53) hout op bos leem op leemgroeve steen op steengroeve
         * goud op rivier (68) gereedschap bij maker (18) vier beschavingskaarten (36)
         * per speler 1 stapel hutten (28) 0 op voedselspoor 0 op scorespoor 5 stamleden
         * 12 voedsel 5 extra stamleden
         */

    }

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