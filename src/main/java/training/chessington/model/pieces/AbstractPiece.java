package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.PlayerColour;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
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
