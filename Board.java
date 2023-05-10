public class Board {
    private char[][] board = new char[8][8];
    private Cell [][] cells;
    private int size = 8;

    public Board() {
        initCells();
        startDisc();
    }

    private void initCells() {
        cells = new Cell[size][size];
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                cells[row][col] = new Cell();
            }
        }
    }

    public void startDisc() {
        cells[3][3].makeWhite();
        cells[4][4].makeWhite();
        cells[3][4].makeBlack();
        cells[4][3].makeBlack();
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
