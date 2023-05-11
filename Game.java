import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame {
    private Board board;
    private Player playerBlack;
    private Player playerWhite;
    private Player currentPlayer;
    private int counter = 0;
    private boolean white = true;

    public Game() {
        board = new Board();
        playerBlack = new BlackPlayer();
        playerWhite = new WhitePlayer();
        GridUI gridUI = new GridUI();
        add(gridUI);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Game();
    }


    class GridUI extends JPanel {
        private int size = 8;
        private Image imageBlack;
        private Image imageWhite;

        public GridUI() {
            imageBlack = new ImageIcon("imgs/othelloblack2.png").getImage();
            imageWhite = new ImageIcon("imgs/othellowhite2.png").getImage();
            setPreferredSize(new Dimension(400, 400));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    int row = e.getY() / 50;
                    int col = e.getX() / 50;
                    System.out.println("Clicked on row: " + row + ", col: " + col);
                    System.out.println("white" + white);

                    if (white) {
                        if (board.isValidMove(row, col, playerWhite.getColor())) {
                            playerWhite.makeMove(board, row, col);
                            board.flipDisc(row, col, playerWhite.getColor());
                            counter++;
                            white =false;
                        }
                    } else {
                        if (board.isValidMove(row, col, playerBlack.getColor())) {
                            playerBlack.makeMove(board, row, col);
                            board.flipDisc(row, col, playerBlack.getOpponentColor());
                            counter++;
                            white = true;

                        }
                    }
                    repaint();

                }


            });
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
                    if (board.getColor(row, col) == 'W') {
                        g.drawImage(imageWhite, col * 50, row * 50, 50, 50, null, null);
                    } else if (board.getColor(row, col) == 'B') {
                        g.drawImage(imageBlack, col * 50, row * 50, 50, 50, null, null);
                    } else {
//                        System.out.println("getimage=" + board.getColor(row, col));
                    }
                }
            }
        }
    }
}

