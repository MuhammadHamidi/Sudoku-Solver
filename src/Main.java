import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void solve(int[][] table , int empty){
        if(empty == 0)
            printAnswer(table);
        else {
            int i = -1;
            int j = -1;
            for (int k = 0; k < 9 && (i == -1); k++) {
                for (int l = 0; l < 9; l++) {
                    if (table[k][l] == 0) {
                        i = k;
                        j = l;
                        break;
                    }
                }
            }

            ArrayList<Integer> valid = getValidNums(table , i , j);
            if (valid.size() != 0) {
                for (int k=0 ; k<valid.size() ; k++) {
                    table[i][j] = valid.get(k);
                    solve(table , empty-1);
                    table[i][j] = 0;
                }
            }
        }
    }

    public static ArrayList<Integer> getValidNums(int[][] table , int i , int j){
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int k = 0; k < 9; k++) {
            if (table[i][k] != 0)
                nums[table[i][k] - 1] = 0;
            if (table[k][j] != 0)
                nums[table[k][j] - 1] = 0;
        }
        for (int k=(i/3)*3 ; k<((i/3)+1)*3 ; k++) {
            for (int l=(j/3)*3 ; l<((j/3)+1)*3 ; l++) {
                if(table[k][l] != 0)
                    nums[table[k][l] - 1] = 0;
            }
        }

        ArrayList<Integer> valid = new ArrayList<>();
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] != 0)
                valid.add(nums[k]);
        }
        return valid;
    }

    public static void printAnswer(int[][] table){
        for (int i=0 ; i<9 ; i++) {
            for (int j=0 ; j<9 ; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int empty = 0;
        int[][] table = new int[9][9];

        for (int i=0 ; i<9 ; i++) {
            for (int j=0 ; j<9 ; j++) {
                table[i][j] = input.nextInt();
                if(table[i][j] == 0)
                    empty++;
            }
        }
        solve(table , empty);
    }
}