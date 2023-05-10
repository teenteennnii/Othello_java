public class Player {
    private char color;

    public Player(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public char getOpponentColor() {
        if (this.getColor() == 'W') {
            return 'B';
        }
        if (this.getColor() == 'B') {
            return 'W';
        }
        return ' ';
    }

    public boolean makeMove(Board board, int row, int col) {
        if (board.isValidMove(row, col, color)) {
            board.flipDisc(row, col, color);
            return true;
        }
        return false;
    }

    public class WhitePlayer extends Player {
        public WhitePlayer() {
            super('W');
        }
    }

    public class BlackPlayer extends Player {
        public BlackPlayer() {
            super('B');
        }
    }
}
