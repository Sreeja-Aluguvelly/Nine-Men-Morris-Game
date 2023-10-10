package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;




public class PiecePlacementTests extends TestCase{
	private NineMenMorrisBoard board;

	protected void setUp() throws Exception {
		super.setUp();
		 board = new NineMenMorrisBoard(); // I
	
	}
	
	public void testSuccesfulPiecePlacement() {
    	board.placePiece(0, 0);
    	board.placePiece(6, 6);
        int boardstate[][]=board.getBoardState();
   		assertEquals(2, boardstate[6][6]); 
		
	}
	
	
	

	
}



