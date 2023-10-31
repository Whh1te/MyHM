package cs3500.Reversi;

import cs3500.Reversi.model.Disc;
import cs3500.Reversi.model.ReversiModel;
import cs3500.Reversi.model.ReversiModelImplementation;
import cs3500.Reversi.view.ReversiTextView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test text view
 */
public class ReversiTextViewTest {

  ReversiModel model;
  ReversiTextView textView;

  @Before
  public void setUp() throws Exception {
    model = new ReversiModelImplementation();
    model.startGame(6, Disc.white);
    textView = new ReversiTextView(model);
  }

  @Test
  public void testToString() {

    String expected = "     _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "_ _ _ _ O _ X _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _\n";

    assertEquals(expected, textView.toString());

    model.putDisc(model.getCell(4, 3), Disc.white);

    expected = "     _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            " _ _ _ O O O _ _ _ _\n" +
            "_ _ _ _ O _ X _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _\n";

    assertEquals(expected, textView.toString());
  }
}