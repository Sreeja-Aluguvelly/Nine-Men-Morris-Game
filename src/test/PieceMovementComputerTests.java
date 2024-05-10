package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;

public class PieceMovementComputerTests extends TestCase {
  private NineMenMorrisBoard board;

  protected void setUp() throws Exception {
    super.setUp();
    board = new NineMenMorrisBoard("Human vs Computer",9,9);
    for(int row=0;row<7;row++) {
      
      for(int col=0;col<7;col++) {
        
        
        if (board.getBlackPieces() > 0 || board.getWhitePieces() > 0) {

        if(board.isValidIntersecction(row,col)) {

           boolean successstatus = board.placePiece(row, col);
           board.changePlayerTurn();

          }  
          
        }
          
      }
      
    }

  }
  
  public void testSuccesfulPieceMovement() {

   int[][] boardstate= board.getBoardState();
    
    boolean successstatus = board.movePiece(3, 6, 6, 6);
    board.changePlayerTurn();

    assertTrue(successstatus);
    if (successstatus) {
      assertEquals(1, boardstate[6][6]);
      assertEquals(2, board.getCurrentPlayer());
      
    }

  }

  public void testUnsucessfulIfNoOfPiecesLeftforPlacement() {
    
    board = new NineMenMorrisBoard("Human vs Computer",9,9);
    boolean successstatus = board.placePiece(0, 0);
    board.changePlayerTurn();
     successstatus = board.placePiece(0,6);
    board.changePlayerTurn();
    
    successstatus = board.movePiece(0,0, 0, 3);
    assertFalse(successstatus);
    int boardstate[][] = board.getBoardState();
    assertEquals(1, boardstate[0][0]);
    assertEquals(1, board.getCurrentPlayer());

  }

  public void testUnsucessfulIfOccupiedPositionMovement() {
    
   int[][] boardstate= board.getBoardState();
   boolean successstatus =false;
   int[][] intersections=board.findAdjacentValidIntersections(3, 6);
   successstatus = board.movePiece(3, 6, 0, 6);
   assertFalse(successstatus);
    if (successstatus) {
      assertEquals(1, boardstate[0][6]);
      assertEquals(1, board.getCurrentPlayer());

    }
  }
}
