package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        List<Move> allowedMoves = new ArrayList<>();

        String[] upLeft = {"Up", "Left"};
        allowedMoves.addAll(getDiagonalMoves(from, board, upLeft));

        String[] upRight = {"Up", "Right"};
        allowedMoves.addAll(getDiagonalMoves(from, board, upRight));

        String[] downLeft = {"Down", "Left"};
        allowedMoves.addAll(getDiagonalMoves(from, board, downLeft));

        String[] downRight ={"Down", "Right"};
        allowedMoves.addAll(getDiagonalMoves(from, board, downRight));

        return allowedMoves;
    }


    public List<Move> getDiagonalMoves(Coordinates from, Board board, String[] whichDirectionArray) {

        List<Move> lineMoves = new ArrayList<>();
        boolean isBlocked = false;
        int col = whichDirectionArray[0].equals("Down")? 1: -1;
        int row = whichDirectionArray[1].equals("Right")? 1: -1;
        int counter = 1;

        while (counter < 8 && !isBlocked){
            Coordinates moveToCoords = new Coordinates(from.getRow() + counter * row, from.getCol() + counter * col);
            //(upLeft, upLeftBlocked, board)
            if (isSpaceInBounds(moveToCoords) && (board.isSpaceEmpty(moveToCoords) || isOpponentInSpace(moveToCoords, board))) {
                lineMoves.add(new Move(from, moveToCoords));
                if (isOpponentInSpace(moveToCoords, board)) {
                    isBlocked = true;
                }
            } else {
                isBlocked = true;
            }
            counter++;
        }

        return lineMoves;
    }
}
