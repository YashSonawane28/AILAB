import java.util.Scanner;

public class aitictactoe {
    static char board[] = new char[10];
    static final char PLAYER = 'O';
    static final char BOT = 'X';

    public static void printBoard() {
        for (int i = 1; i <= 9; i++) {
            System.out.print(board[i] + "|");
            if (i % 3 == 0) {
                System.out.println();
                if (i < 9) {
                    System.out.println("-+-+-");
                }
            }
        }
        System.out.println();
    }


    public static void insertLetter(char letter, int position) {
        if (board[position] == ' ') {
            board[position] = letter;
            printBoard();
            if (checkDraw()) {
                System.out.println("Draw!");
                System.exit(0);
            }
            if (checkForWin()) {
                if (letter == 'X') {
                    System.out.println("Bot wins!");
                } else {
                    System.out.println("Player wins!");
                }
                System.exit(0);
            }
        } else {
            System.out.println("Can't insert there!");
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter a new position:  ");
            position = sc.nextInt();
            insertLetter(letter, position);

        }
    }

    public static boolean checkForWin() {
        return (board[1] == board[2] && board[1] == board[3] && board[1] != ' ')
                || (board[4] == board[5] && board[4] == board[6] && board[4] != ' ')
                || (board[7] == board[8] && board[7] == board[9] && board[7] != ' ')
                || (board[1] == board[4] && board[1] == board[7] && board[1] != ' ')
                || (board[2] == board[5] && board[2] == board[8] && board[2] != ' ')
                || (board[3] == board[6] && board[3] == board[9] && board[3] != ' ')
                || (board[1] == board[5] && board[1] == board[9] && board[1] != ' ')
                || (board[7] == board[5] && board[7] == board[3] && board[7] != ' ');
    }

    public static boolean checkWhichMarkWon(char mark) {
        return (board[1] == board[2] && board[1] == board[3] && board[1] == mark)
                || (board[4] == board[5] && board[4] == board[6] && board[4] == mark)
                || (board[7] == board[8] && board[7] == board[9] && board[7] == mark)
                || (board[1] == board[4] && board[1] == board[7] && board[1] == mark)
                || (board[2] == board[5] && board[2] == board[8] && board[2] == mark)
                || (board[3] == board[6] && board[3] == board[9] && board[3] == mark)
                || (board[1] == board[5] && board[1] == board[9] && board[1] == mark)
                || (board[7] == board[5] && board[7] == board[3] && board[7] == mark);
    }

    public static boolean checkDraw() {
        for (int i = 1; i <= 9; i++) {
            if (board[i] == ' ') {
                return false;
            }
        }
        return true;
    }


    public static void playerMove() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the position for 'O': ");
        int position = sc.nextInt();
        insertLetter(PLAYER, position);
    }

    public static void compMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = 0;
        for (int key = 1; key <= 9; key++) {
            if (board[key] == ' ') {
                board[key] = BOT;
                int score = minimax(board, false);
                board[key] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = key;
                }
            }
        }
        insertLetter(BOT, bestMove);
    }

    public static int minimax(char board[], boolean isMaximizing) {
        if (checkWhichMarkWon(BOT)) {
            return 1000;
        } else if (checkWhichMarkWon(PLAYER)) {
            return -1000;
        } else if (checkDraw()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int key = 1; key <= 9; key++) {
                if (board[key] == ' ') {
                    board[key] = BOT;
                    int score = minimax(board, false);
                    board[key] = ' ';
                    bestScore = Math.max(bestScore, score);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int key = 1; key <= 9; key++) {
                if (board[key] == ' ') {
                    board[key] = PLAYER;
                    int score = minimax(board, true);
                    board[key] = ' ';
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            board[i] = ' ';
        }
        printBoard();

        System.out.println("Positions are as follows:");
        System.out.println("1, 2, 3");
        System.out.println("4, 5, 6");
        System.out.println("7, 8, 9");
        System.out.println();

        while (!checkForWin()) {
            playerMove();
            if (checkForWin()) {
                break; // Check if player wins, no need for bot's move
            }
            compMove();
        }
    }
}