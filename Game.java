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



//    public void play() {
//        Scanner scanner = new Scanner(System.in);
//
//        while (!board.isGameOver()) {
//            board.printBoard();
//            currentPlayer.makeMove(board);
//
//            if (currentPlayer == player1) {
//                currentPlayer = player2;
//            } else {
//                currentPlayer = player1;
//            }
//        }
//
//        System.out.println("Game over! The winner is " + board.getWinner());
//    }


    class GridUI extends JPanel {
        private int size = 8;
        private Image imageBlack;
        private Image imageWhite;

        public GridUI() {
            imageBlack=new ImageIcon("imgs/othelloblack2.png").getImage();
            imageWhite=new ImageIcon("imgs/othellowhite2.png").getImage();

            setPreferredSize(new Dimension(600, 600));
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            setBackground(new Color(53, 101, 77));
            paintboard(g);
            paintdisc(g);

        }

        private void paintboard(Graphics g) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    g.drawRect(50 * col, 50 * row, 50, 50);

                }
            }
        }

        private void paintdisc(Graphics g) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (board.getColor(row,col)=='W'){
                        g.drawImage(imageWhite, col*50, row*50, 50,50, null, null);
                    } else if (board.getColor(row,col)=='B') {
                        g.drawImage(imageBlack, col* 50, row * 50, 50, 50, null, null);
                    }
                    else {
                        System.out.println("getimage="+board.getColor(row,col));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
