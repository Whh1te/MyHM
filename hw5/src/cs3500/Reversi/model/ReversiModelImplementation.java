package cs3500.Reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ReversiModel interfacce.
 * <p>
 * Class invariant: The board is always in valid state.
 * <p>
 * After startGame is called, the board is constructed and put in initial configuration.
 * <p>
 * putDisc(Cell cell, Disc disc) only changes the cell if the move is legal. Thus, the board is
 * always valid after this method call.
 */
public class ReversiModelImplementation implements ReversiModel {
  private List<List<CellImplementation>> board;

  private boolean lastMovePass;

  private boolean gameOver;

  private Disc currentTurn;

  private int boardSize;

  private boolean gameStarted;

  /**
   * Ensure game is started, otherwise throw exception
   *
   * @throws IllegalStateException if game not started yet
   */
  void requireGameStarted() throws IllegalStateException {

    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
  }

  @Override
  public List<List<Cell>> getBorad() throws IllegalStateException {

    requireGameStarted();

    List<List<Cell>> cells = new ArrayList<>();

    for (List<CellImplementation> row : board) {

      cells.add(new ArrayList<>(row));
    }

    return cells;
  }

  @Override
  public int getBoardSize() throws IllegalStateException {
    requireGameStarted();
    return boardSize;
  }

  @Override
  public CellImplementation getCell(int row, int col) throws IllegalStateException {
    requireGameStarted();

    if (row < 0 || row >= board.size()) {
      return null;
    }

    List<CellImplementation> r = board.get(row);

    if (col < 0 || col >= r.size()) {

      return null;
    }

    return board.get(row).get(col);
  }

  @Override
  public void startGame(int boardSize, Disc firstTurn) throws IllegalArgumentException, IllegalStateException {

    if (gameStarted) {
      throw new IllegalStateException();
    }

    if (boardSize <= 1 || firstTurn == null) {
      throw new IllegalArgumentException();
    }

    board = new ArrayList<>();

    for (int i = 0; i < boardSize * 2 - 1; i++) {
      List<CellImplementation> row = new ArrayList<>();

      int numCol = i < boardSize ? boardSize + i : boardSize * 3 - 2 - i;

      for (int j = 0; j < numCol; j++) {
        row.add(new CellImplementation(i, j));
      }

      board.add(row);
    }

    lastMovePass = false;

    currentTurn = firstTurn;

    this.boardSize = boardSize;

    gameStarted = true;

    CellImplementation middleCell = getCell(boardSize - 1, boardSize - 1);

    getNeighbor(middleCell, Direction.east).setDisc(Disc.black);
    getNeighbor(middleCell, Direction.west).setDisc(Disc.white);

    getNeighbor(middleCell, Direction.southWest).setDisc(Disc.black);
    getNeighbor(middleCell, Direction.southEast).setDisc(Disc.white);

    getNeighbor(middleCell, Direction.northWest).setDisc(Disc.black);
    getNeighbor(middleCell, Direction.northEast).setDisc(Disc.white);

  }

  /**
   * @param cell      cell in the board
   * @param direction direction
   * @return the neighboring cell in the given direction
   */
  CellImplementation getNeighbor(CellImplementation cell, Direction direction) {

    int r = -1, c = -1;

    switch (direction) {

      case east: {
        r = cell.getRow();
        c = cell.getCol() + 1;
        break;
      }

      case west: {
        r = cell.getRow();
        c = cell.getCol() - 1;
        break;
      }

      case northEast: {

        r = cell.getRow() - 1;

        if (cell.getRow() < boardSize) {
          c = cell.getCol();
        } else {
          c = cell.getCol() + 1;
        }

        break;
      }

      case northWest: {

        r = cell.getRow() - 1;

        if (cell.getRow() < boardSize) {
          c = cell.getCol() - 1;
        } else {
          c = cell.getCol();
        }

        break;
      }

      case southWest: {
        r = cell.getRow() + 1;

        if (cell.getRow() < boardSize - 1) {
          c = cell.getCol();
        } else {
          c = cell.getCol() - 1;
        }

        break;
      }

      case southEast: {
        r = cell.getRow() + 1;

        if (cell.getRow() < boardSize - 1) {
          c = cell.getCol() + 1;
        } else {
          c = cell.getCol();
        }

        break;
      }
    }

    return getCell(r, c);
  }

  /**
   * @param cell      cell in the board
   * @param direction direction
   * @return list of neighboring cells of given cell in the given direction
   */
  List<CellImplementation> getLine(CellImplementation cell, Direction direction) {

    List<CellImplementation> res = new ArrayList<>();

    while (true) {

      cell = getNeighbor(cell, direction);
      if (cell == null) {
        break;
      } else {
        res.add(cell);
      }
    }

    return res;
  }

  /**
   * @param line returned by getLine
   * @param disc disc to put
   * @return true if successful, false otherwise
   */
  boolean putDiscLine(List<CellImplementation> line, Disc disc) {

    if (line == null || line.size() < 2) {

      return false;
    }

    int i;
    for (i = 0; i < line.size(); i++) {
      CellImplementation cell = line.get(i);

      if (cell.isEmpty()) {
        return false;
      }

      if (cell.getDisc() == disc) {
        break;
      }
    }

    if (i == line.size()) {
      return false;
    }

    if (i == 0) {

      return false;
    }

    for (int j = 0; j < i; j++) {

      CellImplementation cell = line.get(j);

      cell.setDisc(disc);
    }

    return true;
  }

  @Override
  public void putDisc(Cell cell, Disc disc) throws IllegalStateException, IllegalArgumentException {

    requireGameStarted();

    if (cell == null || disc == null) {
      throw new IllegalArgumentException();
    }

    if (!cell.isEmpty()) {
      throw new IllegalStateException("can't put disc on non-empty cell");
    }

    if (disc != currentTurn) {
      throw new IllegalArgumentException("It is not " + (disc == Disc.black ? "black" : "white") + "'s turn");
    }

    CellImplementation cellImp = (CellImplementation) cell;

    boolean success = false;

    for (Direction direction : Direction.values()) {
      List<CellImplementation> line = getLine(cellImp, direction);
      success = success || putDiscLine(line, disc);
    }

    if (success) {

      cell.setDisc(disc);
      currentTurn = currentTurn.getOppositeDisk();
      lastMovePass = false;

    } else {

      throw new IllegalStateException("Illegal move");
    }
  }

  @Override
  public int getScore(Disc disc) throws IllegalStateException, IllegalArgumentException {

    requireGameStarted();

    if (disc == null) {
      throw new IllegalArgumentException();
    }

    int count = 0;

    for (List<CellImplementation> row : board) {

      for (CellImplementation cell : row) {

        if (cell.getDisc() == disc) {
          count++;
        }
      }
    }

    return count;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    requireGameStarted();
    return gameOver;
  }

  @Override
  public Disc getTurn() throws IllegalStateException {
    requireGameStarted();
    return currentTurn;
  }

  @Override
  public void pass() throws IllegalStateException {
    requireGameStarted();

    if (lastMovePass) {
      gameOver = true;
    } else {
      lastMovePass = true;
    }

    currentTurn = currentTurn.getOppositeDisk();
  }

  @Override
  public boolean isLastMovePass() throws IllegalStateException {
    requireGameStarted();
    return lastMovePass;
  }
}
