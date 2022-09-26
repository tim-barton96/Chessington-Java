package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KnightTest {

    @Test
    public void knightCanMoveUpOneSquareAcrossTwo() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 2)));
    }

    @Test
    public void knightCanMoveDownOneSquareAcrossTwo() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, 2)));
    }

    @Test
    public void knightCanMoveUpTwoSquareAcrossOne() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(-2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 1)));
    }

    @Test
    public void knightCanMoveDownTwoSquareAcrossOne() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, 1)));
    }
}
