
package GUI;
import java.awt.*;
import javax.swing.*;


public class NineMenMorrisBoard {
    private int[][] boardState; // Represents the state of the game board
    private int currentPlayer;  // 1 for player 1 (black), 2 for player 2 (white)
    private int blackPieces;    // Number of remaining black pieces
    private int whitePieces;    // Number of remaining white pieces

    public NineMenMorrisBoard() {
        // Initialize the game board
        boardState = new int[7][7]; // 7x7 grid for the Nine Men's Morris board
        currentPlayer = 1;         // Start with player 1 (black)
        blackPieces = 9;           // Each player starts with 9 pieces
        whitePieces = 9;
    }
    
    
    public void placePiece(int row,int col) {
        if (isValidMove(row, col)) {
             getBoardState()[row][col] = getCurrentPlayer();
             
            // Decrease the count of remaining pieces for the current player
            if (getCurrentPlayer() == 1) {
               setBlackPieces(getBlackPieces() - 1);
               // player1PiecesLabel.setText("No. of pieces left"+board.getBlackPieces());
                
            } else {
               setWhitePieces(getWhitePieces() - 1);
               // player2PiecesLabel.setText("No. of pieces left"+board.getWhitePieces());

            }
            // Toggle players
            setCurrentPlayer((getCurrentPlayer() == 1) ? 2 : 1);

   
        }
    }

    public boolean isValidMove(int row, int col) {
        // Implement your logic to check if the move is valid
        // You need to consider the game rules for Nine Men's Morris here
        // Return true if the move is valid, otherwise return false
        // You also need to check if a player has won the game
    	
    	if ((isCorner(row, col) || isEdge(row, col) || isCenter(row, col)) && (boardState[row][col] == 0) && (blackPieces > 0 || whitePieces > 0)) {
    	        // Implement other game rules here

    	        return true; // Valid move
    	    }
        return false;
    }
    
    
    public boolean isValidIntersecction(int row, int col) {
        // Implement your logic to check if the move is valid
        // You need to consider the game rules for Nine Men's Morris here
        // Return true if the move is valid, otherwise return false
        // You also need to check if a player has won the game
    	
    	if ((isCorner(row, col) || isEdge(row, col) || isCenter(row, col)) ) {
    	        // Implement other game rules here
 
    	        return true; // Valid move
    	    }
        return false;
    }
    
    
    

    // Getter method for current player
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(int player) {
        currentPlayer = player;
    }

    // Getter method for game state (board state)
    public int[][] getBoardState() {
        return boardState;
    }

    // Getter method for remaining black pieces
    public int getBlackPieces() {
        return blackPieces;
    }

    // Setter method for remaining black pieces
    public void setBlackPieces(int blackPieces) {
        this.blackPieces = blackPieces;
    }

    // Getter method for remaining white pieces
    public int getWhitePieces() {
        return whitePieces;
    }

    // Setter method for remaining white pieces
    public void setWhitePieces(int whitePieces) {
        this.whitePieces = whitePieces;
    }
 
    
    
    public boolean isCorner(int row, int col) {
        // Check if the intersection is a corner based on the number of rows and columns
        // Corners are at the first and last row and column
        //return( (row == 0 && col == 0) || (row == 0 && col == 7 - 1) || (row == 7 - 1 && col == 0) || (row == 7 - 1 && col == 7 - 1));
        return (row == 0 && col == 0) || (row == 0 && col == 6) || (row == 6 && col == 0) || (row == 6 && col == 6);

    }

    public boolean isEdge(int row, int col) {
        // Check if the intersection is an edge based on the number of rows and columns
        // Edges are not corners and are at the first and last row or column
    	
    	return (row == 1 && col == 1) || (row == 1 && col == 3) || (row == 1 && col == 5) ||
    	           (row == 3 && col == 6) || (row == 5 && col == 5) || (row == 5 && col == 3) ||
    	           (row == 5 && col == 1) || (row == 3 && col == 0)
    	           || (row == 2 && col == 2) || (row == 2 && col == 4) ||
    	           (row == 4 && col == 2) || (row == 4 && col == 4);
    	
    //  return (!isCorner(row, col) &&
      //  		!isCenter(row, col)  &&
        //		 ((row == 0 || row == numRows - 1) && (col % 3 == 0 || col == 1 || col == 5)) ||
          //       ((col == 0 || col == numCols - 1) && (row % 3 == 0 || row == 1 || row == 5)));
    }

    public boolean isCenter(int row, int col) {
        // Check if the intersection is the center based on the number of rows and columns
        // Center intersection is at the middle row and middle column
        //return ((row == 7 / 2) && (col == 7 / 2));
    	if((row == 3 && col == 3 )) {
    		return false;
    	}
 
    	return row==3 || col == 3;
    }
    
    // Other game logic methods can be added here
}
