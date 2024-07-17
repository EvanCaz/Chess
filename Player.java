import java.util.List;
import java.util.ArrayList;
import board.Board;
import board.pieces.*;

class Player {
    // Attributes
    public int color; // 1 for white, 0 for black
    public List<Piece> availPieces;

    // Methods
    public Player() {
        availPieces = new ArrayList<>();
        if (color == 1) { // White pieces
            availPieces.add(new Rook("White", 0, 0 ));
        //     availPieces.add(new Knight
        //     availPieces.add(new Bishop
        //     availPieces.add(new Queen
        //     availPieces.add(new King
        //     availPieces.add(new Bishop
        //     availPieces.add(new Knight
        //     availPieces.add(new Rook
        // } else { // Black pieces
        //     availPieces.add(new Rook
        //     availPieces.add(new Knight
        //     availPieces.add(new Bishop
        //     availPieces.add(new Queen
        //     availPieces.add(new King
        //     availPieces.add(new Bishop
        //     availPieces.add(new Knight
        //     availPieces.add(new Rook
        }
    }


    public void makeMove(String move, Board gameBoard){ // move is alr validated
        // makes the move, probably calls gameBoard.movePiece or sum
        String source = move.substring(0,2);
        String destination = move.substring(3);

        System.out.println("Source: " + source + ". Destination: " + destination);
        // System.out.print(source + " :TEST " + destination);
        // System.out.print("TESTING TESTING");
    }

}