package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;



public class NoOfPiecesLeftTests extends TestCase {
  private NineMenMorrisBoard board;

  protected void setUp() throws Exception {
    super.setUp();
    board = new NineMenMorrisBoard(); // Initialize board

  }

  public void testSuccessfulUpdateOfNoOfPieces() {
    int initialWhitePieces = board.getWhitePieces();
    boolean status = board.placePiece(0, 0);
    board.changePlayerTurn();
    if (status) {
      assertEquals(initialWhitePieces - 1, board.getWhitePieces());
    }
    int initialBlackPieces = board.getBlackPieces();
    status = board.placePiece(0, 0);
    if (status) {
      assertEquals(initialBlackPieces - 1, board.getBlackPieces());
    }
    
  }


  public void testUnscuccesfulUpdateOfNoOfPieces() {
    int initialWhitePieces = board.getWhitePieces();
    boolean status = board.placePiece(0, 0);
    board.changePlayerTurn();
    if (status) {
      assertFalse(initialWhitePieces - 1 != board.getWhitePieces());
    }
  }


}


