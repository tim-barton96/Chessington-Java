package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BishopTest {

    @Test
    public void bishopCanMoveUpOneLeftOne() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
    }

    @Test
    public void bishopCannotMoveOverPieces() {

        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, bishop);
        board.placePiece(coords.plus(1,1), pawn);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(1, 1)));
    }
}
