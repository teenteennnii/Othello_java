import java.util.Random;

public class BotPlayer extends Player {
    private String difficulty;

    public BotPlayer(char color, String difficulty) {
        super(color);
        this.difficulty = difficulty;
    }

    public void makeMove(Board board) {
        switch (difficulty) {
            case "EASY":
                makeRandomMove(board);
                break;
            case "MEDIUM":
                makeBestMove(board);
                break;
            // Add more cases for other difficulty levels if needed

        }
    }

    private void makeRandomMove(Board board) {
        Random random = new Random();
        int size = board.getSize();

        while (true) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            if (board.isValidMove(row, col, getColor())) {
                board.flipDisc(row, col, getColor());
                break;
            }
        }
    }

    private void makeBestMove(Board board) {
        int maxCaptured = -1;
        int bestRow = -1;
        int bestCol = -1;
        int size = board.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.isValidMove(row, col, getColor())) {
                    int captured = countCapturedDiscs(board, row, col);
                    if (captured > maxCaptured) {
                        maxCaptured = captured;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        if (bestRow != -1 && bestCol != -1) {
            board.flipDisc(bestRow, bestCol, getColor());
        }
    }

    private int countCapturedDiscs(Board board, int row, int col) {
        int captured = 0;
        char opponentColor = getOpponentColor();

        // Directions: up, down, left, right, diagonal-up-left, diagonal-up-right,
        // diagonal-down-left, diagonal-down-right
        int[][] directions = {
                {-1, 0},  // Up
                {1, 0},   // Down
                {0, -1},  // Left
                {0, 1},   // Right
                {-1, -1}, // Diagonal Up Left
                {-1, 1},  // Diagonal Up Right
                {1, -1},  // Diagonal Down Left
                {1, 1}    // Diagonal Down Right
        };

        for (int[] direction : directions) {
            int dRow = direction[0];
            int dCol = direction[1];

            captured += countCapturedInDirection(board, row, col, dRow, dCol);
        }

        return captured;
    }

    private int countCapturedInDirection(Board board, int row, int col, int dRow, int dCol) {
        int captured = 0;
        char opponentColor = getOpponentColor();

        row += dRow;
        col += dCol;

        while (row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize()) {
            if (board.getColor(row, col) == opponentColor) {
                captured++;
                row += dRow;
                col += dCol;
            } else if (board.getColor(row, col) == getColor()) {
                return captured;
            } else {
                break;
            }
        }
        return 0;
    }
}