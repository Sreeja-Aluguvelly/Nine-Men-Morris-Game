package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;

public class RemovingOpponentsPiecesComputerTests extends TestCase {
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
 
  public void testSuccesfulPieceRemoval() {
    boolean status = board.placePiece(0, 6);
    int count=0;
    boolean millformed =board.millformed( board.getBoardState(),0,6,board.getCurrentPlayer());   
    if(millformed) { 
      boolean succ = board.removePiece(board.getBoardState(),  3,  1);
    }
    
    for (int[] row : board.getBoardState()) {
      for (int element : row) {
          if (element == 2) {
              count++;
          }
      }
  }
    assertEquals( 1, count);
  }

  public void testUnSuccesfulPieceRemoval() {
    boolean status = board.placePiece(6, 6);
    int count=0;
    boolean millformed =board.millformed( board.getBoardState(),0,6,board.getCurrentPlayer());   
    if(!millformed) {
      
      for (int[] row : board.getBoardState()) {
        for (int element : row) {
            if (element == 2) {
                count++;
            }
        }
    } 
      assertEquals( 2, count);

    }
    
  }

}


