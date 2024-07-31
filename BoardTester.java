import java.util.List;
import board.pieces.Piece;
import board.Board;


public class BoardTester {
    public static void main(String[] args) {
        String white = "white";
        String black = "black";

        Board testBoard = new Board();
        int[] playerOneColor = testBoard.getKingPosition(white);
        int[] playerTwoColor = testBoard.getKingPosition(black);
        testBoard.printChessBoard();
        System.out.println(playerOneColor[0] + " " + playerOneColor[1]);
        System.out.println(playerTwoColor[0] + " " + playerTwoColor[1]);

        // testPieceColors(testBoard);
        
        String move = "A7 A5";
        int[] moveIndices = Utils.stringToIndex(move);

        testBoard.movePiece(moveIndices);
        testBoard.printChessBoard();

        move = "A5 A4";
        moveIndices = Utils.stringToIndex(move);

        testBoard.movePiece(moveIndices);
        testBoard.printChessBoard();

        move = "A4 A3";
        moveIndices = Utils.stringToIndex(move);

        testBoard.movePiece(moveIndices);
        testBoard.printChessBoard();

        move = "A3 B2";
        moveIndices = Utils.stringToIndex(move);

        testBoard.movePiece(moveIndices);
        testBoard.printChessBoard();
        
        List<Piece> capturedPieces = testBoard.getCapturedPieces();
        if (!capturedPieces.isEmpty()) {
            System.out.println("Captured Pieces:");
            for (Piece piece : capturedPieces) {
                System.out.println(piece.getIcon());
            }
        } else {
            System.out.println("No pieces captured.");
        }        
    }
    // private static void testPieceColors(Board board) {
    //     System.out.println("Testing piece colors on the board:");
    //     for (int row = 0; row < 8; row++) {
    //         for (int col = 0; col < 8; col++) {
    //             Piece piece = board.getPieceAt(col, row);
    //             if (piece != null) {
    //                 System.out.println("Piece at (" + row + ", " + col + "): " + piece.getColor() + " " + piece.getIcon());
    //             }
    //         }
    //     }
    // }
}

