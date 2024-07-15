package board.pieces;

import java.util.ArrayList;
import java.util.List;

/**
 * Queen subclass for the Piece class. Handles Queen specific methods for the Piece class.
 */
public class Queen extends Piece {
   public Queen(String pieceColor, int row, int column) {
      super(pieceColor, row, column);
   }

   @Override
   public String getIcon() {
    return pieceColor.charAt(0) + "Q";
   }

   @Override
   public List<int[]> possibleMoves(Piece[][] boardPosition) {
      List<int[]> moves = new ArrayList<>();
      int[][] directions = {
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

      for (int[] direction : directions) {
         int moveRow = row;
         int moveColumn = column;
         while (true) {
            moveRow =+ direction[0];
            moveColumn =+ direction[1];
            if (isInBounds(moveRow, moveColumn)) {
               if (boardPosition[moveRow][moveColumn] == null) {
                  moves.add(new int[]{moveRow, moveColumn});
               } else if (!boardPosition[moveRow][moveColumn].getColor().equals(pieceColor)) {
                  moves.add(new int[]{moveRow, moveColumn});
                  break;
               } else {
                  break;
               }
            }
         }
      }
      return moves;
   }

   private boolean isInBounds(int row, int column) {
      if (row >= 0 && row < 8 && column >= 0 && column < 8) {
        return true;
      } else {
        return false;
      }
   }

}
