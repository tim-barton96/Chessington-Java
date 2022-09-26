package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

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

    public List<Move> getDirectionMoves(Coordinates from, Board board, String direction) {

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

    public List<Move> getXYMoves(Coordinates from, Board board) {

        List<Move> xyMoves = new ArrayList<>();

        xyMoves.addAll(getDirectionMoves(from, board, "Left"));
        xyMoves.addAll(getDirectionMoves(from, board, "Right"));
        xyMoves.addAll(getDirectionMoves(from, board, "Up"));
        xyMoves.addAll(getDirectionMoves(from, board, "Down"));

        return xyMoves;
    }

    public List<Move> getDiagonalMovesAlongLine(Coordinates from, Board board, String[] whichDirectionArray) {

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

    public List<Move> getAllDiagonalMoves(Coordinates from, Board board) {

        String[] upLeft = {"Up", "Left"};
        List<Move> diagonalMoves = new ArrayList<>(getDiagonalMovesAlongLine(from, board, upLeft));

        String[] upRight = {"Up", "Right"};
        diagonalMoves.addAll(getDiagonalMovesAlongLine(from, board, upRight));

        String[] downLeft = {"Down", "Left"};
        diagonalMoves.addAll(getDiagonalMovesAlongLine(from, board, downLeft));

        String[] downRight ={"Down", "Right"};
        diagonalMoves.addAll(getDiagonalMovesAlongLine(from, board, downRight));

        return diagonalMoves;
    }

}
