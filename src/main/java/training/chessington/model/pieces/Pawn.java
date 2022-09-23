package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }
    private boolean hasMoved = false;

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();

        allowedMoves.add(new Move(from, from.plus(1,0)));
        allowedMoves.add(new Move(from, from.plus(-1, 0)));

        if (!this.hasMoved) {
            switch (this.colour) {
                case BLACK: allowedMoves.add(new Move(from, from.plus(2,0)));
                case WHITE:allowedMoves.add(new Move(from, from.plus(-2,0)));
            }
        }


        return allowedMoves;
    }

    
    public void changeHasMoved() {
        this.hasMoved = true;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }
}
