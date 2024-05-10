package test;

import junit.framework.TestCase;
import java.awt.Font;
import javax.swing.JButton;
import GUI.NineMenMorrisBoard;



public class RecordTests extends TestCase {
  private NineMenMorrisBoard board;

  protected void setUp() throws Exception {
    super.setUp();

    board = new NineMenMorrisBoard("Human vs Human",9,9);
 
  }

  public void testSuccesfulRecord() {

    boolean status = board.placePiece(0, 0);
    board.recordMove(NineMenMorrisBoard.MoveType.PLACING, -1, -1, 0, 0);

    assertEquals(1, board.getRecordedMoves().size());

      
    

  }




}


