package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        List<Move> allowedMoves = new ArrayList<>();

        allowedMoves.addAll(getDirectionsMoves(from, board, "Left"));
        allowedMoves.addAll(getDirectionsMoves(from, board, "Right"));
        allowedMoves.addAll(getDirectionsMoves(from, board, "Up"));
        allowedMoves.addAll(getDirectionsMoves(from, board, "Down"));


        return allowedMoves;
    }

    public List<Move> getDirectionsMoves(Coordinates from, Board board, String direction) {

        List<Move> lineMoves = new ArrayList<>();

        int row = 0;
        int col = 0;
        int counter = 1;
        int counterChange = 1;
        int max = 8;

        switch (direction) {
            case "Left" : row = 1; counter = -1; counterChange = -1; max *= -1; break;
            case "Right" : row = 1; break;
            case "Up": col = 1; counter = -1; counterChange = -1; max *= -1; break;
            case "Down": col = 1; break;
        }

        boolean isBlocked = false;


        while (counter != max && !isBlocked){
            Coordinates moveToCoords = new Coordinates(from.getRow() + (counter * row), from.getCol() + (counter * col));
            //(upLeft, upLeftBlocked, board)
            if (isSpaceInBounds(moveToCoords) && (board.isSpaceEmpty(moveToCoords) || isOpponentInSpace(moveToCoords, board))) {
                lineMoves.add(new Move(from, moveToCoords));
                if (isOpponentInSpace(moveToCoords, board)) {
                    isBlocked = true;
                }
            } else {
                isBlocked = true;
            }
            counter+= counterChange;
        }
        return lineMoves;
    }
}
