import java.util.Scanner;

public class ConnectFour {
    static final int ROWS = 6;
    static final int COLS = 7;
    static char[][] board = new char[ROWS][COLS];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char playAgain;

        do {
            initializeBoard();
            char currentPlayer = 'X';
            boolean gameOver = false;

            while (!gameOver) {
                displayBoard();

                System.out.print("Player " + currentPlayer + ", enter column (0-6): ");
                int col = sc.nextInt();

                if (col < 0 || col >= COLS) {
                    System.out.println("Invalid column! Try again.");
                    continue;
                }

                int row = dropPiece(col, currentPlayer);

                if (row == -1) {
                    System.out.println("Column is full! Choose another.");
                    continue;
                }

                if (checkWin(row, col, currentPlayer)) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " Wins!");
                    gameOver = true;
                } else if (isBoardFull()) {
                    displayBoard();
                    System.out.println("Game Draw!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("Play Again? (Y/N): ");
            playAgain = sc.next().charAt(0);

        } while (playAgain == 'Y' || playAgain == 'y');

        sc.close();
    }

    static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = '.';
            }
        }
    }

    static void displayBoard() {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }

    static int dropPiece(int col, char player) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == '.') {
                board[i][col] = player;
                return i;
            }
        }
        return -1;
    }

    static boolean isBoardFull() {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] == '.')
                return false;
        }
        return true;
    }

    static boolean checkWin(int row, int col, char player) {

        // Horizontal
        int count = 0;
        for (int j = 0; j < COLS; j++) {
            if (board[row][j] == player)
                count++;
            else
                count = 0;
            if (count == 4)
                return true;
        }

        // Vertical
        count = 0;
        for (int i = 0; i < ROWS; i++) {
            if (board[i][col] == player)
                count++;
            else
                count = 0;
            if (count == 4)
                return true;
        }

        // Diagonal (\)
        for (int i = row, j = col; i > 0 && j > 0; i--, j--) {
        }
        int r = row, c = col;
        while (r > 0 && c > 0) {
            r--;
            c--;
        }

        count = 0;
        while (r < ROWS && c < COLS) {
            if (board[r][c] == player)
                count++;
            else
                count = 0;
            if (count == 4)
                return true;
            r++;
            c++;
        }

        // Diagonal (/)
        r = row;
        c = col;
        while (r < ROWS - 1 && c > 0) {
            r++;
            c--;
        }

        count = 0;
        while (r >= 0 && c < COLS) {
            if (board[r][c] == player)
                count++;
            else
                count = 0;
            if (count == 4)
                return true;
            r--;
            c++;
        }

        return false;
    }
}