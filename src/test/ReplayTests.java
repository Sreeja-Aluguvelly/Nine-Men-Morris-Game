package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;



public class ReplayTests extends TestCase {
  private NineMenMorrisBoard board;

  protected void setUp() throws Exception {
    super.setUp();

    board = new NineMenMorrisBoard("Human vs Human",9,9);
 
  }

  public void testSuccesfulReplay() {

    boolean status = board.placePiece(0, 0);
    board.recordMove(NineMenMorrisBoard.MoveType.PLACING, -1, -1, 0, 0);

     status = board.placePiece(0, 0);
    board.recordMove(NineMenMorrisBoard.MoveType.PLACING, -1, -1, 6,6);

    assertEquals(2, board.getRecordedMoves().size());
    board.nextClickOnReplay(NineMenMorrisBoard.recordedMoves);
    
    assertEquals(1, board.getRecordedMoves().size());


  }





}


