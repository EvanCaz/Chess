package board.pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;

/**
 * Queen subclass for the Piece class. Handles Queen specific methods for the Piece class.
 */
public class Queen extends Piece {
   public Queen(String pieceColor, int row, int column) {
      super(pieceColor, row, column);
   }

   @Override
   public String getIcon() {
      return pieceColor.equals("white") ? "\u2655" : "\u265B";
   }

   @Override
   public List<int[]> possibleMoves(Piece[][] boardPosition) {
      List<int[]> moves = new ArrayList<>();
      int[][] directions = {
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

      for (int[] direction : directions) {
         int moveRow = row + direction[0];
         int moveColumn = column + direction[1];
         while (Board.isInBounds(moveRow, moveColumn)) {
            if (boardPosition[moveRow][moveColumn] == null) {
                moves.add(new int[]{moveRow, moveColumn});
            } else if (!boardPosition[moveRow][moveColumn].getColor().equals(pieceColor)) {
                moves.add(new int[]{moveRow, moveColumn});
                break;
            } else {
                break;
            }
            moveRow += direction[0];
            moveColumn += direction[1];
         }
      }

    return moves;
   }
}
