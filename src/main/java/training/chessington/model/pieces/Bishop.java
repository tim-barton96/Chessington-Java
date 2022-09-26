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

        boolean[] upLeft = {false, false};
        allowedMoves.addAll(getDiagonalMoves(from, board, upLeft));
        boolean[] upRight = {true, false};
        allowedMoves.addAll(getDiagonalMoves(from, board, upRight));
        boolean[] downLeft = {false, true};
        allowedMoves.addAll(getDiagonalMoves(from, board, downLeft));
        boolean[] downRight ={true, true};
        allowedMoves.addAll(getDiagonalMoves(from, board, downRight));

        return allowedMoves;
    }


    public List<Move> getDiagonalMoves(Coordinates from, Board board, boolean[] whichDirectionArray) {

        List<Move> lineMoves = new ArrayList<>();
        boolean isBlocked = false;
        int row = whichDirectionArray[0]? 1: -1;
        int col = whichDirectionArray[1]? 1: -1;
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

    public boolean isOpponentInSpace(Coordinates coords, Board board) {
        if (board.isSpaceEmpty(coords)) {
            return false;
        } else return !board.get(coords).getColour().equals(this.colour);
    }



    public boolean isSpaceInBounds(Coordinates coords) {
        if (coords.getRow() < 0 || coords.getRow() > 7) {
            return false;
        } else return coords.getCol() >= 0 && coords.getCol() <= 7;
    }

}
