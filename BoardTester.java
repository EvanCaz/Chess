import board.Board;

public class BoardTester {
    public static void main(String[] args) {
        Board testBoard = new Board();
        testBoard.printChessBoard();

        testBoard.movePiece(1, 0, 3, 0);
        testBoard.printChessBoard();
        
    }
}