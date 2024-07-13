package Pieces;

import java.util.List;

public abstract class Piece {
   protected String pieceColor;
   protected int row;
   protected int column;

   public Piece(String pieceColor, int row, int column) {
      this.pieceColor = pieceColor;
      this.row = row;
      this.column = column;
   }
   
   public boolean movePiece(Piece[][] boardPostion, int moveToRow, int moveToColumn) {
      List<int[]> canMoveTo = possibleMoves(boardPostion);
      for (int[] move : canMoveTo) {   //TAKE A LOOOK AT THIS POSSIBLEMOVES CALL
         if (move[0] == moveToRow && move[1] == moveToColumn) {
            boardPostion[this.row][this.column] = null;
            this.row = moveToRow;
            this.column = moveToColumn;
            boardPostion[moveToRow][moveToColumn] = this;
            return true;
         }
      }
      return false;
   }

   public String getColor() {
      return pieceColor;
   }

   public abstract String getIcon();
   
   public abstract List<int[]> possibleMoves(Piece[][] boardPosition);

}
