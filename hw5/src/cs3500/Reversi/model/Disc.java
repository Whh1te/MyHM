package cs3500.Reversi.model;

/**
 * Disc num, white or black
 */
public enum Disc {
  white,
  black;

  /**
   * @return disc with opposite color
   */
  Disc getOppositeDisk() {

    if (this == white) {

      return black;
    } else {

      return white;
    }
  }

  @Override
  public String toString() {
    if (this == white) {
      return "O";
    } else {
      return "X";
    }
  }
}
