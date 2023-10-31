package cs3500.Reversi.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test implementation specific of model
 */
public class ReversiModelImplementationTest {

  ReversiModelImplementation model;

  @Before
  public void setUp() throws Exception {
    model = new ReversiModelImplementation();
    model.startGame(6, Disc.white);
  }

  @Test
  public void requireGameStarted() {
    model.requireGameStarted();
    model = new ReversiModelImplementation();
    Assert.assertThrows(IllegalStateException.class, () -> model.requireGameStarted());
  }

  @Test
  public void getNeighbor() {
    assertNull(model.getNeighbor(model.getCell(0, 0), Direction.west));
    assertNull(model.getNeighbor(model.getCell(0, 0), Direction.northEast));
    assertNull(model.getNeighbor(model.getCell(0, 0), Direction.northWest));

    assertEquals(model.getCell(0, 1), model.getNeighbor(model.getCell(0, 0), Direction.east));
    assertEquals(model.getCell(1, 1), model.getNeighbor(model.getCell(0, 0), Direction.southEast));
    assertEquals(model.getCell(1, 0), model.getNeighbor(model.getCell(0, 0), Direction.southWest));


    model = new ReversiModelImplementation();
    model.startGame(4, Disc.white);

    CellImplementation cell = model.getCell(6, 3);

    assertNull(model.getNeighbor(cell, Direction.east));
    assertNull(model.getNeighbor(cell, Direction.southWest));
    assertNull(model.getNeighbor(cell, Direction.southEast));

    assertEquals(model.getCell(6, 2), model.getNeighbor(cell, Direction.west));
    assertEquals(model.getCell(5, 3), model.getNeighbor(cell, Direction.northWest));
    assertEquals(model.getCell(5, 4), model.getNeighbor(cell, Direction.northEast));
  }

  @Test
  public void getLine() {

    assertEquals(0, model.getLine(model.getCell(0, 0), Direction.northEast).size());
    assertEquals(10, model.getLine(model.getCell(0, 0), Direction.southEast).size());
    assertEquals(5, model.getLine(model.getCell(0, 0), Direction.southWest).size());
    assertEquals(5, model.getLine(model.getCell(0, 0), Direction.east).size());
    assertEquals(0, model.getLine(model.getCell(0, 0), Direction.west).size());

  }

  @Test
  public void putDiscLine() {

    assertFalse(model.putDiscLine(model.getLine(model.getCell(0, 0), Direction.northEast), Disc.white));
    assertFalse(model.putDiscLine(model.getLine(model.getCell(4, 3), Direction.northEast), Disc.white));
    assertTrue(model.putDiscLine(model.getLine(model.getCell(4, 3), Direction.east), Disc.white));
  }
}