package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;
import GUI.NineMenMorrisGUI;

public class PiecePlacementComputerTests extends TestCase {
  private NineMenMorrisBoard board;
  private NineMenMorrisGUI GUI;

  protected void setUp() throws Exception {
    super.setUp();
    board = new NineMenMorrisBoard("Human vs Computer",9,9);
  }
  
  public void testSuccesfulPiecePlacement() {
    boolean status = board.placePiece(0, 0);
    board.changePlayerTurn();
    
    assertTrue(status);
    if (status) {
      int boardstate[][] = board.getBoardState();
      assertEquals(1, boardstate[0][0]);
      assertEquals(2, board.getCurrentPlayer());
      
    }

  }

  public void testOccupiedIntersection() {
    boolean status = board.placePiece(0, 0);
    board.changePlayerTurn();
    status = board.placePiece(0, 0);
    assertFalse(status);
    int boardstate[][] = board.getBoardState();
    assertEquals(1, boardstate[0][0]);
    assertTrue("Values should not be equal", boardstate[0][0] != 2);
    assertEquals(2, board.getCurrentPlayer());

  }

  public void testInvalidlocationOutsideBoard() {
    boolean status = board.placePiece(0, 7);
    assertFalse(status);
    assertEquals(1, board.getCurrentPlayer());


  }
  
  public void testInvalidIntersection() {
    boolean status = board.placePiece(0, 1);
    int boardstate[][] = board.getBoardState();
    assertFalse(status);
    assertEquals(0, boardstate[0][0]);
    assertEquals(1, board.getCurrentPlayer());

  }
}


