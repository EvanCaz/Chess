import java.util.ArrayList;
import java.util.List;

import Pieces.Piece;
import Pieces.Rook;
import Pieces.Knight;
import Pieces.Bishop;
import Pieces.Queen;
import Pieces.King;
import Pieces.Pawn;

class Board {
   private Piece[][] chessBoard;
   private List<Piece> capturedPieces;

   public Board() {
      chessBoard = new Piece[8][8];
      initializeChessBoard();
      capturedPieces = new ArrayList<>();
   }

   public void initializeChessBoard() {
      
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

   public void addCapturedPiece(Piece piece) {
      capturedPieces.add(piece);
   }
   
   public List<Piece> getCapturedPieces() {
      return capturedPieces;
   }
}
