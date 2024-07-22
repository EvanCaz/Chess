package board.pieces;

import board.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * Pawn subclass for the Piece class. Handles Pawn specific methods for the Piece class.
 */
public class Pawn extends Piece {
    private boolean firstMove;

    public Pawn(String pieceColor, int row, int column) {
        super(pieceColor, row, column);
        firstMove = true;
    }

    @Override
    public boolean movePiece(Piece[][] boardPosition, int moveToRow, int moveToColumn) {
        List<int[]> canMoveTo = possibleMoves(boardPosition);
        for (int[] move : canMoveTo) {
            if (move[0] == moveToRow && move[1] == moveToColumn) {
                boardPosition[this.row][this.column] = null;
                this.row = moveToRow;
                this.column = moveToColumn;
                boardPosition[moveToRow][moveToColumn] = this;
                this.firstMove = false; // Update firstMove after moving
                return true;
            }
        }
        return false;
    }

    @Override
    public String getIcon() {
        return pieceColor.equals("white") ? "\u2659" : "\u265F";
    }

    @Override
    public List<int[]> possibleMoves(Piece[][] boardPosition) {
        List<int[]> moves = new ArrayList<>();
        int directions = pieceColor.equals("black") ? 1 : -1;

        int moveRow = row + directions;
        int moveColumn = column;

        if (Board.isInBounds(moveRow, moveColumn) && boardPosition[moveRow][moveColumn] == null) {
            moves.add(new int[]{moveRow, moveColumn});
            if (firstMove) {
                moveRow += directions;
                if (Board.isInBounds(moveRow, moveColumn) && boardPosition[moveRow][moveColumn] == null) {
                    moves.add(new int[]{moveRow, moveColumn});
                }
            }
        }

        int[][] captureMoves = { {directions, 1}, {directions, -1} };
        for (int[] direction : captureMoves) {
            moveRow = row + direction[0];
            moveColumn = column + direction[1];
            if (Board.isInBounds(moveRow, moveColumn) && boardPosition[moveRow][moveColumn] != null && !boardPosition[moveRow][moveColumn].getColor().equals(pieceColor)) {
                moves.add(new int[]{moveRow, moveColumn});
            }
        }
        return moves;
    }
}