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
    //Getters
    public String getName() {
        return name;
    }
    
    public int[] bid() {
        System.out.println("Enter bid");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int face = sc.nextInt();
        int[] arr = {num, face};
        return arr;
    }
}