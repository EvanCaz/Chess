import java.util.ArrayList;
import java.util.List;

class Board {
   private String[][] chessBoard;
   private List<String> capturedPieces;

   public Board() {
      chessBoard = new String[8][8];
      initializeChessBoard();
      capturedPieces = new ArrayList<>();
   }

   public void initializeChessBoard() {
      for (int i = 0; i < 8; i++) { //Initializes the chessboard with spaces/hashes
         for (int j = 0; j < 8; j++) {
            if ((i+j) %2 == 0) {
               chessBoard[i][j] = " ";
            } else {
               chessBoard[i][j] = " ##";
            }
         }
      }
      
      chessBoard[0][0] = "bR";
      chessBoard[0][1] = "bN";
      chessBoard[0][2] = "bB";
      chessBoard[0][3] = "bQ";
      chessBoard[0][4] = "bK";
      chessBoard[0][5] = "bB";
      chessBoard[0][6] = "bN";
      chessBoard[0][7] = "bR";

      for (int i = 0; i < 8; i++){   //Initializes all 8 black pawns with a for loop
         chessBoard[1][i] = "bP";
      }

      chessBoard[7][0] = "wR";
      chessBoard[7][1] = "wN";
      chessBoard[7][2] = "wB";
      chessBoard[7][3] = "wQ";
      chessBoard[7][4] = "wK";
      chessBoard[7][5] = "wB";
      chessBoard[7][6] = "wN";
      chessBoard[7][7] = "wR";

      for (int i = 0; i < 8; i++){   //Initializes all 8 white pawns with a for loop
        chessBoard[6][i] = "wP";
     }
   }
   
   public void addCapturedPiece(String piece) {
      capturedPieces.add(piece);
   }
   
   public List<String> getCapturedPieces() {
      return capturedPieces;
   }

   public void printChessBoard() {
      System.out.println("  A  B  C  D  E  F  G  H");
      for (int i = 0; i < 8; i++){
         System.out.print((8 - i) + " ");
         for (int j = 0; j < 8; j++){
            System.out.print(chessBoard[i][j] + " ");
         }
         System.out.println();
      }
      System.out.println();
   }
}
