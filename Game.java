import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame {
    private Board board;
    private Player playerBlack;
    private Player playerWhite;
    private JLabel countScoreLabel;
    private String mode = "Player";
    public static final int CELL_SIZE = 50;
    private boolean whiteTurn = true;

    public Game() {
        board = new Board();
        playerBlack = new BlackPlayer();
        playerWhite = new WhitePlayer();
        GridUI gridUI = new GridUI();
        countScoreLabel = new JLabel(board.countScore() + "                 " +
                "Turn : White", JLabel.CENTER);
        countScoreLabel.setPreferredSize(new Dimension(100, CELL_SIZE));
        add(countScoreLabel, BorderLayout.NORTH);
        add(gridUI);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

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
                    int row = e.getY() / CELL_SIZE;
                    int col = e.getX() / CELL_SIZE;

                    if (mode.equals("Player")){
                        playWithPlayer(row, col);
                    } else if (mode.equals("EASY")){
                        playWithBotEASY(row, col);
                    }

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

        public void playWithPlayer(int row, int col) {
            if (whiteTurn) {
                if (board.isValidMove(row, col, playerWhite.getColor())) {
                    playerWhite.makeMove(board, row, col);
                    board.flipDisc(row, col, playerWhite.getColor());
                    whiteTurn =false;
                }
                countScoreLabel.setText(board.countScore() + "                 " + whoTurn());
                repaint();
                if(board.isGameOver(playerWhite.getColor())) {
                    JOptionPane.showMessageDialog(Game.this,
                            board.getWinner(),
                            "Congratulations",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                if (board.isValidMove(row, col, playerBlack.getColor())) {
                    playerBlack.makeMove(board, row, col);
                    board.flipDisc(row, col, playerBlack.getColor());
                    whiteTurn = true;
                }
                countScoreLabel.setText(board.countScore() + "                 " + whoTurn());
                repaint();
                if(board.isGameOver(playerBlack.getColor())) {
                    JOptionPane.showMessageDialog(Game.this,
                            board.getWinner(),
                            "Congratulations",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        public void playWithBotEASY(int row, int col) {

        }

        public String whoTurn() {
            if (whiteTurn) {
                return "Turn : White";
            }
            return "Turn : Black";
        }

        private void paintBoard(Graphics g) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    g.drawRect(CELL_SIZE * col, CELL_SIZE * row, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        private void paintDisc(Graphics g) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (board.getColor(row, col) == 'W') {
                        g.drawImage(imageWhite, col * CELL_SIZE, row * CELL_SIZE,
                                CELL_SIZE, CELL_SIZE, null, null);
                    } else if (board.getColor(row, col) == 'B') {
                        g.drawImage(imageBlack, col * CELL_SIZE, row * CELL_SIZE,
                                CELL_SIZE, CELL_SIZE, null, null);
                    }
                }
            }
        }
    }
}

