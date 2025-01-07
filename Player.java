import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    int dice;
    int[] faces;
    String name;
    Player(String name) {
        dice = 5;
        throwDice();
        this.name = name;
    }
    public void throwDice() {
        Random rand = new Random();
        int[] arr = new int[dice];
        for (int i = 0; i < dice; i++) {
            arr[i] = rand.nextInt(1, 7);
        }
        faces = arr;
    }
    public void drawDice() {
        Dice.drawDice(faces);
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
        throwDice();
    }
    public int[] choice(int[] bid, int total) {
        System.out.println("================");
        System.out.println("1. Raise bid");
        System.out.println("2. Call bluff");
        System.out.println("3. Call right on");
        System.out.println("0. Quit");
        System.out.print("Selection: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        System.out.println("================");
        
        int[] ret = new int[3];
        //This is a test
        switch (choice) {
            case 1:
                int[] temp;
                while (true) {
                    temp = bid(new int[2], 0);    //The array passed in is only used in the Computer class
                    if (temp[0] > bid[0] || temp[0] == bid[0] && temp[1] != bid[1]) {
                        ret[0] = 1;
                        ret[1] = temp[0];
                        ret[2] = temp[1];
                        break;
                    }
                    System.out.println("Bid must be higher than the previous");
                }
                break;
            case 2:
                ret[0] = 2;
                break;
            case 3:
                ret[0] = 3;
                break;
            case 0:
                System.exit(1);
            default:
                System.out.println("Option not found");
                ret[0] = 0;
                break;
        }
        return ret;
    }
    public int[] bid(int[] bid, int total) {
        System.out.print("Enter bid: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int face = sc.nextInt();
        int[] arr = {num, face};
        return arr;
    }
    
}