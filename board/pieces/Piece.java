package board.pieces;

import java.util.List;

/**
 * Superclass for all Piece objects. Handles piece movement and abstract subclass specific features.
 */
public abstract class Piece {
   protected String pieceColor;
   protected int row;
   protected int column;

   /**
    * Constructor for Piece. Initializes a Piece object.
    *
    * @param pieceColor
    * @param row
    * @param column
    */
   public Piece(String pieceColor, int row, int column) {
      this.pieceColor = pieceColor;
      this.row = row;
      this.column = column;
   }
   
   /**
    * Moves the piece to a new position.
    *
    * @param boardPostion
    * @param moveToRow
    * @param moveToColumn
    * @return true if the move is valid, otherwise false.
    */
   public boolean movePiece(Piece[][] boardPostion, int moveToRow, int moveToColumn) {
      List<int[]> canMoveTo = possibleMoves(boardPostion);
      for (int[] move : canMoveTo) {
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
   
   /**
    * Gets the row of a piece object.
    * @return row of piece object.
    */
   public int getRow() {
      return row;
   }

   /**
    * Gets the column of a piece object.
    * @return column of piece object
    */
   public int getColumn() {
      return column;
   }

   /**
    * Gets the color.
    *
    * @return color of the piece.
    */
   public String getColor() {
      return pieceColor;
   }
   
   /**
    * Abstract method that gets the icon. Different for each subclass.
    *
    * @return the icon of the piece in unicode for a GUI implementation.
    */
   public abstract String getIcon();
   
   /**
    * Abstract method that checks possible moves of a piece. Different for each subclass.
    * Takes the board position of a piece and extrapolates where that piece could move.
    *
    * @param boardPosition
    * @return a list of possible moves for the piece at current positioin.
    */
   public abstract List<int[]> possibleMoves(Piece[][] boardPosition);

}
