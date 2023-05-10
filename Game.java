import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game() {
        board = new Board();
        player1 = new WhitePlayer();
        player2 = new Player('W');
        currentPlayer = player1;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!board.isGameOver()) {
//            currentPlayer.makeMove(board);

            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }

        System.out.println("Game over! The winner is " + board.getWinner());
    }

}
