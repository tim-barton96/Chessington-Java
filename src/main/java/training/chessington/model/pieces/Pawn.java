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

        //out of bounds check
        if (this.colour == PlayerColour.WHITE && whiteMoveOne.getRow() < 0) {
            return allowedMoves;
        } 
        if (this.colour == PlayerColour.BLACK && blackMoveOne.getRow() > 7) {
            return allowedMoves;
        }


         //capture!!!
         switch (this.colour) {
            case BLACK:
                if (blackCaptureRight.getCol() < 8 &&!board.isSpaceEmpty(blackCaptureRight) && board.get(blackCaptureRight).getColour() == PlayerColour.WHITE) {
                    allowedMoves.add(new Move(from, blackCaptureRight));
                }
                if (blackCaptureLeft.getCol() >= 0 && !board.isSpaceEmpty(blackCaptureLeft) && board.get(blackCaptureLeft).getColour() == PlayerColour.WHITE) {
                    allowedMoves.add(new Move(from, blackCaptureLeft));
                }
            case WHITE: 
                if (whiteCaptureRight.getCol() < 8 && !board.isSpaceEmpty(whiteCaptureRight) && board.get(whiteCaptureRight).getColour() == PlayerColour.BLACK) {
                    allowedMoves.add(new Move(from, whiteCaptureRight));
                    }
                if (whiteCaptureLeft.getCol() >= 0 && !board.isSpaceEmpty(whiteCaptureLeft) && board.get(whiteCaptureLeft).getColour() == PlayerColour.BLACK) {
                    allowedMoves.add(new Move(from, whiteCaptureLeft));
                }
        }
        
        //blocked in check
        if (this.colour == PlayerColour.WHITE && !board.isSpaceEmpty(whiteMoveOne)) {
            return allowedMoves;
        } else if (this.colour == PlayerColour.BLACK && !board.isSpaceEmpty(blackMoveOne)) {
            return allowedMoves;
        }
    
    
        //standard move
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
                    if (board.isSpaceEmpty(blackMoveTwo) && from.getRow() == 1) {
                        allowedMoves.add(new Move(from, blackMoveTwo));
                    }
                case WHITE: 
                    if (board.isSpaceEmpty(whiteMoveTwo) && from.getRow() == 6) {
                        allowedMoves.add(new Move(from, whiteMoveTwo));
                    }
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
