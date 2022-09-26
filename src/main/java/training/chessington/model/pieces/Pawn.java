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


    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();

        Coordinates moveOne = this.colour.equals(PlayerColour.BLACK) ? from.plus(1, 0): from.plus(-1, 0);
        Coordinates moveTwo = this.colour.equals(PlayerColour.BLACK) ? from.plus(2 ,0 ): from.plus(-2,0);
        int startingRow = this.colour.equals(PlayerColour.BLACK) ? 1 : 6;
        Coordinates captureLeft = this.colour.equals(PlayerColour.BLACK) ? from.plus(1,-1): from.plus(-1,-1);
        Coordinates captureRight = this.colour.equals(PlayerColour.BLACK) ? from.plus(1,1): from.plus(-1,1);

        //out of bounds check
        if (moveOne.getRow() < 0 || moveOne.getRow() > 7) {
            return allowedMoves;
        }

        //capture!!!

        if (captureRight.getCol() < 8 && !board.isSpaceEmpty(captureRight) && board.get(captureRight).getColour() != this.colour) {
            allowedMoves.add(new Move(from, captureRight));
        }
        if (captureLeft.getCol() >= 0 && !board.isSpaceEmpty(captureLeft) && board.get(captureLeft).getColour() != this.colour) {
            allowedMoves.add(new Move(from, captureLeft));
        }

        //blocked in check
        if (!board.isSpaceEmpty(moveOne)) {
            return allowedMoves;
        }
        
        //standard move
        allowedMoves.add(new Move(from, moveOne));

        if (from.getRow() == startingRow) {
            allowedMoves.add(new Move(from, moveTwo));
        }

        return allowedMoves;
    }

}
