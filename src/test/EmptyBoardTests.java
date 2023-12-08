package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;



public class EmptyBoardTests extends TestCase {
  private NineMenMorrisBoard board;

  protected void setUp() throws Exception {
    super.setUp();
    board = new NineMenMorrisBoard();

  }

  public void testSuccesfulBoardCreation() {

    int boardstate[][] = board.getBoardState();
    for (int row = 0; row < 7; row++) {
      for (int column = 0; column < 7; column++) {
        assertEquals(0, boardstate[row][column]);
        
        
      }
    }
    assertEquals(1, board.getCurrentPlayer());


  }

  public void testInvalidRowIndexing() {

    try {
      int boardstate[][] = board.getBoardState();
      int value = boardstate[8][2];
      System.out.println(value);
    } catch (Exception e) {
      return;
    }
    fail("Expected Exception, but no exception was thrown.");

  }

  public void testInvalidColumnIndexing() {

    try {
      int boardstate[][] = board.getBoardState();
      int value = boardstate[5][9];
      System.out.println(value);
    } catch (Exception e) {
      return;
    }
    fail("Expected Exception, but no exception was thrown.");

  }



}


