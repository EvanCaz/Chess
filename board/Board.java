package board;

import java.util.List;

import board.pieces.*;

/**
 * Represents the chessboard. This class manages the board output and state.
 */
public class Board {
   private Piece[][] chessBoard;
   private int[] whiteKingPosition;
   private int[] blackKingPosition;

   /**
    * Creates an empty board and calls the board initializer.
    */
   public Board() {
      chessBoard = new Piece[8][8];
      initializeChessBoard();
   }
   
   /**
    * Initializes the board with all pieces in their starting location.
    * Initializes the king positions
    */
   private void initializeChessBoard() {
      
      chessBoard[0][0] = new Rook("black", 0, 0);
      chessBoard[0][1] = new Knight("black", 0, 1);
      chessBoard[0][2] = new Bishop("black", 0, 2);
      chessBoard[0][3] = new Queen("black", 0, 3);
      chessBoard[0][4] = new King("black", 0, 4);
      blackKingPosition = new int[]{0, 4};
      chessBoard[0][5] = new Bishop("black", 0, 5);
      chessBoard[0][6] = new Knight("black", 0, 6);
      chessBoard[0][7] = new Rook("black", 0, 7);

      for (int i = 0; i < 8; i++){   //Initializes all 8 black pawns with a for loop
         chessBoard[1][i] = new Pawn("black", 1, i);
      }

      chessBoard[7][0] = new Rook("white", 7, 0);
      chessBoard[7][1] = new Knight("white", 7, 1);
      chessBoard[7][2] = new Bishop("white", 7, 2);
      chessBoard[7][3] = new Queen("white", 7, 3);
      chessBoard[7][4] = new King("white", 7, 4);
      whiteKingPosition = new int[]{7, 4};
      chessBoard[7][5] = new Bishop("white", 7, 5);
      chessBoard[7][6] = new Knight("white", 7, 6);
      chessBoard[7][7] = new Rook("white", 7, 7);

      for (int i = 0; i < 8; i++){   //Initializes all 8 white pawns with a for loop
        chessBoard[6][i] = new Pawn("white", 6, i);
     }
   }
   
   public static boolean isInBounds(int row, int column) {
      return row >= 0 && row < 8 && column >= 0 && column < 8;
   }

   /**
    * Helper method to move pieces. Updates the new positions of the King instances.
    * @param int[] array indicating columns and rows
    * @return true if move was valid and completed, false otherwise.
    */
    public boolean movePiece(int[] moveIndices) {
      int fromColumn = moveIndices[0];
      int fromRow = moveIndices[1];
      int toColumn = moveIndices[2];
      int toRow = moveIndices[3];
      boolean moveSuccessful = false;
      
      Piece pieceToMove = getPieceAt(fromColumn, fromRow);
      Piece moveToSpace = getPieceAt(toColumn, toRow);
      if (pieceToMove != null && pieceToMove instanceof King) {
         String kingColor = pieceToMove.getColor();
         if (moveToSpace != null) {
            chessBoard[toRow][toColumn] = null;
         }
         setKingPostion(pieceToMove, toRow, toColumn);
         
         if (isInCheck(kingColor)) {
            setKingPostion(pieceToMove, fromRow, fromColumn);
            chessBoard[toRow][toColumn] = moveToSpace;
            return moveSuccessful; 
         }

         moveSuccessful = pieceToMove.movePiece(chessBoard, toRow, toColumn); // calls movePiece

         if (!moveSuccessful) {
            setKingPostion(pieceToMove, fromRow, fromColumn);
            chessBoard[toRow][toColumn] = moveToSpace;
         }
         
         return moveSuccessful;

      } else if (pieceToMove != null) {
         moveSuccessful = pieceToMove.movePiece(chessBoard, toRow, toColumn); 
         

         return moveSuccessful;
      }
      return moveSuccessful;
   }


