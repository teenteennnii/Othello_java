public class Board {
    private char[][] board = new char[8][8];

    public Board() {
        // Initialize the board to its starting position
        board[3][3] = 'W';
        board[4][4] = 'W';
        board[3][4] = 'B';
        board[4][3] = 'B';
    }

    public void printBoard() {
        // Print out the current state of the board
    }

    public boolean isValidMove(int row, int col, char player) {
        // Check if the given move is valid for the given player
        return false;
    }

    public void makeMove(int row, int col, char player) {
        // Make the given move for the given player
    }

    public boolean isGameOver() {
        // Check if the game is over
        return false;
    }

    public char getWinner() {
        // Determine the winner of the game
        return 'W';
    }
}
