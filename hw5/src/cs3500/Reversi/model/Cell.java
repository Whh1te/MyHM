package cs3500.Reversi.model;

/**
 * cell on the game board
 */
public interface Cell {
  /**
   * @return disc in the cell
   */
  Disc getDisc();

  /**
   * @return true if the cell is empty
   */
  boolean isEmpty();

  /**
   * put disc in the cell
   *
   * @param disc disc to put
   */
  void setDisc(Disc disc);
}
