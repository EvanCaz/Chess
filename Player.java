


class Player {
    // Attributes

    int color; // 1 for white, 0 for black
    //public List<String> availPieces;



    // Methods

    public void makeMove(String move, Board gameBoard){ // move is alr validated
        // makes the move, probably calls gameBoard.movePiece or sum
        String source = move.substring(0,2);
        String destination = move.substring(3);
        // System.out.print(source + " :TEST " + destination);
        // System.out.print("TESTING TESTING");
    }

}
 