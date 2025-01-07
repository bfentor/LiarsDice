import java.util.List;
//import java.util.ArrayList;
import java.util.stream.Collectors;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.LinkedHashMap;

public class mode {
    public static void main(String[] args) {
        test();
    }
    public static int[] findTotals(int[] inputArray) {
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
    public void drawDice() {
         
    }
    public static void test() {
        int[] test = {1, 1, 1, 4, 2, 3, 3, 5, 6};
        for (int num : findTotals(test)) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static List<Integer> getUnique(List<Integer> l) {
        return l.stream().distinct().collect(Collectors.toList()); 
    }
}