   /**
    * Gets a piece at a specific position.
    *Takes that input in [column][row] to match chess notation, and returns in [row][column] to
    *match matrix notation.
    * @param column
    * @param row
    * @return a Piece object.
    */
   public Piece getPieceAt(int column, int row) {
      if (isInBounds(row, column)){
         return chessBoard[row][column];
      }
      return null;
   }


   /**
    * Returns the position[row][column] of the king piece given the color
    * @param kingColor
    * @return an int[] with the position of the king.
    */
   public int[] getKingPosition(String kingColor) {
      return kingColor.equals("white") ? whiteKingPosition : blackKingPosition;
   }

   private void setKingPostion(Piece kingPiece, int row, int column) {
      if (kingPiece.getColor().equals("white")) {
         whiteKingPosition[0] = row;
         whiteKingPosition[1] = column;
      } else {
         blackKingPosition[0] = row;
         blackKingPosition[1] = column;
      }
      
   }
   
   /**
    * Checks of the king, given the color of the king is in check from opponent pieces.
    *
    * @param kingColor
    * @return true if the king is in check, false otherwise.
    */
    public boolean isInCheck(String kingColor) {
      int[] kingSpace = kingColor.equals("white") ? whiteKingPosition : blackKingPosition;
      for (int i = 0; i < 8; i++) {
          for (int j = 0; j < 8; j++) {
              Piece checkPiece = chessBoard[i][j];
              if (checkPiece != null && !checkPiece.getColor().equals(kingColor)) {
                  List<int[]> possibleMoves = checkPiece.possibleMoves(chessBoard);
                  for (int[] move : possibleMoves) {
                      if (move[0] == kingSpace[0] && move[1] == kingSpace[1]) {
                          return true;
                      }
                  }
              }
          }
      }
      return false;
  }
   
   /**
    * Checks if a king is in check mate status, based on if it's in check, if it can move, or if
    * another piece can block/capture the opposing piece.  
    *
    * @param kingColor
    * @return false if it's not check, can be moved, or can be blocked, true otherwise.
    */
   public boolean isInCheckMate(String kingColor) {
      int[] kingSpace = kingColor.equals("white") ? whiteKingPosition : blackKingPosition;
      
      // Checks if the king is in check
      if (!isInCheck(kingColor)) {
         return false;
      }
      
      // Checks if the king can move out of the check
      Piece kingPiece = chessBoard[kingSpace[0]][kingSpace[1]];
      List<int[]> kingMoves = kingPiece.possibleMoves(chessBoard);
      for (int[] move : kingMoves) {
         int toRow = move[0];
         int toColumn = move[1];
         Piece placeHolder = chessBoard[toRow][toColumn];
         
         chessBoard[toRow][toColumn] = kingPiece; 
         chessBoard[kingSpace[0]][kingSpace[1]] = null;
         boolean possibleEscape = !isInCheck(kingColor);
         
         chessBoard[kingSpace[0]][kingSpace[1]] = kingPiece;
         chessBoard[toRow][toColumn] = placeHolder;

         if (possibleEscape) { 
            return false;
         }
      }
      
      // Checks if any pieces can block/capture to save the king
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
            Piece checkPiece = chessBoard[i][j];
            if (checkPiece != null && checkPiece.getColor().equals(kingColor)) {
               List<int[]> possibleMoves = checkPiece.possibleMoves(chessBoard);
               for (int[] move : possibleMoves) {
                  int toRow = move[0];
                  int toColumn = move[1];
                  Piece placeHolder = chessBoard[toRow][toColumn];

                  chessBoard[toRow][toColumn] = checkPiece; 
                  chessBoard[i][j] = null;
                  boolean possibleBlock = !isInCheck(kingColor);

                  chessBoard[i][j] = checkPiece;
                  chessBoard[toRow][toColumn] = placeHolder;

                  if (possibleBlock) {
                     return false;
                  }
               }
            }
         }
      }
      return true; //all conditions for a checkmate were false
   }
}
