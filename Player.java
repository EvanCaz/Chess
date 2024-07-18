import java.util.List;
import java.util.ArrayList;
import board.Board;
import board.pieces.*;

class Player {
    // Attributes
    public int color; // 1 for white, 0 for black
    public List<Piece> availPieces;
    private Utils funcs;
    String move;

    // Methods
    public Player() {
        availPieces = new ArrayList<>();
        funcs = new Utils();
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


    public void makeMove(Board gameBoard) {
        // need to make sure the source piece is one avail to the player, the dest piece is one that can be taken, and the source is not an empty spot
        boolean goodMove = false;
        while (!goodMove) {
            move = funcs.moveValidation(); // makes sure it is in correct form, now we check if it is a piece that the player owns, and is a possible move
            int[] locations = funcs.stringToIndex(move);
            Piece curPiece = gameBoard.getPieceAt(locations[0], locations[1]);
            // System.out.println("Test color print: " + curPiece.getColor() + " : " + curPiece.getIcon());
            if (color == 1) {
                // System.out.println("Test inside if color  == 1");
                if (curPiece.getColor().charAt(0) == 'w') {
                    // System.out.println("Test inside if piece color == w");
                    if (!gameBoard.movePiece(locations)) {
                        System.out.println("The move you made was bad dummy.");
                    } else {
                        goodMove = true;
                        System.out.println("Successful move.");
                    }
                } else {
                    System.out.println("You cannot move that piece.");
                }
            } else if (color == 0) {
                if (curPiece.getColor().charAt(0) == 'b') { // assuming color 0 is for black pieces
                    if (!gameBoard.movePiece(locations)) {
                        System.out.println("The move you made was bad dummy.");
                    } else {
                        goodMove = true;
                        System.out.println("Successful move.");
                    }
                } else {
                    System.out.println("You cannot move that piece.");
                }
            }
        }
    }
}