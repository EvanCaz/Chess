package board.pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;

/**
 * Rook subclass for the Piece class. Handles Rook specific methods for the Piece class.
 */
public class Rook extends Piece {
   public Rook(String pieceColor, int row, int column) {
      super(pieceColor, row, column);
   }

   @Override
   public String getIcon() {
      return pieceColor.charAt(0) + "R";
   }

   @Override
   public List<int[]> possibleMoves(Piece[][] boardPosition) {
      List<int[]> moves = new ArrayList<>();
      int[][] directions = { {1, 0}, {-1, 0}, {0,1}, {0, -1} };

      for (int[] direction : directions) {
         int moveRow = row;
         int moveColumn = column;
         while (true) {
            moveRow += direction[0];
            moveColumn += direction[1];
            if (Board.isInBounds(moveRow, moveColumn)) {
               if (boardPosition[moveRow][moveColumn] == null) {
                  moves.add(new int[]{moveRow, moveColumn});
               } else {
                  if (!boardPosition[moveRow][moveColumn].getColor().equals(pieceColor)) {
                    moves.add(new int[]{moveRow, moveColumn});
                  }
                  break;
               }
            } else {
               break;
            }
         }

      }
      return moves;
   }
}
