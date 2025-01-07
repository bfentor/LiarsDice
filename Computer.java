import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
// import java.util.Arrays;
//import java.util.LinkedHashMap;
//import java.util.Map;

public class Computer extends Player {
    Computer(String name) {
        super(name);
    }
    public int[] bid(int[] bid, int total) {
        int[] arr = new int[2];
        int[] totals = findTotals(faces);
        
        //Pick face ==================================
        //If else ladder where some part of the chance is determined by
        //a random number and another is determined by how many dice
        //the computer has
        
        double[] percs = new double[6];
        double modifier = 1.2;
        
        for (int i = 0; i < 6; i++) {
            percs[i] = totals[i];
        }
        
        for (int i = 0; i < totals.length; i++) {
            percs[i] /= (double)dice;
            percs[i] += 0.2;
            if (percs[i] > 0.4)
                if (bid[0] > total/4)
                    percs[i] *= modifier*1.5;
                else 
                    percs[i] *= modifier;
        }
        
        for (double i : percs)
            System.out.print(i + " ");
        System.out.println();
        
        double tot = 0.0;
        for (int i = 0; i < totals.length; i++) {
            tot += percs[i];
            percs[i] = tot;
        }
        
        for (double i : percs)
            System.out.print(i + " ");
        System.out.println();
        
        //Decision
        Random r = new Random();
        double dec = r.nextDouble() * percs[5];
        for (int i : totals)
            System.out.print(i + " ");
        System.out.println();
        System.out.println(dec);
        
        if (dec < percs[0]) {
            System.out.println("1");
            arr[1] = 1;
        } else if (dec < percs[1]) {
            System.out.println("2");
            arr[1] = 2;
        } else if (dec < percs[2]) {
            System.out.println("3");
            arr[1] = 3;
        } else if (dec < percs[3]) {
            System.out.println("4");
            arr[1] = 4;
        } else if (dec < percs[4]) {
            System.out.println("5");
            arr[1] = 5;
        } else if (dec < percs[5]) {
            System.out.println("6");
            arr[1] = 6;
        }
       
        //Pick number of dice ==========================
        
        int num = bid[0];
        double rand = r.nextDouble() * 10;
        
        if (totals[arr[1]-1] >= bid[0])
            num += 2;
        else if (totals[arr[1]] == 0 && rand < 4)
            num += 1;
        else if (rand >= 4 && rand < 9)
            num += 0;
        else if (rand >= 9)
            num += 2;
        
        arr[0] = num;
        return arr;
    }
    public int[] findTotals(int[] inputArray) {
        // Determine the maximum value in the input array to define the array size
        int maxValue = 6;

        // Create an array to store the counts, initialized to 0
        int[] counts = new int[maxValue];

        // Count the occurrences of each number
        for (int num : inputArray) {
            counts[num - 1]++;
        }
        
        // Create the 2D array for the result
        int[] result = new int[6];
        //int index = 0;
        
        //for (int i = 0; i < 6; i++) result[i][0] = i + 1;
        
        // Fill the 2D array with the dice values and their counts
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                //result[index][0] = i + 1; // Die value (1-based index)
                result[i] = counts[i]; // Frequency
                //index++;
            }
        }

        return result;
    }
    public int[] choice(int[] bid, int total) {
        int choice;
        
        //TODO: Choice semi randomly based on previous bid and total dice
        
        int[] bd = bid(bid, total);
        int[] ret = {1, bd[0], bd[1]};
        return ret;
    }
    public void drawDice() {
         
    }
    public void test() {
        int[] test = {1, 1, 1, 4, 2, 3, 3};
        
        System.out.println(test);
        
        for (int num : findTotals(test)) {
                System.out.print(num + " ");
        }
        System.out.println();
    }
}