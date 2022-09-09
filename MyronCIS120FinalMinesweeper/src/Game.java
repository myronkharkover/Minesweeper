import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {
    public void run() {

        final JFrame frame = new JFrame("Minesweeper");
        frame.setLocation(400, 400);

        final JPanel state_panel = new JPanel();
        frame.add(state_panel, BorderLayout.SOUTH);
        final JLabel state = new JLabel("Setting up...");
        state_panel.add(state);

        final JPanel description_panel = new JPanel();
        frame.add(description_panel, BorderLayout.EAST);
        final JLabel description_label = new JLabel("<html>Instructions:<br/>This is Minesweeper."
                + "<br/> There are 9 mines on the board.<br/>"
                + " Press on all of the spaces that aren't mines to win! <br/>"
                + "The reset button will reset the game. <br/> The Number of Moves "
                + "tells "
                + "you how many moves you have used <html>");
        description_panel.add(description_label);

        final GameVisuals visuals = new GameVisuals(state);
        frame.add(visuals, BorderLayout.CENTER);

        final JPanel crtl_panel = new JPanel();
        frame.add(crtl_panel, BorderLayout.WEST);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                visuals.reset();
            }
        });
        crtl_panel.add(reset);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        visuals.reset();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}