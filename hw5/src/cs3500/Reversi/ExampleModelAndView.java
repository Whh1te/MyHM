package cs3500.Reversi;

import cs3500.Reversi.model.Disc;
import cs3500.Reversi.model.ReversiModel;
import cs3500.Reversi.model.ReversiModelImplementation;
import cs3500.Reversi.view.ReversiTextView;

/**
 * Show example usage of model and view.
 */
public class ExampleModelAndView {
  public static void main(String[] args) {

    ReversiModel model = new ReversiModelImplementation();
    model.startGame(6, Disc.white);
    ReversiTextView reversiTextView = new ReversiTextView(model);

    System.out.println("initial board:");
    System.out.println(reversiTextView);

    System.out.println("put white disc at (4, 3)");
    model.putDisc(model.getCell(4, 3), Disc.white);

    System.out.println(reversiTextView);

    System.out.println("put black disc at (3, 4)");
    model.putDisc(model.getCell(3, 4), Disc.black);

    System.out.println(reversiTextView);

    System.out.println("put white disc at (6, 3)");
    model.putDisc(model.getCell(6, 3), Disc.white);

    System.out.println(reversiTextView);

    System.out.println("put black disc at (4, 2)");
    model.putDisc(model.getCell(4, 2), Disc.black);

    System.out.println(reversiTextView);

    System.out.println("put white disc at (4, 6)");
    model.putDisc(model.getCell(4, 6), Disc.white);

    System.out.println(reversiTextView);

    System.out.println("black pass");
    model.pass();

    System.out.println(reversiTextView);

    System.out.println("put white disc at (4, 1)");
    model.putDisc(model.getCell(4, 1), Disc.white);

    System.out.println(reversiTextView);

    System.out.println("black pass");
    model.pass();

    System.out.println(reversiTextView);

    System.out.println("put white disc at (2, 3)");
    model.putDisc(model.getCell(2, 3), Disc.white);

    System.out.println(reversiTextView);

    System.out.println("black pass");
    model.pass();

    System.out.println("white pass");
    model.pass();

    System.out.println("game over: " + model.isGameOver());

    System.out.println("white score: " + model.getScore(Disc.white));
    System.out.println("black score " + model.getScore(Disc.black));

  }
}
