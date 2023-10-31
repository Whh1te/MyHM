package cs3500.Reversi;

import cs3500.Reversi.model.Cell;
import cs3500.Reversi.model.Disc;
import cs3500.Reversi.model.ReversiModel;
import cs3500.Reversi.model.ReversiModelImplementation;

import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test public interface of model
 */
public class ReversiModelInterfaceTest {

  ReversiModel model;

  @org.junit.Before
  public void setUp() throws Exception {
    model = new ReversiModelImplementation();
    model.startGame(6, Disc.white);
  }

  @org.junit.Test
  public void getBorad() {
    List<List<Cell>> board = model.getBorad();
    assertEquals(11, board.size());
    assertEquals(6, board.get(0).size());
    assertEquals(11, board.get(5).size());
    assertEquals(6, board.get(10).size());

  }

  @org.junit.Test
  public void getBoardSize() {
    assertEquals(6, model.getBoardSize());
  }

  @org.junit.Test
  public void getCell() {
    assertEquals(Disc.white, model.getCell(5, 4).getDisc());
    assertEquals(Disc.black, model.getCell(5, 6).getDisc());
  }

  @org.junit.Test
  public void startGame() {
    Assert.assertThrows(IllegalStateException.class, () -> model.startGame(12, Disc.black));
  }

  @org.junit.Test
  public void putDisc() {
    Assert.assertThrows(IllegalArgumentException.class, () -> model.putDisc(model.getCell(0, 0), Disc.black));
    Assert.assertThrows(IllegalStateException.class, () -> model.putDisc(model.getCell(0, 0), Disc.white));

    model.putDisc(model.getCell(4, 3), Disc.white);
    assertEquals(5, model.getScore(Disc.white));
    assertEquals(2, model.getScore(Disc.black));

    model.putDisc(model.getCell(3, 4), Disc.black);
    assertEquals(4, model.getScore(Disc.white));
    assertEquals(4, model.getScore(Disc.black));

    model.putDisc(model.getCell(6, 3), Disc.white);
    assertEquals(6, model.getScore(Disc.white));
    assertEquals(3, model.getScore(Disc.black));
  }

  @org.junit.Test
  public void getScore() {
    assertEquals(3, model.getScore(Disc.white));
    assertEquals(3, model.getScore(Disc.black));
  }

  @org.junit.Test
  public void isGameOver() {
    assertFalse(model.isGameOver());
    model.pass();
    model.pass();
    assertTrue(model.isGameOver());

  }

  @org.junit.Test
  public void getTurn() {
    assertEquals(Disc.white, model.getTurn());
    model.pass();
    assertEquals(Disc.black, model.getTurn());

  }

  @org.junit.Test
  public void pass() {
    model.pass();
    assertFalse(model.isGameOver());
    assertEquals(Disc.black, model.getTurn());
    model.pass();
    assertTrue(model.isGameOver());
  }

  @org.junit.Test
  public void isLastMovePass() {
    assertFalse(model.isLastMovePass());
    model.pass();
    assertTrue(model.isLastMovePass());
    model.putDisc(model.getCell(4, 6), Disc.black);
    assertFalse(model.isLastMovePass());
    model.pass();
    assertFalse(model.isGameOver());
    assertTrue(model.isLastMovePass());
  }
}