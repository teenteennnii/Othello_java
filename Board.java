public class Board {
    private Cell [][] cells;
    private int size = 8;
    private int countWhite = 0;
    private int countBlack = 0;

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

    public char getColor(int row, int col) {
        return cells[row][col].getDiscColor();
    }

    public boolean isValidMove(int row, int col, char player) {
        // Check if the given move is valid for the given player
        return false;
    }

    public void makeMove(int row, int col, char player) {
        // Make the given move for the given player
    }

    public void countWhiteDisc() {
        countWhite = 0;
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(getColor(row, col) == 'W'){
                    countWhite += 1;
                }
            }
        }
    }

    public void countBlackDisc() {
        countBlack = 0;
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(getColor(row, col) == 'W'){
                    countBlack += 1;
                }
            }
        }
    }

    public char getWinner() {
        if (countBlack > countWhite) {
            return 'B';
        } else if (countWhite > countBlack) {
            return 'W';
        }
        return 'D';
    }
}
