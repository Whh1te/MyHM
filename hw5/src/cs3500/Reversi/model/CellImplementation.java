package cs3500.Reversi.model;

public class CellImplementation implements Cell {

  private Disc disc;

  private int row;

  private int col;

  public CellImplementation(int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public Disc getDisc() {
    return disc;
  }

  @Override
  public boolean isEmpty() {
    return disc == null;
  }

  @Override
  public void setDisc(Disc disc) {
    this.disc = disc;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  @Override
  public String toString() {

    if (isEmpty()) {

      return "_";
    } else {

      return disc.toString();
    }
  }
}
