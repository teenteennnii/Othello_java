public class Board{
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

    public void startDisc() { //minus 1
        cells[3][3].makeWhite();
        cells[4][4].makeWhite();
        cells[4][3].makeBlack();
        cells[3][4].makeBlack();
    }

    public char getColor(int row, int col) {
        return cells[row][col].getDiscColor();
    }

    public boolean isValidMove(int row, int col, char player) {
        // Check if the given position is within the bounds of the game board
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        // Check if the specified position is empty
        if (cells[row][col].getDiscColor() != ' ') {
            return false;
        }
        // Check if the move is adjacent to an opponent's disc
        char opponentColor = (player == 'W') ? 'B' : 'W';

        // Check horizontally
        for (int i = col - 1; i >= 0; i--) {
            if (cells[row][i].getDiscColor() == player) {
                if (col - i > 1) {
                    return true;
                }
                break;
            } else if (cells[row][i].getDiscColor() == ' ') {
                break;
            }
        }
        for (int i = col + 1; i < size; i++) {
            if (cells[row][i].getDiscColor() == player) {
                if (i - col > 1) {
                    return true;
                }
                break;
            } else if (cells[row][i].getDiscColor() == ' ') {
                break;
            }
        }

        // Check vertically
        for (int i = row - 1; i >= 0; i--) {
            if (cells[i][col].getDiscColor() == player) {
                if (row - i > 1) {
                    return true;
                }
                break;
            } else if (cells[i][col].getDiscColor() == ' ') {
                break;
            }
        }
        for (int i = row + 1; i < size; i++) {
            if (cells[i][col].getDiscColor() == player) {
                if (i - row > 1) {
                    return true;
                }
                break;
            } else if (cells[i][col].getDiscColor() == ' ') {
                break;
            }
        }

        // Check diagonally (top-left to bottom-right)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (cells[i][j].getDiscColor() == player) {
                if (row - i > 1) {
                    return true;
                }
                break;
            } else if (cells[i][j].getDiscColor() == ' ') {
                break;
            }
        }
        for (int i = row + 1, j = col + 1; i < size && j < size; i++, j++) {
            if (cells[i][j].getDiscColor() == player) {
                if (i - row > 1) {
                    return true;
                }
                break;
            } else if (cells[i][j].getDiscColor() == ' ') {
                break;
            }
        }

        // Check diagonally (top-right to bottom-left)
        for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++) {
            if (cells[i][j].getDiscColor() == player) {
                if (row - i > 1) {
                    return true;
                }
                break;
            } else if (cells[i][j].getDiscColor() == ' ') {
                break;
            }
        }
        for (int i = row + 1, j = col - 1; i < size && j >= 0; i++, j--) {
            if (cells[i][j].getDiscColor() == player) {
                if (i - row > 1) {
                    return true;
                }
                break;
            } else if (cells[i][j].getDiscColor() == ' ') {
                break;
            }
        }

        // If none of the above conditions are met, the move is not valid
        return false;
    }

    public void flipDisc(int row, int col, char player) {
        char opponentColor = (player == 'W') ? 'B' : 'W';

        // Flip horizontally
        for (int i = col - 1; i >= 0; i--) {
            if (cells[row][i].getDiscColor() == player) {
                for (int j = i + 1; j < col; j++) {
                    cells[row][j].flipColor();
                }
                break;
            } else if (cells[row][i].getDiscColor() == ' ') {
                break;
            }
        }
        for (int i = col + 1; i < size; i++) {
            if (cells[row][i].getDiscColor() == player) {
                for (int j = i - 1; j > col; j--) {
                    cells[row][j].flipColor();
                }
                break;
            } else if (cells[row][i].getDiscColor() == ' ') {
                break;
            }
        }

        // Flip vertically
        for (int i = row - 1; i >= 0; i--) {
            if (cells[i][col].getDiscColor() == player) {
                for (int j = i + 1; j < row; j++) {
                    cells[j][col].flipColor();
                }
                break;
            } else if (cells[i][col].getDiscColor() == ' ') {
                break;
            }
        }
        for (int i = row + 1; i < size; i++) {
            if (cells[i][col].getDiscColor() == player) {
                for (int j = i - 1; j > row; j--) {
                    cells[j][col].flipColor();
                }
                break;
            } else if (cells[i][col].getDiscColor() == ' ') {
                break;
            }
        }

        // Flip diagonally (top-left to bottom-right)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (cells[i][j].getDiscColor() == player) {
                for (int k = i + 1, l = j + 1; k < row && l < col; k++, l++) {
                    cells[k][l].flipColor();
                }
                break;
            } else if (cells[i][j].getDiscColor() == ' ') {
                break;
            }
        }
        for (int i = row + 1, j = col + 1; i < size && j < size; i++, j++) {
            if (cells[i][j].getDiscColor() == player) {
                for (int k = i - 1, l = j - 1; k > row && l > col; k--, l--) {
                    cells[k][l].flipColor();
                }
                break;
            } else if (cells[i][j].getDiscColor() == ' ') {
                break;
            }
        }

        // Flip diagonally (top-right to bottom-left)
        for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++) {
            if (cells[i][j].getDiscColor() == player) {
                for (int k = i + 1, l = j - 1; k < row && l > col; k++, l--) {
                    cells[k][l].flipColor();
                }
                break;
            } else if (cells[i][j].getDiscColor() == ' ') {
                break;
            }
        }
        for (int i = row + 1, j = col - 1; i < size && j >= 0; i++, j--) {
            if (cells[i][j].getDiscColor() == player) {
                for (int k = i - 1, l = j + 1; k > row && l < col; k--, l++) {
                    cells[k][l].flipColor();
                }
                break;
            } else if (cells[i][j].getDiscColor() == ' ') {
                break;
            }
        }
    }

    public void addDisc(int row, int col, char player) {
        if (player == 'W') {
            cells[row][col].makeWhite();
        }
        if (player == 'B') {
            cells[row][col].makeBlack();
        }
    }

    public boolean isGameOver() {
        // Check if there are any valid moves for white
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (isValidMove(row, col, 'W')) {
                    return false; // White has a valid move, game is not over
                }
            }
        }

        // Check if there are any valid moves for black
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (isValidMove(row, col, 'B')) {
                    return false; // Black has a valid move, game is not over
                }
            }
        }

        // No valid moves for both white and black, game is over
        return true;
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
                if(getColor(row, col) == 'B'){
                    countBlack += 1;
                }
            }
        }
    }

    public void countDisc() {
        countWhiteDisc();
        countBlackDisc();
    }

    public String countScore() {
        countDisc();
        return "White: " + countWhite + "\t" + "Black: " + countBlack;
    }

    public int getSize() {
        return size;
    }

    public String getWinner() {
        countDisc();
        if (countBlack > countWhite) {
            return "Black Win!";
        } else if (countWhite > countBlack) {
            return "White Win!";
        }
        return "Draw!";
    }

//    public void checkMove() {
//        for(int row = 0; row < size; row++) {
//            for(int col = 0; col < size; col++) {
//                System.out.println(isValidMove(row, col, 'W'));
//            }
//        }
//    }
}

