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




        return new ArrayList<>();
    }


    public boolean isSpaceInBounds(Coordinates coords) {
        if (coords.getRow() < 0 || coords.getRow() > 7) {
            return false;
        }
        return coords.getCol() >= 0 && coords.getCol() <= 7;
    }
}
