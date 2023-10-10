package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;




public class EmptyBoardTests extends TestCase{
	private NineMenMorrisBoard board;

	protected void setUp() throws Exception {
		super.setUp();
		 board = new NineMenMorrisBoard(); // I
	
	}

	public void testSuccesfulBoardCreation() {
   
        int boardstate[][]=board.getBoardState();
    	System.out.print(board.getCurrentPlayer());
		for (int row = 0; row<7; row++) {
			for (int column = 0; column<7; column++) {
				System.out.print(boardstate[row][column]);
				assertEquals(0, boardstate[row][column]); 
			}
		}
		assertEquals(1, board.getCurrentPlayer()); 

		
	}
		

	
}



