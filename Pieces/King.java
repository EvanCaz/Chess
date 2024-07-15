package Pieces;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
   public King(String pieceColor, int row, int column) {
      super(pieceColor, row, column);
   }

   @Override
   public String getIcon() {
    return pieceColor.charAt(0) + "K";
   }

   @Override
   public List<int[]> possibleMoves(Piece[][] boardPosition) {
      List<int[]> moves = new ArrayList<>();
      int[][] directions = {
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

      for (int[] direction : directions) {
         int moveRow = direction[0];
         int moveColumn = direction[1];
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
