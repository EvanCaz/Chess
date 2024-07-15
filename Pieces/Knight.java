package Pieces;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
   public Knight(String pieceColor, int row, int column) {
      super(pieceColor, row, column);
   }

   @Override
   public String getIcon() {
      return pieceColor.charAt(0) + "N";
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
         if (isInBounds(moveRow, moveColumn) && (boardPosition[moveRow][moveColumn] == null || !boardPosition[moveRow][moveColumn].getColor().equals(pieceColor))) {
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
