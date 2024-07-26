package board.pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;

/**
 * Knight subclass for the Piece class. Handles Knight specific methods for the Piece class.
 */
public class Knight extends Piece {
   public Knight(String pieceColor, int row, int column) {
      super(pieceColor, row, column);
   }

   @Override
   public String getIcon() {
      return pieceColor.equals("white") ? "\u2658" : "\u265E";
   }

   @Override
   public List<int[]> possibleMoves(Piece[][] boardPosition) {
      List<int[]> moves = new ArrayList<>();
      int[][] directions = { 
        {2, 1}, {2, -1}, {-2,1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
      };

      for (int[] direction : directions) {
         int moveRow = row + direction[0];
         int moveColumn = column + direction[1];
         if (Board.isInBounds(moveRow, moveColumn) && (boardPosition[moveRow][moveColumn] == null || !boardPosition[moveRow][moveColumn].getColor().equals(pieceColor))) {
            moves.add(new int[]{moveRow, moveColumn});
         }
      }
      return moves;
   }
}
