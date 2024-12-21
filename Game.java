import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
        
        Collections.shuffle(players, new Random());
        
        int[] bid = new int[2];
        
        //Game loop
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                if (bid[0] == 0) {
                    System.out.println("Initial Bid");
                    players.get(i).drawDice();
                    bid = players.get(i).bid();
                    System.out.printf("\n%s bid %d %d\n", players.get(i).getName(), bid[0], bid[1]);
                    continue;
                }
                players.get(i).drawDice();
                int[] ret = players.get(i).choice(bid);
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
                        for (Player obj1 : players)
                            obj1.throwDice();
                        break;
                    case 3:
                        int total = checkDice(players, bid);
                        if (i-1 == -1)
                            prev = players.size() - 1;
                        else
                            prev = i-1;
                        
                        if (total == bid[0]) {
                            System.out.printf("Everyone but %s loses a die. There are exactly %d %ds\n", players.get(i).getName(), total, bid[1]);
                            for (Player obj0 : players) {
                                if (obj0.getName().equals(players.get(i).getName()))
                                    continue;
                                obj0.loseDie();
                            }
                        } else {
                            System.out.printf("%s loses a die. There are %d %ds\n", players.get(i).getName(), total, bid[1]);
                            players.get(i).loseDie();
                        }
                        for (Player obj1 : players)
                            obj1.throwDice();
                        break;
                }
                for (int l = 0; l < players.size(); l++) {
                    if (players.get(l).getDice().length == 0) {
                        System.out.printf("%s is out of dice\n", players.get(l).getName());
                        players.remove(l);
                        if (players.size() == 1) {
                            System.out.printf("%s wins!\n", players.get(0).getName());
                            System.exit(1);
                        }
                    }
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
        for (Player player : players) {
            faces = player.getDice();
            
            for (int face : faces) {
                if (face == bid[1])
                    total++;
            }
        }
        return total;
    }
}