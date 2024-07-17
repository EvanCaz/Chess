import java.util.List;
import board.pieces.Piece;
import board.Board;


public class BoardTester {
    public static void main(String[] args) {
        Board testBoard = new Board();
        testBoard.printChessBoard();

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
}