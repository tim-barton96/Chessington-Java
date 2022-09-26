package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        List<Move> allowedMoves = new ArrayList<>();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Coordinates newSpace = new Coordinates(from.getRow() + i, from.getCol() + j);
                if (isSpaceInBounds(newSpace) && (board.isSpaceEmpty(newSpace) || isOpponentInSpace(newSpace, board))) {
                    allowedMoves.add(new Move(from, newSpace));
                }
            }
        }
        return allowedMoves;
    }
}
