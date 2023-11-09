package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;



public class PieceMovementTests extends TestCase {
  private NineMenMorrisBoard board;

  protected void setUp() throws Exception {
    super.setUp();
    board = new NineMenMorrisBoard(); // I

  }
// test for , successful piece placement 
  public void testSuccesfulPiecePlacement() {
    boolean status = board.placePiece(0, 0);
    assertTrue(status);
    if (status) {
      int boardstate[][] = board.getBoardState();
      assertEquals(1, boardstate[0][0]);
      assertEquals(2, board.getCurrentPlayer());
      

    }


  }

  //test for, if player selects occupied intersection then piece is not placed.
  public void testOccupiedIntersection() {
    boolean status = board.placePiece(0, 0);
    status = board.placePiece(0, 0);
    assertFalse(status);
    int boardstate[][] = board.getBoardState();
    assertEquals(1, boardstate[0][0]);
    assertTrue("Values should not be equal", boardstate[0][0] != 2);
    assertEquals(2, board.getCurrentPlayer());

  }

  //test for, if player selects invalid location then piece is not placed.
  public void testInvalidlocationOutsideBoard() {
    boolean status = board.placePiece(0, 7);
    assertFalse(status);
    assertEquals(1, board.getCurrentPlayer());


  }
  
  //test for, if player selects invalids intersection then piece is not placed
  public void testInvalidIntersection() {
    boolean status = board.placePiece(0, 1);
    int boardstate[][] = board.getBoardState();
    assertFalse(status);
    assertEquals(0, boardstate[0][0]);
    assertEquals(1, board.getCurrentPlayer());




  }
}


