import java.util.List;
import board.pieces.Piece;
import board.Board;

public class BoardTester {
    public static void main(String[] args) {
        Board testBoard = new Board();
        testBoard.printChessBoard();

        testBoard.movePiece(1, 0, 3, 0);
        testBoard.printChessBoard();

        testBoard.movePiece(3, 0, 4, 0);
        testBoard.printChessBoard();

        testBoard.movePiece(4, 0, 5, 0);
        testBoard.printChessBoard();

        testBoard.movePiece(5, 0, 6, 1);
        testBoard.printChessBoard();
        
        List<Piece> capturedPieces = testBoard.getCapturedPieces();
        System.out.println(capturedPieces);

        testBoard.movePiece(0, 0, 6, 0);
        testBoard.printChessBoard();

        capturedPieces = testBoard.getCapturedPieces();
        System.out.println(capturedPieces);

        testBoard.movePiece(6, 0, 7, 0);
        testBoard.printChessBoard();

        capturedPieces = testBoard.getCapturedPieces();
        System.out.println(capturedPieces);

        testBoard.movePiece(7, 0, 7, );
        testBoard.printChessBoard();

        
    }
}