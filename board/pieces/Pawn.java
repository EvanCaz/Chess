package board.pieces;

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
        return pieceColor.charAt(0) + "P";
    }

    @Override
    public List<int[]> possibleMoves(Piece[][] boardPosition) {
        List<int[]> moves = new ArrayList<>();
        int directions = pieceColor.equals("black") ? 1 : -1;

        int moveRow = row + directions;
        int moveColumn = column;

        if (isInBounds(moveRow, moveColumn) && boardPosition[moveRow][moveColumn] == null) {
            moves.add(new int[]{moveRow, moveColumn});
            if (firstMove) {
                moveRow += directions;
                if (isInBounds(moveRow, moveColumn) && boardPosition[moveRow][moveColumn] == null) {
                    moves.add(new int[]{moveRow, moveColumn});
                }
            }
        }

        int[][] captureMoves = { {directions, 1}, {directions, -1} };
        for (int[] direction : captureMoves) {
            moveRow = row + direction[0];
            moveColumn = column + direction[1];
            if (isInBounds(moveRow, moveColumn) && boardPosition[moveRow][moveColumn] != null && !boardPosition[moveRow][moveColumn].getColor().equals(pieceColor)) {
                moves.add(new int[]{moveRow, moveColumn});
            }
        }
        return moves;
    }

    private boolean isInBounds(int row, int column) {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }
}