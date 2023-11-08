
package GUI;

import java.util.ArrayList;
import java.util.List;

public class NineMenMorrisBoard {
  private int[][] boardState;
  private int currentPlayer;
  private int blackPieces;
  private int whitePieces;
  private int[][] highlightedIntersections;



  public NineMenMorrisBoard() {
    boardState = new int[7][7];
    currentPlayer = 1;
    blackPieces = 9;
    whitePieces = 9;

  }



  // placePiece function takes the selected position and
  // places the corresponding piece in the given location
  // return true if successful and false if not
  public boolean placePiece(int row, int col) {
    if (isValidMove(row, col)) {
      getBoardState()[row][col] = getCurrentPlayer();

      if (getCurrentPlayer() == 1) {
        setWhitePieces(getWhitePieces() - 1);

      } else {
        setBlackPieces(getBlackPieces() - 1);

      }

      return true;
    }
    return false;
  }


  public boolean isValidMove(int row, int col) {

    if (isValidIntersecction(row, col) && (boardState[row][col] == 0)
        && (blackPieces > 0 || whitePieces > 0)) {
      return true;
    }
    return false;
  }


  public boolean isValidIntersecction(int row, int col) {
    if ((isCorner(row, col) || isEdge(row, col) || isCenter(row, col))) {

      return true;
    }
    return false;
  }



  public int getCurrentPlayer() {
    return currentPlayer;
  }

  public void changePlayerTurn() {
    setCurrentPlayer((getCurrentPlayer() == 1) ? 2 : 1);

  
  }
  public void setCurrentPlayer(int player) {
    currentPlayer = player;
  }

  public int[][] getBoardState() {
    return boardState;
  }

  public int getBlackPieces() {
    return blackPieces;
  }

  public void setBlackPieces(int blackPieces) {
    this.blackPieces = blackPieces;
  }

  public int getWhitePieces() {
    return whitePieces;
  }

  public void setWhitePieces(int whitePieces) {
    this.whitePieces = whitePieces;
  }



  public boolean isCorner(int row, int col) {
    return (row == 0 && col == 0) || (row == 0 && col == 6) || (row == 6 && col == 0)
        || (row == 6 && col == 6);

  }

  public boolean isEdge(int row, int col) {
    return (row == 1 && col == 1) || (row == 1 && col == 5) || (row == 5 && col == 5)
        || (row == 5 && col == 1) || (row == 2 && col == 2) || (row == 2 && col == 4)
        || (row == 4 && col == 2) || (row == 4 && col == 4);

  }

  public boolean isCenter(int row, int col) {
    if ((row == 3 && col == 3)) {
      return false;
    }

    return row == 3 || col == 3;
  }
  
  
  

  
  public void setHighlightedIntersections(int[][] intersections) {
    this.highlightedIntersections = intersections;
}
  
  
  
  public int[][] getHighlightedIntersections() {
    return this.highlightedIntersections;
}
  
  
 public boolean gameover(int[][] boardstate, int currentplayer) {
   
   
   int opponent = (currentPlayer == 1) ? 2 : 1;
   System.out.println("Priting less than 3 pieces func");
  System.out.println(hasFewerThanThreePieces(boardState, opponent));
  
  
  
  System.out.println("Priting has legal moves func");
 System.out.println(hasLegalMoves(boardState, opponent));
 
 
 
 
 if(!(getWhitePieces()  >0 && getBlackPieces() >0)) {
   
   
   if (hasFewerThanThreePieces(boardState, opponent)) {
     return true; 
 }

 if (!hasLegalMoves(boardState, opponent)) {
     return true; 
 }

   
 }
  

   return false; 
 }
 
 public boolean hasFewerThanThreePieces(int[][] boardState, int player) {
   int count = 0;
   for (int row = 0; row < boardState.length; row++) {
       for (int col = 0; col < boardState[row].length; col++) {
           if (boardState[row][col] == player) {
               count++;
           }
       }
   }
   return count < 3;
}
 
 
 
 public boolean hasLegalMoves(int[][] boardState, int player) {
   int[][] intersections=new int[0][0];
   boolean value=false;
   System.out.println(intersections);
    for (int row = 0; row < boardState.length; row++) {
        for (int col = 0; col < boardState[row].length; col++) {
            if (boardState[row][col] == player) {
             intersections=findAdjacentValidIntersections(row, col);
             if(intersections.length!=0)
             {
             //System.out.println("Intersections has legal moves");
             value=true;
             break;
             }
             
                // Check if this piece can be moved to an adjacent empty spot.
                // You'll need to implement this logic based on the rules.
                // This will depend on the current game phase (Placing, Moving, Flying).
                // You should check if a move is legal for the given player and their piece's position.
                // If a legal move is found, return true.
            }
        }
    }
    return value;
 }
 
 
 
