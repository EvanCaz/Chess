package board.pieces;

import java.util.ArrayList;
import java.util.List;

/**
 * Pawn subclass for the Piece class. Handles Pawn specific methods for the Piece class.
 */
public class Pawn extends Piece {
   private boolean firstMove;

   public Pawn(String pieceColor, int row, int column) {
      super(pieceColor, row, column);
      firstMove = true;
   }

   @Override
   public String getIcon() {
      return pieceColor.charAt(0) + "N";
   }

   @Override
   public List<int[]> possibleMoves(Piece[][] boardPosition) {
      List<int[]> moves = new ArrayList<>();
      int directions = 0;
      if (pieceColor.equals("white")) {
         directions = 1;
      } else {
         directions = -1;
      }

      int moveRow = row + directions;
      int moveColumn = column;
      if (isInBounds(moveRow, moveColumn) && boardPosition[moveRow][moveColumn] == null) {
         moves.add(new int[]{moveRow, moveColumn});
         if (firstMove) {
            moveRow += directions;
            if (isInBounds(moveRow, moveColumn) && boardPosition[moveRow][moveColumn] == null) {
               moves.add(new int[]{moveRow, moveColumn});
            }
         }
      }

      int[][] captureMoves = { {directions, 1}, {directions, -1} };
      for (int[] direction : captureMoves) {
         moveRow = row + direction[0];
         moveColumn = column + direction[1];
         if (isInBounds(moveRow, moveColumn) && boardPosition[moveRow][moveColumn] != null && !boardPosition[moveRow][moveColumn].getColor().equals(pieceColor)) {
            moves.add(new int[]{moveRow, moveColumn});
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
