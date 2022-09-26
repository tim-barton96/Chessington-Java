package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        List<Move> allowedMoves = new ArrayList<>();

        getAllPossibleMoves(from).stream()
                .filter(coordinates -> {
                    return board.isSpaceEmpty(coordinates) || !board.get(coordinates).getColour().equals(this.colour);
                })
                .forEach(coordinates -> {
                    allowedMoves.add(new Move(from, coordinates));
                });

        return allowedMoves;
    }

    public List<Coordinates> getAllPossibleMoves(Coordinates from) {


        //check if left, right, up or down are out of bounds
        Coordinates moveDownOneLeftTwo = new Coordinates(from.getRow()+1, from.getCol()-2);
        Coordinates moveDownOneRightTwo = new Coordinates(from.getRow()+1, from.getCol()+2);
        Coordinates moveUpOneLeftTwo = new Coordinates(from.getRow()-1, from.getCol()-2);
        Coordinates moveUpOneRightTwo = new Coordinates(from.getRow()-1, from.getCol()+2);
        Coordinates moveDownTwoLeftOne = new Coordinates(from.getRow()+2, from.getCol()-1);
        Coordinates moveDownTwoRightOne = new Coordinates(from.getRow()+2, from.getCol()+1);
        Coordinates moveUpTwoLeftOne = new Coordinates(from.getRow()-2, from.getCol()-1);
        Coordinates moveUpTwoRightOne = new Coordinates(from.getRow()-2, from.getCol()+1);

        Coordinates[] allMoveCoords = {moveDownOneLeftTwo, moveDownOneRightTwo, moveUpOneLeftTwo, moveUpOneRightTwo, moveDownTwoLeftOne, moveDownTwoRightOne, moveUpTwoLeftOne, moveUpTwoRightOne};
        List<Coordinates> validCoords = new ArrayList<>();

        for (Coordinates coord: allMoveCoords) {
            if (isSpaceInBounds(coord)) {
                validCoords.add(coord);
            }
        }
        return validCoords;
    }

}
