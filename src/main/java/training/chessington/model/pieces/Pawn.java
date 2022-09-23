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

        Coordinates blackMoveOne = from.plus(1, 0);
        Coordinates blackMoveTwo = from.plus(2 ,0 );
        Coordinates blackCaptureLeft = from.plus(1,-1);
        Coordinates blackCaptureRight = from.plus(1,1);

        Coordinates whiteMoveOne = from.plus(-1, 0);
        Coordinates whiteMoveTwo = from.plus(-2,0);
        Coordinates whiteCaptureLeft = from.plus(-1,-1);
        Coordinates whiteCaptureRight = from.plus(-1,1);
        
        //blocked in check
        if (this.colour == PlayerColour.WHITE && !board.isSpaceEmpty(whiteMoveOne)) {
            return allowedMoves;
        } else if (this.colour == PlayerColour.BLACK && !board.isSpaceEmpty(blackMoveOne)) {
            return allowedMoves;
        }
    
    
        //edge of board check
        if (this.colour == PlayerColour.WHITE) {
            allowedMoves.add(new Move(from, whiteMoveOne));
        } 
        if (this.colour == PlayerColour.BLACK) {
            allowedMoves.add(new Move(from, blackMoveOne));
        }
        
        //starting move check
        if (!this.hasMoved) {
            switch (this.colour) {
                case BLACK: 
                    if (board.isSpaceEmpty(blackMoveTwo)) {
                        allowedMoves.add(new Move(from, blackMoveTwo));
                    }
                case WHITE: 
                    if (board.isSpaceEmpty(whiteMoveTwo)) {
                        allowedMoves.add(new Move(from, whiteMoveTwo));
                    }
            }
        }

        //capture!!!
        switch (this.colour) {
            case BLACK:
                if (!board.isSpaceEmpty(blackCaptureRight) && board.get(blackCaptureRight).getColour() == PlayerColour.WHITE) {
                    allowedMoves.add(new Move(from, blackCaptureRight));
                }
                if (!board.isSpaceEmpty(blackCaptureLeft) && board.get(blackCaptureLeft).getColour() == PlayerColour.WHITE) {
                    allowedMoves.add(new Move(from, blackCaptureLeft));
                }
            case WHITE: 
                if (!board.isSpaceEmpty(whiteCaptureRight) && board.get(whiteCaptureRight).getColour() == PlayerColour.BLACK) {
                    allowedMoves.add(new Move(from, whiteCaptureRight));
                    }
                if (!board.isSpaceEmpty(whiteCaptureLeft) && board.get(whiteCaptureLeft).getColour() == PlayerColour.BLACK) {
                    allowedMoves.add(new Move(from, whiteCaptureLeft));
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
