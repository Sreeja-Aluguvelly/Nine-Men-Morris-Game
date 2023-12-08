package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;



public class FlyingPieceTests extends TestCase {
  private NineMenMorrisBoard board;

  protected void setUp() throws Exception {
    super.setUp();
    board = new NineMenMorrisBoard(); // I
    boolean status = board.placePiece(2, 2);
    board.changePlayerTurn();

    status = board.placePiece(1, 3);
    board.changePlayerTurn();

    status = board.placePiece(4, 2);
    board.changePlayerTurn();

    status = board.placePiece(3, 4);
    board.changePlayerTurn();

    status = board.placePiece(4, 3);
    board.changePlayerTurn();
      
    status = board.placePiece(1,5);
    board.changePlayerTurn();
    
    status = board.placePiece(2,4);
    board.changePlayerTurn();
    
    status = board.placePiece(1,1);
    board.removePiece(board.getBoardState(),2,4);
    board.changePlayerTurn();
    
    status = board.placePiece(2,4);
    board.changePlayerTurn();
    
    status = board.placePiece(3,6);
    board.changePlayerTurn();
    
    status = board.placePiece(6,3);
    board.changePlayerTurn();
    
    status = board.placePiece(5,5);
    board.changePlayerTurn();
    
    status = board.placePiece(6,6);
    board.changePlayerTurn();
    
    status = board.placePiece(5,1);
    board.changePlayerTurn();
    
    status = board.placePiece(0,0);
    board.changePlayerTurn();
    
    status = board.placePiece(3,1);
    board.removePiece(board.getBoardState(),4,3);
    board.changePlayerTurn();
    
    status = board.placePiece(4,3);
    board.changePlayerTurn();
    
    status = board.placePiece(3,5);
    board.removePiece(board.getBoardState(),4,3);
    board.changePlayerTurn();
    
    board.movePiece(4, 2, 4, 3);
    board.changePlayerTurn();
    
    board.movePiece(5, 5, 3, 5);
    board.removePiece(board.getBoardState(),6,6);
    board.changePlayerTurn();
    
    board.movePiece(4, 3, 4, 4);
    board.changePlayerTurn();
    
    board.movePiece(3, 5, 5, 5);
    board.removePiece(board.getBoardState(),6,3);
    board.changePlayerTurn();
    
    board.movePiece(4, 4, 4, 3);
    board.changePlayerTurn();
    
    board.movePiece(5, 5, 3, 5);
    board.removePiece(board.getBoardState(),0,0);
    board.changePlayerTurn();
    
    
 }

  public void testSuccessfulFlyingPiece() {

    int[][] boardstate= board.getBoardState();
     boolean successstatus = board.movePiece(4, 3, 4, 2);
     board.changePlayerTurn();

     assertTrue(successstatus);
     if (successstatus) {
       assertEquals(1, boardstate[4][2]);
       assertEquals(2, board.getCurrentPlayer()); 
     }
   }
   public void testUnsucessfulFlyingPiece() {
     
     boolean successstatus = board.movePiece(4, 3, 3, 4);
     
     System.out.println(successstatus);
     assertFalse(successstatus);
     int boardstate[][] = board.getBoardState();
       assertEquals(2, boardstate[1][3]);
       assertEquals(1, board.getCurrentPlayer());
   } 
   }
  



