package cs3500.Reversi.model;

import java.util.List;

/**
 * Represents the primary model interface for playing a game of Reversi.
 */
public interface ReversiModel {


  /**
   * @param boardSize number of cells for each edge of the hexagon board
   * @param firstTurn specify which disc (white or black) plays first
   * @throws IllegalStateException    if the game has already started
   * @throws IllegalArgumentException if the arguments are invalid
   */
  void startGame(int boardSize, Disc firstTurn) throws IllegalArgumentException, IllegalStateException;

  /**
   * @return the board of game.
   * @throws IllegalStateException if the game hasn't been started yet
   */
  List<List<Cell>> getBorad() throws IllegalStateException;


  /**
   * @param row index of the row in the board
   * @param col index of the cell in the row
   * @return cell for the given position or null if position is out of range
   * @throws IllegalStateException if the game hasn't been started yet
   */
  Cell getCell(int row, int col) throws IllegalStateException;


  /**
   * @return board size
   * @throws IllegalStateException if the game hasn't been started yet
   */
  int getBoardSize() throws IllegalStateException;


  /**
   * @param cell cell to put the disc
   * @param disc white or black disc
   * @throws IllegalStateException    if the game hasn't been started yet or the move is illegal
   * @throws IllegalArgumentException if cell or disc is null or it is not disc's turn
   */
  void putDisc(Cell cell, Disc disc) throws IllegalStateException, IllegalArgumentException;

  /**
   * The score for black disc is the number of black discs on the board.
   * The score for white disc is the number of white discs on the board.
   *
   * @param disc white or black disc
   * @return the score for the player with given disc
   * @throws IllegalStateException    if the game hasn't been started
   * @throws IllegalArgumentException if disc is null
   */
  int getScore(Disc disc) throws IllegalStateException, IllegalArgumentException;

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise
   * @throws IllegalStateException if the game hasn't been started yet
   */
  boolean isGameOver() throws IllegalStateException;

  /**
   * @return the disc of the next turn
   * @throws IllegalStateException if the game hasn't been started yet
   */
  Disc getTurn() throws IllegalStateException;

  /**
   * pass the turn
   *
   * @throws IllegalStateException if the game hasn't been started yet
   */
  void pass() throws IllegalStateException;

  /**
   * Signal if the most recent move is pass
   *
   * @return true if the last move is pass, otherwise false
   * @throws IllegalStateException if the game hasn't been started yet
   */
  boolean isLastMovePass();

}
