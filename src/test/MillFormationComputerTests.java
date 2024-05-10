package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;

public class MillFormationComputerTests extends TestCase {
  private NineMenMorrisBoard board;

  protected void setUp() throws Exception {
    super.setUp();
    board = new NineMenMorrisBoard("Human vs Computer",9,9);
    boolean status = board.placePiece(0, 0);
    board.changePlayerTurn();
    
    status = board.placePiece(3, 0);
    board.changePlayerTurn();

    status = board.placePiece(0, 3);
    board.changePlayerTurn();

    status = board.placePiece(3, 1);
    board.changePlayerTurn();


  }

  public void testSuccesfulMillFormation() {
    boolean status = board.placePiece(0, 6);
    
    boolean millformed =board.millformed( board.getBoardState(),0,6,board.getCurrentPlayer());   
    assertTrue(millformed);
    if (millformed) {
      assertEquals(1, board.getCurrentPlayer());

    }

  }
 
  public void testUnSuccesfulMillFormation() {
    boolean status = board.placePiece(4,3);
    board.changePlayerTurn();
    boolean millformed =board.millformed( board.getBoardState(),4,3,board.getCurrentPlayer());   
    assertFalse(millformed);
    assertEquals(2, board.getCurrentPlayer());

  }
  
}


