package cs3500.Reversi.view;

import cs3500.Reversi.model.Cell;
import cs3500.Reversi.model.ReversiModel;

import java.util.List;


/**
 * A simple text-based rendering of the Reversi game.
 * <p>
 * Usage Example:
 * <p>
 * ReversiModel model = new ReversiModelImplementation();
 * model.startGame(6, Disc.white);
 * ReversiTextView reversiTextView = new ReversiTextView(model);
 * System.out.println(reversiTextView);
 */
public class ReversiTextView implements TextView {

  private ReversiModel reversiModel;

  /**
   * @param reversiModel reversi model
   */
  public ReversiTextView(ReversiModel reversiModel) {
    this.reversiModel = reversiModel;
  }

  @Override
  public String toString() {

    int boardSize = reversiModel.getBoardSize();

    List<List<Cell>> board = reversiModel.getBorad();

    StringBuilder s = new StringBuilder();

    for (int i = 0; i < board.size(); i++) {

      List<Cell> row = board.get(i);

      StringBuilder r = new StringBuilder();

      int numSpace = 0;
      if (i < boardSize) {
        numSpace = boardSize - i - 2;
      } else {
        numSpace = i - boardSize;
      }

      for (int k = 0; k < numSpace; k++) {
        r.append(" ");
      }

      for (Cell cell : row) {

        r.append(" ").append(cell);
      }

      if (i == boardSize - 1) {
        r = new StringBuilder(r.substring(1));
      }

      s.append(r).append("\n");
    }

    return s.toString();
  }
}
