import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingBoard {
    private static final String[] UNICODE_PIECES = {
        "\u2654", "\u2655", "\u2656", "\u2657", "\u2658", "\u2659",
        "\u265A", "\u265B", "\u265C", "\u265D", "\u265E", "\u265F"
    };

    public static void main(String[] args) {
        // Create the main frame for the chess board
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
        boardPanel.setPreferredSize(new Dimension(400, 400));

        Color lightColor = new Color(230, 225, 211);
        Color darkColor = new Color(181, 136, 66);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JLabel square = new JLabel(getPieceUnicode(row, col));
                square.setFont(new Font("Serif", Font.BOLD, 32));
                square.setHorizontalAlignment(JLabel.CENTER);
                square.setVerticalAlignment(JLabel.CENTER);

                JPanel panelSquare = new JPanel(new BorderLayout());
                panelSquare.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                if ((row + col) % 2 == 0) {
                    panelSquare.setBackground(lightColor);
                } else {
                    panelSquare.setBackground(darkColor);
                }

                // Add mouse event listeners for highlighting
                panelSquare.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        panelSquare.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        panelSquare.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    }
                });

                panelSquare.add(square);
                boardPanel.add(panelSquare);
            }
        }

        frame.add(boardPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String getPieceUnicode(int row, int col) {
        if (row == 0 || row == 7) {
            int offset = (row == 0) ? 0 : 6;
            switch (col) {
                case 0:
                case 7:
                    return UNICODE_PIECES[2 + offset];
                case 1:
                case 6:
                    return UNICODE_PIECES[4 + offset];
                case 2:
                case 5:
                    return UNICODE_PIECES[3 + offset];
                case 3:
                    return UNICODE_PIECES[1 + offset];
                case 4:
                    return UNICODE_PIECES[0 + offset];
            }
        } else if (row == 1 || row == 6) {
            return UNICODE_PIECES[5 + ((row == 1) ? 0 : 6)];
        }
        return "";
    }
}
