package board.pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;

/**
 * King subclass for the Piece class. Handles King specific methods for the Piece class.
 */
public class King extends Piece {
   public King(String pieceColor, int row, int column) {
      super(pieceColor, row, column);
   }

   @Override
   public String getIcon() {
    return pieceColor.equals("white") ? "\u2654" : "\u265A";
   }

   @Override
   public List<int[]> possibleMoves(Piece[][] boardPosition) {
      List<int[]> moves = new ArrayList<>();
      int[][] directions = {
          {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1} 
      };
      
      for (int[] direction : directions) {
          int moveRow = row + direction[0];
          int moveColumn = column + direction[1];
          
          // Check if the position is within bounds of the board
          if (Board.isInBounds(moveRow, moveColumn)) {
              Piece targetPiece = boardPosition[moveRow][moveColumn];
              
              // Check if the target position is either empty or occupied by an opponent's piece
              if (targetPiece == null || !targetPiece.getColor().equals(this.getColor())) {
                  moves.add(new int[]{moveRow, moveColumn});
              }
          }
      }
      
      return moves;
  }
}
