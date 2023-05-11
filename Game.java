import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame {
    private Board board;
    private Player playerBlack;
    private Player playerWhite;
    private boolean whiteTurn = true;

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

                    if (whiteTurn) {
                        if (board.isValidMove(row, col, playerWhite.getColor())) {
                            playerWhite.makeMove(board, row, col);
                            board.flipDisc(row, col, playerWhite.getColor());
                            whiteTurn =false;
                        }
                        repaint();
                        if(board.isGameOver(playerWhite.getColor())) {
                            JOptionPane.showMessageDialog(Game.this,
                                    "Congratulations",
                                    board.getWinner(),
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        if (board.isValidMove(row, col, playerBlack.getColor())) {
                            playerBlack.makeMove(board, row, col);
                            board.flipDisc(row, col, playerBlack.getColor());
                            whiteTurn = true;
                        }
                        repaint();
                        if(board.isGameOver(playerBlack.getColor())) {
                            JOptionPane.showMessageDialog(Game.this,
                                    "Congratulations",
                                    board.getWinner(),
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    System.out.println(whoTurn());
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            setBackground(new Color(53, 101, 77));
            paintBoard(g);
            paintDisc(g);

        }

        public String whoTurn() {
            if (whiteTurn) {
                return "White Turn";
            }
            return "Black Turn";
        }

        private void paintBoard(Graphics g) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    g.drawRect(50 * col, 50 * row, 50, 50);
                }
            }
        }

        private void paintDisc(Graphics g) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (board.getColor(row, col) == 'W') {
                        g.drawImage(imageWhite, col * 50, row * 50, 50, 50, null, null);
                    } else if (board.getColor(row, col) == 'B') {
                        g.drawImage(imageBlack, col * 50, row * 50, 50, 50, null, null);
                    }
                }
            }
        }
    }
}

