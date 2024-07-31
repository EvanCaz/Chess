import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import board.Board;
import board.pieces.Piece;

public class Chess {
    private static JPanel firstPanel = null;
    private static JPanel secondPanel = null;
    private static Board board = new Board();
    private static JPanel[][] panelTracker = new JPanel[8][8];
    private static int turn = 1; // 1 for white, 0 for black
    private static JLabel turnLabel = new JLabel("White's Turn", JLabel.CENTER); // Label to display the turn

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

            panelTracker[row][col] = panelSquare; // Updates panelTracker with new panel

            // Add mouse event listeners for highlighting and clicking
            panelSquare.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (panelSquare != firstPanel) {
                        panelSquare.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (panelSquare != firstPanel) {
                        panelSquare.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    handleClick(panelSquare);
                }
            });

            panelSquare.add(square);
            boardPanel.add(panelSquare);
        }
    }

    // Set the font for the turn label
    turnLabel.setFont(new Font("Serif", Font.BOLD, 24));
    frame.add(boardPanel, BorderLayout.CENTER);
    frame.add(turnLabel, BorderLayout.SOUTH); // Add the turn label at the bottom
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    updateBoard();
}

/**
 * Method to get the icon associated with a piece at a board position.
 * 
 * @param row
 * @param col
 * @return a unicode piece of type String
 */
private static String getPieceUnicode(int row, int col) {
    Piece piece = board.getPieceAt(col, row);
    if (piece != null) {
        return piece.getIcon();
    }
    return "";
}

private static void handleClick(JPanel panelSquare) {
    if (firstPanel == null) { // Selecting the panel
        firstPanel = panelSquare;
        firstPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
    } else if (firstPanel == panelSquare) { // Deselecting a clicked piece
        firstPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        firstPanel = null;
    } else {
        secondPanel = panelSquare;
        movePiece(firstPanel, secondPanel);
        firstPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        secondPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        firstPanel = null;
        secondPanel = null;
    }
}

/**
 * Moves the pieces on the board and handle turns, calls the Board classes movePiece method to handle validation.
 * 
 * @param first
 * @param second
 */
private static void movePiece(JPanel first, JPanel second) {
    Point fromPoint = getPanelIndicies(first);
    Point toPoint = getPanelIndicies(second);

    int[] indicies = {fromPoint.x, fromPoint.y, toPoint.x, toPoint.y};
    boolean moveSuccessful = false;

    try {
        Piece piece = board.getPieceAt(indicies[0], indicies[1]);
        if ((turn == 1 && piece.getColor().equals("white")) ||
            (turn == 0 && piece.getColor().equals("black"))) {
            moveSuccessful = board.movePiece(indicies);
        }
    } catch (NullPointerException e) {
        System.out.println("Null space was clicked or an invalid color was clicked");
    }

    if (moveSuccessful) { 
        turn = 1 - turn; // Switch turn: 1 to 0 or 0 to 1
        updateTurnLabel(); // Update the turn label
        updateBoard();
    }
}

/**
 * update the turn label.
 */
private static void updateTurnLabel() {
    if (turn == 1) {
        if(board.isInCheck("white")){
            turnLabel.setText("<html>White's Turn<br>You are in check!</html>");
        } else {
        turnLabel.setText("White's Turn"); 
        }
    } else if (turn == 0) {
        if(board.isInCheck("black")){
            turnLabel.setText("<html>Black's Turn<br>You are in check!</html>");
        } else {
        turnLabel.setText("Black's Turn"); 
        }
    }
}


/**
 * Helper Method to find the index of a panel, to pass to other methods interacting with the 
 * Board or Piece classes.
 * 
 * @param panel
 * @return a Point object with indicies of a panel if it can be found, returns null otherwise.
 */
private static Point getPanelIndicies(JPanel panel) {
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            if (panelTracker[row][col] == panel) {
                return new Point(col, row); // (col, row) to satisfy board's movePiece method()
            }
        }
    }
    return null;
}

/**
 * Method to update the GUI representation of the board.
 */
private static void updateBoard() {
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            JLabel label = (JLabel) panelTracker[row][col].getComponent(0);
            label.setText(getPieceUnicode(row, col));
        }
    }
}

/**
 * Method to update the turn label.
 */

}