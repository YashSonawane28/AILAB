import java.util.Arrays;

public class nqueenmrv {
    public static int count =1;

    public static void solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1); // Initialize all queens to be not placed

        placeQueens(queens, n, 0);
    }

    private static void placeQueens(int[] queens, int n, int row) {
        if (row == n) {
            printSolution(queens);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isSafe(queens, row, i)) {
                queens[row] = i;
                placeQueens(queens, n, row + 1);
                queens[row] = -1; // Backtrack
            }
        }
    }

    private static boolean isSafe(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || queens[i] - i == col - row || queens[i] + i == col + row) {
                return false; // Check column and diagonals
            }
        }
        return true;
    }

    private static void printSolution(int[] queens) {
        System.out.println(count);
        count++;
        int n = queens.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(queens[i] == j ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 8; // Change n to the desired board size
        solveNQueens(n);
    }
}
