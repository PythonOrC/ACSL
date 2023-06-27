import java.util.Arrays;

public class Targets {
    public static void main(String[] args) {
        System.out.println(missingArrow(4, "02 11 20 21", "0103 1012", "13E 30B 33E"));
        
    }

    public static String missingArrow(int size, String tar, String num, String arr) {
        int[][] matrix = new int[size][size];
        String[] targets = tar.split(" ");
        char[] numbers = num.replace(" ", "").toCharArray();

        int[][] border = new int[2][size];
        for (int i = 0; i < size; i++) {
            border[1][i] = Integer.parseInt(String.valueOf(numbers[i]));
            border[0][i] = Integer.parseInt(String.valueOf(numbers[i + size]));
        }
        String[] arrows = arr.split(" ");
        for (String target : targets) {
            int row = Integer.parseInt(target.substring(0, 1));
            int col = Integer.parseInt(target.substring(1, 2));
            matrix[row][col] = 1;
        }
        for (String arrow : arrows) {
            int row = Integer.parseInt(arrow.substring(0, 1));
            int col = Integer.parseInt(arrow.substring(1, 2));
            String dir = arrow.substring(2, 3);
            border[1][row] -= 1;
            border[0][col] -= 1;
            int col_inc = 0;
            int row_inc = 0;
            switch (dir) {
                case "A":
                    col_inc = -1;
                    break;
                case "B":
                    row_inc = -1;
                    break;
                case "C":
                    col_inc = 1;
                    break;
                case "D":
                    row_inc = 1;
                    break;
                case "E":
                    col_inc = -1;
                    row_inc = -1;
                    break;
                case "F":
                    col_inc = 1;
                    row_inc = -1;
                    break;
                case "G":
                    col_inc = 1;
                    row_inc = 1;
                    break;
                case "H":
                    col_inc = -1;
                    row_inc = 1;
                    break;
            }
            while (row >= 0 && row < size && col >= 0 && col < size) {
                if (matrix[row][col] == 1) {
                    matrix[row][col] = 0;
                    break;
                }
                row += row_inc;
                col += col_inc;
            }
        }
        int a_x=0;
        int a_y=0;
        for (int i = 0; i < size; i++) {
            if (border[0][i] != 0)
                a_x = i;
        }
        for (int i = 0; i < size; i++) {
            if (border[1][i] != 0)
                a_y = i;
        }
        int t_x=0;
        int t_y=0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 1) {
                    t_x = j;
                    t_y = i;
                }
            }
        }
        String a_dir="";
        if (a_x-t_x>0){
            if (a_y-t_y>0){
                a_dir = "E";
            }
            else if (a_y-t_y<0){
                a_dir = "H";
            }
            else{
                a_dir = "A";
            }
        }
        else if (a_x-t_x<0){
            if (a_y-t_y>0){
                a_dir = "F";
            }
            else if (a_y-t_y<0){
                a_dir = "G";
            }
            else{
                a_dir = "C";
            }
        }
        else{
            if (a_y-t_y>0){
                a_dir = "B";
            }
            else if (a_y-t_y<0){
                a_dir = "D";
            }
        }
        return a_y+""+a_x+a_dir;
    }
}