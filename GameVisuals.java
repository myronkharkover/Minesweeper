import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameVisuals extends JPanel {

    private GameMechanics m;
    private JLabel state;
    private int moveCount;

    public GameVisuals(JLabel status) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setFocusable(true);

        m = new GameMechanics();
        state = status;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                Point pt = me.getPoint();
                m.flip(pt.y / 50, pt.x / 50);
                if (!(m.gameResult() == 1 || m.gameResult() == -1)) {
                    moveCount++;
                }
                updateStatus();
                repaint();
            }
        });
        moveCount = 0;
    }

    public void reset() {
        m.reset();
        moveCount = 0;
        repaint();
        requestFocusInWindow();
    }

    private void updateStatus() {
        int gameStatus = m.gameResult();
        if (gameStatus == 1) {
            state.setText("You won!!!  Number of Moves: " + (moveCount + 1));
        } else if (gameStatus == -1) {
            state.setText("You Lost! Number of Moves: " + (moveCount + 1));
        } else {
            state.setText("Number of Moves: " + moveCount);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawLine(50, 0, 50, 400);
        g.drawLine(100, 0, 100, 400);
        g.drawLine(150, 0, 150, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(250, 0, 250, 400);
        g.drawLine(300, 0, 300, 400);
        g.drawLine(350, 0, 350, 400);
        g.drawLine(0, 50, 400, 50);
        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 150, 400, 150);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 250, 400, 250);
        g.drawLine(0, 300, 400, 300);
        g.drawLine(0, 350, 400, 350);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Spaces s = m.getSpaces(row, col);
                if (s.isPressed() && !s.isMine()) {
                    String str = String.valueOf(s.getMineCount());
                    if (s.getMineCount() >= 4) {
                        g.setColor(Color.ORANGE);
                    } else if (s.getMineCount() == 3) {
                        g.setColor(Color.RED);
                    } else if (s.getMineCount() == 2) {
                        g.setColor(Color.GREEN);
                    } else if (s.getMineCount() == 1) {
                        g.setColor(Color.BLUE);
                    } else if (s.getMineCount() == 0) {
                        g.setColor(Color.BLACK);
                    }
                    g.drawString(str, 50 * col + 25, 50 * row + 25);
                }
                if (s.isPressed() && s.isMine()) {
                    g.setColor(Color.RED);
                    g.drawLine(50 * col, 50 * row,
                            50 * col + 50, 50 * row + 50);
                    g.drawLine(50 * col, 50 * row + 50,
                            50 * col + 50, 50 * row);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}