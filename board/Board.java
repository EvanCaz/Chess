package board;

import java.util.ArrayList;
import java.util.List;

import board.pieces.*;

/**
 * Represents the chessboard. This class manages the board output and state.
 */
public class Board {
   private Piece[][] chessBoard;
   private List<Piece> capturedPieces;

   /**
    * Creates an empty board and calls the board initializer.
    */
   public Board() {
      chessBoard = new Piece[8][8];
      initializeChessBoard();
      capturedPieces = new ArrayList<>();
   }
   
   /**
    * Initializes the board with all pieces in their starting location.
    */
   private void initializeChessBoard() {
      
      chessBoard[0][0] = new Rook("black", 0, 0);
      chessBoard[0][1] = new Knight("black", 0, 1);
      chessBoard[0][2] = new Bishop("black", 0, 2);
      chessBoard[0][3] = new Queen("black", 0, 3);
      chessBoard[0][4] = new King("black", 0, 4);
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
      chessBoard[7][5] = new Bishop("white", 7, 5);
      chessBoard[7][6] = new Knight("white", 7, 6);
      chessBoard[7][7] = new Rook("white", 7, 7);

      for (int i = 0; i < 8; i++){   //Initializes all 8 white pawns with a for loop
        chessBoard[6][i] = new Pawn("white", 6, i);
     }
   }

   /**
    * Prints the current state of the chessboard to the console.
    */

   public void printChessBoard() {
      System.out.println("  A  B  C  D  E  F  G  H");
      for (int i = 0; i < 8; i++){
         System.out.print((8 - i) + " ");
         for (int j = 0; j < 8; j++){
            Piece boardSpace = chessBoard[i][j];
            if (boardSpace != null) { 
               System.out.print(boardSpace.getIcon() + " ");
            } else {
               if ((i + j) % 2 == 1) {
                  System.out.print("## ");
               } else {
                  System.out.print("   ");
               }
            }
         }
         System.out.println();
      }
      System.out.println();
   }
   
   public static boolean isInBounds(int row, int column) {
      return row >= 0 && row < 8 && column >= 0 && column < 8;
   }

   /**
    * Helper method to move pieces. Uses 
    * @param int[] array indicating columns and rows
    * @return true if move was valid and completed, false otherwise.
    */
   public boolean movePiece(int[] moveIndices) {
      int fromColumn = moveIndices[0];
      int fromRow = moveIndices[1];
      int toColumn = moveIndices[2];
      int toRow = moveIndices[3];
      
      Piece pieceToMove = getPieceAt(fromColumn, fromRow); //changed to match the method
      if (pieceToMove != null) {
         Piece moveToSpace = getPieceAt(toColumn, toRow); //changed to match the method
         if (moveToSpace != null && !moveToSpace.getColor().equals(pieceToMove.getColor())) {
            addCapturedPiece(moveToSpace);
         }      
         return pieceToMove.movePiece(chessBoard, toRow, toColumn);
      }
      return false;
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
    * Keeps a running list of pieces that have been captured.
    * @param piece
    */
   public void addCapturedPiece(Piece piece) {
      capturedPieces.add(piece);
   }
   
   /**
    * Returns a list of pieces that have been captured.
    * @return a list of captured pieces.
    */
   public List<Piece> getCapturedPieces() {
      return capturedPieces;
   }
}