 public boolean removePiece(int[][] boardState, int row, int col) {
   if (boardState[row][col] != 0 && boardState[row][col] != currentPlayer) {
       boardState[row][col] = 0;
       return true;
   }
   return false;
}
 public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
// if (isValidMove(fromRow, fromCol) && isValidMove(toRow, toCol)) {
       int[][] boardState = getBoardState();
       int currentPlayer = getCurrentPlayer();
       System.out.println("player tuen is "+currentPlayer);
       if (currentPlayer == 2 && getBlackPieces() == 0) {
//         if (Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol) == 1) {
               boardState[toRow][toCol] = 2;
               boardState[fromRow][fromCol] = 0;
               return true;
//         }
       } else if (currentPlayer == 1 && getWhitePieces() == 0) {
//         if (Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol) == 1) {
               boardState[toRow][toCol] = 1;
               boardState[fromRow][fromCol] = 0;
               return true;
//         }
       }
       return false;
 }

 
 public boolean millformed(int[][] board, int row,int col, int player) {
   
   
   if(hasHorizontalMill(board,row,col,player)) {
     return true;
     
   }
   
  if(hasVerticalMill(board,row,col,player)) {
    return true;
     
   }
   
   
   
   return false;
}
  

 public  boolean hasHorizontalMill(int[][] board, int row, int col, int player) {
   if (row % 2 == 0) {
       // Check horizontal mills in valid locations (even rows).
       //System.out.println("Entered");
       return (board[row][0] == player && board[row][3] == player && board[row][6] == player)
           || (board[row][2] == player && board[row][3] == player && board[row][4] == player);
   } 
   else if(row==3 && (col == 0 ||col == 1 || col == 2))
   {
    return (board[row][0] == player && board[row][1] == player && board[row][2] == player);
   }
   else if(row==3 && (col == 4 ||col == 5 || col == 6))
   {
    return (board[row][4] == player && board[row][5] == player && board[row][6] == player);
   }
   else {
       // Check horizontal mills in valid locations (odd rows).
       return (board[row][1] == player && board[row][3] == player && board[row][5] == player);
   }
}

 public  boolean hasVerticalMill(int[][] board, int row, int col, int player) {
   if (col % 2 == 0) {
       // Check vertical mills in valid locations (even columns).

       return (board[0][col] == player && board[3][col] == player && board[6][col] == player)
          
           || (board[2][col] == player && board[3][col] == player && board[4][col] == player);
   }
   else if(col==3 && (row == 0 ||row == 1 || row == 2))
   {
    return (board[0][col] == player && board[1][col] == player && board[2][col] == player);
   }
   else if(col==3 && (row == 4 ||row == 5 || row == 6))
   {
    return(board[6][col] == player && board[4][col] == player && board[5][col] == player);
   }
   else {
       // Check vertical mills in valid locations (odd columns).
       return (board[1][col] == player && board[3][col] == player && board[5][col] == player);
}
}

   
   
public int[][] getplayerpieces(int[][] boardState, int player){
     
     
     
     List<int[]> playerPieces = new ArrayList<>();
    List<int[]> playerPieces1 = new ArrayList<>();
     for (int row = 0; row < 7; row++) {
         for (int col = 0; col < 7; col++) {
             if (boardState[row][col] == player) {
                 playerPieces.add(new int[]{row, col});
             }
         }
     }

     for (int[] intersection : playerPieces) {
            System.out.println("(" + intersection[0] + ", " + intersection[1] + ")");
            if(!(millformed(getBoardState(),intersection[0] ,intersection[1], player)))
            {
              System.out.println("((" + intersection[0] + ", " + intersection[1] + "))");
              playerPieces1.add(new int[]{intersection[0], intersection[1]});
            }

        }
        if(playerPieces1.isEmpty())
        {
          playerPieces1.addAll(playerPieces);
        }


     return playerPieces1.toArray(new int[0][0]);
}
   
   
   public int[][] findAdjacentValidIntersections(int x, int y) {
     ArrayList<int[]> adjacentIntersections = new ArrayList<>();
     int[][] possibleNeighbors = {
             {x - 2, y}, {x + 2, y}, {x, y - 2}, {x, y + 2}
         };
         // Handle specific cases where the piece is at a corner
         if ((x == 0 && y == 0) || (x == 6 && y == 0) || (x == 0 && y == 6) || (x == 6 && y == 6)) {
             possibleNeighbors = new int[][] {
                 {x + 3, y}, {x, y + 3}, {x - 3, y}, {x, y - 3}
             };
         }
         else if((x == 3 && y == 6) || (x == 3 && y == 0))
         {
           possibleNeighbors = new int[][] {
                 {x, y - 1}, {x + 3, y}, {x - 3, y}, {x, y + 1}
             };
         }
         else if(x == 0 && y == 3 || x == 6 && y == 3)
         {
           possibleNeighbors = new int[][] {
                 {x , y + 3}, {x, y - 3}, {x + 1, y}, {x - 1, y}
             };
         }
         // Handle the specific case where the piece is at (1,3)
         else if ((x == 1 && y == 3) ||(x == 5 && y == 3 )) {
             possibleNeighbors = new int[][] {
                 {x + 1, y},{x - 1, y},{x, y - 2},{x, y + 2}
             };
           }
          else if ((x == 3 && y == 1) ||(x == 3 && y == 5 )) {
             possibleNeighbors = new int[][] {
                 {x , y + 1},{x, y - 1},{x - 2, y},{x + 2, y}
             };   
         }
          else if ((x == 2 && y == 3) || (x == 3 && y == 4) || (x == 3 && y == 2) || (x == 4 && y == 3)) {
             possibleNeighbors = new int[][] {
                 {x - 1, y},{x , y + 1},{x, y - 1},{x + 1, y}
             };   
         }
         else if((x == 2 && y == 2) || (x == 2 && y == 4) || (x == 4 && y == 2) || (x == 4 && y == 4))
         {
         possibleNeighbors = new int[][]{
             {x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}
         };
       }

         for (int[] neighbor : possibleNeighbors) {
             int nx = neighbor[0];
             int ny = neighbor[1];
             if(nx >=0 && nx < 7 && ny >=0 && ny < 7 )
             {
             if (isValidIntersecction(nx, ny) && boardState[nx][ny] == 0) {
                 adjacentIntersections.add(new int[]{nx,ny});
             }
           }
         }
       return adjacentIntersections.toArray(new int[0][0]);
     }
   
   
   
   
   
   
   
   
   
   
}
