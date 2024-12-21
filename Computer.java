public class Computer extends Player {
    Computer(String name) {
        super(name);
    }
    public int[] bid() {
        int[] arr = {2, 5};
        return arr;
    }
    public int[] choice(int[] bid) {
        int[] ret = {1, 4, 5};
        return ret;
    }
    public void drawDice() {
        
    }
}