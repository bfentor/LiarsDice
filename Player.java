import java.util.Random;
import java.util.Scanner;

public class Player {
    int dice;
    int[] faces;
    String name;
    Player(String name) {
        dice = 5;
        throwDice(dice);
        this.name = name;
    }
    public void throwDice(int dice) {
        Random rand = new Random();
        int[] arr = new int[dice];
        for (int i = 0; i < dice; i++) {
            arr[i] = rand.nextInt(1, 7);
        }
        faces = arr;
    }
    //Getters and setters
    public String getName() {
        return name;
    }
    public int[] getDice() {
        return faces;
    }
    public void loseDie() {
        dice--;
        throwDice(dice);
    }
    public int[] choice() {
        System.out.println("================");
        System.out.println("1. Raise bid");
        System.out.println("2. Call bluff");
        System.out.println("3. Call right on");
        System.out.print("Selection: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        System.out.println("================");
        
        int[] ret = new int[3];
        
        switch (choice) {
            case 1:
                int[] bid = bid();
                ret[0] = 1;
                ret[1] = bid[0];
                ret[2] = bid[1];
                break;
            case 2:
                ret[0] = 2;
                break;
            case 3:
                ret[0] = 3;
                break;
            default:
                System.out.println("Option not found");
                ret[0] = 0;
                break;
        }
        return ret;
    }
    public int[] bid() {
        System.out.print("Enter bid: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int face = sc.nextInt();
        int[] arr = {num, face};
        return arr;
    }
}