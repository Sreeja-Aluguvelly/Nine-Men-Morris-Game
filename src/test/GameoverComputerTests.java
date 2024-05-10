package test;

import junit.framework.TestCase;
import GUI.NineMenMorrisBoard;



public class GameoverComputerTests extends TestCase {
  private NineMenMorrisBoard board;

  protected void setUp() throws Exception {
    super.setUp();
    board = new NineMenMorrisBoard("Human vs Computer",9,9);
    

  
  }
  
  
  
  public void winByLessThan3Pieces() {
    

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
    

    board.movePiece(4, 3, 4, 2);
    board.changePlayerTurn();
    
    board.movePiece(3, 5, 5, 5);
    board.removePiece(board.getBoardState(),2,2);
    
   
    
    
    
    
    assertTrue(status);
    int boardstate[][] = board.getBoardState();
    
    
    boolean gameover=board.gameover(board.getBoardState(), board.getCurrentPlayer());
    assertTrue(gameover);
    
    if(gameover) {
      
      assertEquals(2, board.getCurrentPlayer());

      
      
    }
    
    
    
    
    
    
    
    
    
    
    
    
    


  }
  

  public void winByOpponentBlock() {
 
    
    boolean status = board.placePiece(2, 2);
    board.changePlayerTurn();
    
    status = board.placePiece(3, 1);
    board.changePlayerTurn();

    status = board.placePiece(2, 3);
    board.changePlayerTurn();

    status = board.placePiece(5, 3);
    board.changePlayerTurn();

    status = board.placePiece(3,2);
    board.changePlayerTurn();

    status = board.placePiece(3,5);
    board.changePlayerTurn();

    status = board.placePiece(4,3);
    board.changePlayerTurn();

    status = board.placePiece(1,3);
    board.changePlayerTurn();

    status = board.placePiece(4,4);
    board.changePlayerTurn();

    status = board.placePiece(4,2);
    board.changePlayerTurn();

    status = board.placePiece(3,4);
    board.changePlayerTurn();

    status = board.placePiece(2,4);
    board.changePlayerTurn();
   
    status = board.placePiece(5,1);
    board.changePlayerTurn();
    
    status = board.placePiece(6,0);
    board.changePlayerTurn();
    
    status = board.placePiece(1,1);
    board.changePlayerTurn();
    
    status = board.placePiece(6,3);
    board.changePlayerTurn();
    
    status = board.placePiece(5,5);
    board.changePlayerTurn();
    
    
    status = board.placePiece(0,0);

    assertTrue(status);
    int boardstate[][] = board.getBoardState();
    
    
    boolean gameover=board.gameover(board.getBoardState(), board.getCurrentPlayer());
    assertTrue(gameover);
    
    if(gameover) {
      
      assertEquals(1, board.getCurrentPlayer());
   }
       
  }

  public void testGameNotOver() {
    
    boolean gameover=board.gameover(board.getBoardState(), board.getCurrentPlayer());
    assertFalse(gameover);
    
  }
}


