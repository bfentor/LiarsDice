public class Dice {
    public static void drawDice(int[] num) {
        String[] one = new String[5];
        one[0] = "+-------+";
        one[1] = "|       |";
        one[2] = "|   O   |";
        one[3] = "|       |";
        one[4] = "+-------+";

        String[] two = new String[5];
        two[0] = "+-------+";
        two[1] = "| O     |";
        two[2] = "|       |";
        two[3] = "|     O |";
        two[4] = "+-------+";

        String[] three = new String[5];
        three[0] = "+-------+";
        three[1] = "| O     |";
        three[2] = "|   O   |";
        three[3] = "|     O |";
        three[4] = "+-------+";

        String[] four = new String[5];
        four[0] = "+-------+";
        four[1] = "| O   O |";
        four[2] = "|       |";
        four[3] = "| O   O |";
        four[4] = "+-------+";

        String[] five = new String[5];
        five[0] = "+-------+";
        five[1] = "| O   O |";
        five[2] = "|   O   |";
        five[3] = "| O   O |";
        five[4] = "+-------+";
        
        String[] six = new String[5];
        six[0] = "+-------+";
        six[1] = "| O   O |";
        six[2] = "| O   O |";
        six[3] = "| O   O |";
        six[4] = "+-------+";
        
        String[][] dice = {one,two,three,four,five,six};
        
        draw(dice, num);
    }
    public static int[] mkList(int[] list, int num) {
        for (int i = 0; i < num; i++) {
            list[i] = ((int)(Math.random()*6));
        }
        return list;
    }
    public static void draw(String[][] dice, int[] list) {
        String str = "";
        for (int k = 0; k < (11*list.length)+4; k++) {
            str += "~";
        }
        System.out.println(str);
        for (int i = 0; i < 5; i++) {
            System.out.print("I  ");
            for (int k = 0; k < list.length; k++) {
                System.out.print(dice[list[k]-1][i] + "  ");
            }
            System.out.println("I");
        }
        System.out.println(str);
    }
}
