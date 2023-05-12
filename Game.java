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
        countScoreLabel = new JLabel("", JLabel.CENTER);
        countScoreLabel.setPreferredSize(new Dimension(100, CELL_SIZE));
        MenuUI menuUI = new MenuUI();
        add(menuUI);
        add(countScoreLabel, BorderLayout.NORTH);
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
            setResizable(false);
            setPreferredSize(new Dimension(400, 400));
            countScoreLabel.setText(board.countScore() + "                 " + whoTurn());
            repaint();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    int row = e.getY() / CELL_SIZE;
                    int col = e.getX() / CELL_SIZE;

                    if (mode.equals("Player")) {
                        playWithPlayer(row, col);
                    } else if (mode.equals("EASY")) {
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
                    whiteTurn = false;
                }
                countScoreLabel.setText(board.countScore() + "                 " + whoTurn());
                repaint();
                if (board.isGameOver(playerWhite.getColor())) {
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
                if (board.isGameOver(playerBlack.getColor())) {
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

    class MenuUI extends JPanel {
        private int width = 200;
        private int height = 100;

        public MenuUI() {
            setPreferredSize(new Dimension(400, 400));
            setResizable(false);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    int ycur = e.getY();
                    int xcur = e.getX();


                    if (xcur > 100 && xcur < 300 && ycur > 50 && ycur < 150) {
                        MenuUI2 uinew = new MenuUI2();
                        add(uinew);
                        pack();
                    } else if (xcur > 100 && xcur < 300 && ycur > 250 && ycur < 350) {
                        GridUI uinew = new GridUI();
                        add(uinew);
                        pack();
                    }
                    repaint();
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            setBackground(new Color(53, 101, 77));
            paintselectplayer(g);
        }

        public void paintselectplayer(Graphics g) {
            g.drawRect(100, 50, width, height);
            g.drawRect(100, 250, width, height);
            g.drawString("Vs Bot", 180, 100);
            g.drawString("Vs Player", 180, 300);
        }
    }

    class MenuUI2 extends JPanel {
        private int width = 200;
        private int height = 50;

        public MenuUI2() {
            setPreferredSize(new Dimension(400, 400));
            setResizable(false);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    int ycur = e.getY();
                    int xcur = e.getX();
                    if (xcur > 100 && xcur < 300 && ycur > 50 && ycur < 100) {
                        System.out.println("select easy bot");
                    } else if (xcur > 100 && xcur < 300 && ycur > 150 && ycur  <200) {
                        System.out.println("select medium bot");

                    } else if (xcur > 100 && xcur < 300 && ycur > 250 && ycur < 300) {
                        System.out.println("select hard bot");

                    }
                    repaint();
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            setBackground(new Color(53, 101, 77));
            paintSelectDiff(g);
        }

        public void paintSelectDiff(Graphics g) {
            g.drawRect(100, 50, width, height);
            g.drawRect(100, 150, width, height);
            g.drawRect(100, 250, width, height);

            g.drawString("Easy", 180, 75);
            g.drawString("Medium", 180, 180);
            g.drawString("Hard", 180, 280);
        }
    }
}
