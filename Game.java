import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Game {
    final static String ANSI_HOME = "\u001b[H";
    final static String ESC = "\033[";
    final static String cls = ESC + "2J" + ANSI_HOME;
    public static void main(String[] args) {
        Player player = new Player("Player");
        Computer comp1 = new Computer("Computer 1");
        Computer comp2 = new Computer("Computer 2");
        List<Player> players = new ArrayList<Player>();
        players.add(player);
        players.add(comp1);
        players.add(comp2);
        Random rand = new Random();
        
        //Randomize list
        for (int i = 0; i < players.size(); i++) {
            int randIndex = rand.nextInt(players.size());
            Player temp = players.get(randIndex);
            players.set(randIndex, players.get(i));
            players.set(i, temp);
        }
        
        //Bidding
        //int[][] bids = new int[players.length][2];
        
        int[] bid = new int[2];
        
        //Game loop
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                if (bid[0] == 0) {
                    bid = players.get(i).bid();
                    System.out.printf("\n%s bid %d %d\n", players.get(i).getName(), bid[0], bid[1]);
                    continue;
                }
                int[] ret = players.get(i).choice();
                switch (ret[0]) {
                    case 0:
                        i--;
                        continue;
                    case 1:
                        bid[0] = ret[1];
                        bid[1] = ret[2];
                        System.out.printf("\n%s bid %d %d\n", players.get(i).getName(), ret[1], ret[2]);
                        break;
                    case 2:
                        System.out.printf("%s calls bluff\n", players.get(i).getName());
                        int prev;
                        if (i-1 == -1)
                            prev = players.size() - 1;
                        else
                            prev = i-1;
                        
                        int[] bluff = callBluff(players, bid);
                        boolean isBluff;
                        if (bluff[0] == 1) {
                            System.out.printf("%s loses a die. Only %d %ds\n", players.get(prev).getName(), bluff[1], bid[1]);
                            players.get(prev).loseDie();
                            isBluff = true;
                        } else {
                            System.out.printf("%s loses a die. At least %d %ds\n", players.get(i).getName(), bluff[1], bid[1]);
                            players.get(i).loseDie();
                            isBluff = false;
                        }
                        int num;
                        if (isBluff)
                            num = prev;
                        else 
                            num = i;
                        if (players.get(num).getDice().length == 0) {
                            System.out.printf("%s is out of dice\n", players.get(num).getName());
                            players.remove(num);
                            if (players.size() == 1) {
                                System.out.printf("%s wins!", players.get(0).getName());
                                System.exit(1);
                            }
                        }
                        break;
                    case 3:
                        int[] rightOn = callRightOn(players, bid);
                        
                        break;
                }
            }
        }
    }
    public static int[] callBluff(List<Player> players, int[] bid) {
        int total = checkDice(players, bid);
        int[] ret = new int[2];
        if (total < bid[0]) {
            ret[0] = 1;
            ret[1] = total;
        } else {
            ret[0] = 0;
            ret[1] = total;
        }
        return ret;
    }
    public static int[] callRightOn(List<Player> players, int[] bid) {
        int total = checkDice(players, bid);
        int[] ret = new int[2];        
        if (total == bid[0]) {
            ret[0] = 1;
            ret[1] = total;
        } else {
            ret[0] = 0;
            ret[1] = total;
        }
        return ret;
    }
    public static int checkDice(List<Player> players, int[] bid) {
        int[][] all = new int[players.size()][5];
        int total = 0;
        int[] faces;
        for (int i = 0; i < players.size(); i++) {
            faces = players.get(i).getDice();
            
            for (int face : faces) {
                if (face == bid[1])
                    total++;
            }
        }
        return total;
    }
}