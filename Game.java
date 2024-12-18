import java.util.Random;
public class Game {
    final static String ANSI_HOME = "\u001b[H";
    final static String ESC = "\033[";
    final static String cls = ESC + "2J" + ANSI_HOME;
    public static void main(String[] args) {
        Player player = new Player("Player");
        Computer comp1 = new Computer("Computer 1");
        Computer comp2 = new Computer("Computer 2");
        Player[] players = {player, comp1, comp2};
        Random rand = new Random();
        
        //Randomize list
        for (int i = 0; i < players.length; i++) {
            int randIndex = rand.nextInt(players.length);
            Player temp = players[randIndex];
            players[randIndex] = players[i];
            players[i] = temp;
        }
        
        //Bidding
        int[][] bids = new int[players.length][2];
        
        for (int i = 0; i < 3; i++) {
            System.out.println(players[i].getName() + " is bidding");
            bids[i] = players[i].bid();
            System.out.println(bids[i][0] + " " + bids[i][1]);
        }    
    }
}