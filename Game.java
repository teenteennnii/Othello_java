import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Game extends JFrame {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game() {
        board = new Board();
        player1 = new Player('B');
        player2 = new Player('W');
        currentPlayer = player1;
        GridUI gridUI = new GridUI();
        add(gridUI);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        Game game = new Game();
//        game.play();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!board.isGameOver()) {
            board.printBoard();
            currentPlayer.makeMove(board);

            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }

        System.out.println("Game over! The winner is " + board.getWinner());
    }

}

    class GridUI extends JPanel {
        public GridUI() {
            setPreferredSize(new Dimension(600, 600));

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            setBackground(new Color(53, 101, 77));
            g.setColor(Color.black);
            g.drawRect(50, 50, 50, 50);
            g.drawRect(100, 50, 50, 50);
            g.fillOval(50, 50, 50, 50);
            g.setColor(Color.white);

            g.fillOval(100, 50, 50, 50);


        }
    }


