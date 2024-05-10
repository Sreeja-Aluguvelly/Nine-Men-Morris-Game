
package GUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class NineMenMorrisBoard {
  private int[][] boardState;
  private int currentPlayer;
  private int blackPieces;
  private int whitePieces;
  private int[][] highlightedIntersections;
 public static List<MoveRecord> recordedMoves;
 static private List<MoveRecord> recordedPrevMoves= new ArrayList<>();;
  public enum MoveType {
    PLACING, MOVING, FLYING, REMOVING
}
  private String gameType;  


  public NineMenMorrisBoard(String gameType2,int blackPieces, int whitePieces) {
    boardState = new int[7][7];
    currentPlayer = 1;
    recordedMoves = new ArrayList<>();
    gameType=gameType2;
    this.blackPieces=blackPieces;
    this.whitePieces=whitePieces;
    
  }
  
  public int[] findRandomValidIntersection() {
    System.out.println("In this"+gameType);

   
   if("Human vs Computer".equals(gameType) ){
        
      
      Random random = new Random();

      while (true) {
          int x = random.nextInt(7); // Assuming grid size is 7x7
          int y = random.nextInt(7);
          System.out.println(x+y);

          if (isValidMove(x, y)) {
              // Found a valid intersection, return the coordinates
            System.out.println("returning this");
            System.out.println(x+"   "+y);
            return new int[]{x, y};
          }
      }
      
    }
    return new int[]{-1,-1};

  }
  
  public int[] findMovedIntersection(int[] selectedArray){
    
    
    int[][] intersections = findAdjacentValidIntersections(selectedArray[0], selectedArray[1]);

    for(int[] inter:intersections) {
    
    if(isValidMove(inter[0], inter[1]))
    {
      System.out.println("is valid move");
      getBoardState()[inter[0]][inter[1]]=1;
      if(millformed(boardState,inter[0], inter[1],1))
      {
        System.out.println("is valid move");

        getBoardState()[inter[0]][inter[1]]=0;
        return new int[]{inter[0], inter[1]};
      }
      else {
        continue;
      }

    }
    getBoardState()[inter[0]][inter[1]]=0;

        
    
    }
    return new int[] {-1,-1};
    
  }
  
  
  

  public  int[] getRandomElementToMove() {
    
    
    System.out.println("ingettomove");
    
    List<int[]> playerPieces = new ArrayList<>();
    
    for (int row = 0; row < 7; row++) {
      for (int col = 0; col < 7; col++) {
        if (boardState[row][col] == 1) {
          playerPieces.add(new int[] {row, col});
        }
      }
    }
   int array[][]= playerPieces.toArray(new int[0][0]);
    
    
    while(true) {

   if (array != null && array.length > 0) {
        Random random = new Random();
        int randomIndex = random.nextInt(array.length);

        // Assuming each 1D array in the 2D array has the same length
        int[] selectedArray = array[randomIndex];
        
        
        
        int[][] intersections = findAdjacentValidIntersections(selectedArray[0], selectedArray[1]);
        
        
        
        
        
      if (intersections.length != 0) {
        return selectedArray;     
        }
      
      
     if (intersections.length> 4) {
       
       
       
       if(isValidMove(selectedArray[0], selectedArray[1]))
       {
         System.out.println("is valid move");
         getBoardState()[selectedArray[0]][selectedArray[1]]=1;
         if(millformed(boardState,selectedArray[0], selectedArray[1],1))
         {
           System.out.println("is valid move");

           getBoardState()[selectedArray[0]][selectedArray[1]]=0;
           return new int[]{selectedArray[0], selectedArray[1]};
         }
         getBoardState()[selectedArray[0]][selectedArray[1]]=0;


       }
       
       
       
       }
      

    } else {
        return null;
    }
}
  }
  
  public  int[] getRandomElement(int[][] array) {
    if (array != null && array.length > 0) {
        Random random = new Random();
        int randomIndex = random.nextInt(array.length);

        // Assuming each 1D array in the 2D array has the same length
        int[] selectedArray = array[randomIndex];

        return selectedArray;
    } else {
        return null;
    }
}
  
  

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

  public int[] makeStrategicMove(int[][] boardState) {
    for (int row = 0; row < 7; row++) {
      for (int col = 0; col < 7; col++) {
        if(isValidMove(row,col))
        {
          System.out.println("is valid move");
          getBoardState()[row][col]=2;
          if(millformed(boardState,row,col,2))
          {
            System.out.println("is valid move");

            getBoardState()[row][col]=0;
            return new int[]{row, col};
          }
          getBoardState()[row][col]=0;


        }
        
      }       
    }
    return new int[]{-1,-1};
  }
  
  public int[] makeStrategicMoveToPlace(int[][] boardState) {
    for (int row = 0; row < 7; row++) {
      for (int col = 0; col < 7; col++) {
        if(isValidMove(row,col))
        {
          System.out.println("is valid move");
          getBoardState()[row][col]=1;
          if(millformed(boardState,row,col,1))
          {
            System.out.println("is valid move");

            getBoardState()[row][col]=0;
            return new int[]{row, col};
          }
          getBoardState()[row][col]=0;


        }
        
      }       
    }
    return new int[]{-1,-1};
  
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



    if (getWhitePieces() <= 0 && getBlackPieces() <= 0) {


      if (hasFewerThanThreePieces(boardState, opponent)) {
        
        printRecordedMoves();
        return true;
      }

      if (!hasLegalMoves(boardState, opponent)) {
        printRecordedMoves();
        return true;
      }


    }


    return false;
  }
  
  
        
        
        
        
        
        
      
//        if (boardState[row][col] == player) {
//        //  playerCount=playerCount+1;
//        }
//        else if(boardState[row][col] == 0)
//        {
//          playerPieces.add(new int[] {row, col});
//        }
//      }
//      if(playerCount==2)
//      {
//        return playerPieces.toArray(new int[0][0]);
//



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
    int[][] intersections = new int[0][0];
    boolean value = false;
    for (int row = 0; row < boardState.length; row++) {
      for (int col = 0; col < boardState[row].length; col++) {
        if (boardState[row][col] == player) {
          intersections = findAdjacentValidIntersections(row, col);
          if (intersections.length != 0) {
            value = true;
            break;
          }

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

  
  public boolean removePiece1(int[][] boardState, int row, int col) {
    if (boardState[row][col] != 0) {
      boardState[row][col] = 0;
      return true;
    }
    return false;
  }
   public void nextClickOnReplay(List<MoveRecord> recordedMoves1) {
   System.out.println("Click Next");  
   printRecordedMoves();
   recordedMoves=recordedMoves1;
   Iterator<MoveRecord> iterator = recordedMoves.iterator();
//   while (iterator.hasNext()) {
     MoveRecord move = iterator.next();
       try {
         boolean success=false;
         boolean success2=false;

         switch (move.getMoveType()) {
           case PLACING:
             System.out.println("in placing"+ getCurrentPlayer());
             
             
             success= placePiece(move.getDestRow() ,move.getDestCol());
             System.out.println(success);
             System.out.println("count of white"+getWhitePieces());

               break;
           case REMOVING:{
             System.out.println("in removing");

             success2=  removePiece(boardState, move.getDestRow(), move.getDestCol()) ;
             System.out.println("before turn   "+getCurrentPlayer());

             changePlayerTurn();
             System.out.println("after turn   "+getCurrentPlayer());

               break;}
           case MOVING:
             System.out.println("in moving");

             success=  movePiece(move.getSourceRow(),move.getSourceCol(),move.getDestRow(),move.getDestCol());
               break;
               
           default:
               System.out.println("Unknown move type: " + move.moveType);
               break;
       }

         
         if (success) {
           boolean millformed =
               millformed(getBoardState(), move.getDestRow(), move.getDestCol(), getCurrentPlayer());

           if (millformed) {
                    System.out.println("In computer removing piece");   
             
           }
           else {
     System.out.println("In mill not formed  chnging");
      changePlayerTurn();

           }
     
         }
           
         recordedPrevMoves.add(0,new MoveRecord(move.moveType, move.getSourceRow(),move.getSourceCol(),move.getDestRow(),move.getDestCol())); // Store the move
         iterator.remove(); // Remove the move from the list after processing
       } catch (IllegalArgumentException e) {
           System.out.println("Invalid move type: " + move.moveType);
           iterator.remove(); // Remove the invalid move from the list
       }

   
   }
   
   
   

   public void prevClickOnReplay(List<MoveRecord> recordedMoves1) {
   System.out.println("Click Prev");  
   
   
   
   
   printRecordedPrevMoves();
   recordedPrevMoves=recordedMoves1;
   Iterator<MoveRecord> iterator = recordedPrevMoves.iterator();
//   while (iterator.hasNext()) {
     MoveRecord move = iterator.next();
       try {
         boolean success=false;
         boolean success2=false;
         
         
         
       
         
         
         
         
         

         switch (move.getMoveType()) {
           case PLACING:
             
             System.out.println("in placing"+ getCurrentPlayer());
             
            // getBoardState()[move.getDestRow() ][move.getDestCol()] = 0;
             success2=  removePiece1(boardState, move.getDestRow(), move.getDestCol()) ;

             //success= placePiece(move.getDestRow() ,move.getDestCol());
             System.out.println(success2);
             System.out.println("count of white"+getWhitePieces());

               break;
           case REMOVING:{
             System.out.println("in removing");
             //getBoardState()[move.getDestRow() ][move.getDestCol()] = getCurrentPlayer();
             success= placePiece(move.getDestRow() ,move.getDestCol());

             //success2=  removePiece(boardState, move.getDestRow(), move.getDestCol()) ;
             System.out.println("before turn   "+getCurrentPlayer());

             changePlayerTurn();
             System.out.println("after turn   "+getCurrentPlayer());
              success2=true;

               break;}
           case MOVING:

             System.out.println("in placing"+ getCurrentPlayer());
             
             getBoardState()[move.getDestRow() ][move.getDestCol()] = 0;

             //success= placePiece(move.getDestRow() ,move.getDestCol());
             System.out.println("count of white"+getWhitePieces());

               break;
               
           default:
               System.out.println("Unknown move type: " + move.moveType);
               break;
       }

         
         
         
         
         
         if (!success2) {
           boolean millformed =
               millformed(getBoardState(), move.getDestRow(), move.getDestCol(), getCurrentPlayer());

           if (millformed) {
                    System.out.println("In computer removing piece");   

             
           }
           else {
             changePlayerTurn();

     System.out.println("In mill not formed  chnging");

           }
     
         }
           

         

         recordedMoves.add(0,new MoveRecord(move.moveType, move.getSourceRow(),move.getSourceCol(),move.getDestRow(),move.getDestCol())); // Store the move

           iterator.remove(); // Remove the move from the list after processing
       } catch (IllegalArgumentException e) {
           System.out.println("Invalid move type: " + move.moveType);
           iterator.remove(); // Remove the invalid move from the list
       }

   
   }
  
  
  
  
  public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
    int[][] boardState = getBoardState();
    int currentPlayer = getCurrentPlayer();
    if (boardState[toRow][toCol] == 0) {
      if (currentPlayer == 2 && getBlackPieces() == 0) {
        boardState[toRow][toCol] = 2;
        boardState[fromRow][fromCol] = 0;
        return true;
      } else if (currentPlayer == 1 && getWhitePieces() == 0) {
        boardState[toRow][toCol] = 1;
        boardState[fromRow][fromCol] = 0;
        return true;
      }
    }
    return false;
  }


  public boolean millformed(int[][] board, int row, int col, int player) {


    if (hasHorizontalMill(board, row, col, player)) {
      return true;

    }

    if (hasVerticalMill(board, row, col, player)) {
      return true;

    }



    return false;
  }

  public boolean millformed1(int[][] board, int row,int col, int player) {
    
    
    if(hasHorizontalMill(board,row,col,player)) {
      return true;
      
    }
    
   if(hasVerticalMill(board,row,col,player)) {
     return true;
      
    }

    if(hasDiagonalMill(board,row,col,player)) {
     return true;
      
    }
    
    
    
    return false;
 }
  

  public boolean hasHorizontalMill(int[][] board, int row, int col, int player) {
    if (row % 2 == 0) {
      return (board[row][0] == player && board[row][3] == player && board[row][6] == player)
          || (board[row][2] == player && board[row][3] == player && board[row][4] == player);
    } else if (row == 3 && (col == 0 || col == 1 || col == 2)) {
      return (board[row][0] == player && board[row][1] == player && board[row][2] == player);
    } else if (row == 3 && (col == 4 || col == 5 || col == 6)) {
      return (board[row][4] == player && board[row][5] == player && board[row][6] == player);
    } else {
      // Check horizontal mills in valid locations (odd rows).
      return (board[row][1] == player && board[row][3] == player && board[row][5] == player);
    }
  }

  public boolean hasVerticalMill(int[][] board, int row, int col, int player) {
    if (col % 2 == 0) {
      return (board[0][col] == player && board[3][col] == player && board[6][col] == player)

          || (board[2][col] == player && board[3][col] == player && board[4][col] == player);
    } else if (col == 3 && (row == 0 || row == 1 || row == 2)) {
      return (board[0][col] == player && board[1][col] == player && board[2][col] == player);
    } else if (col == 3 && (row == 4 || row == 5 || row == 6)) {
      return (board[6][col] == player && board[4][col] == player && board[5][col] == player);
    } else {
      // Check vertical mills in valid locations (odd columns).
      return (board[1][col] == player && board[3][col] == player && board[5][col] == player);
    }
  }

  public boolean hasDiagonalMill(int[][] board,int row, int col, int player)
  {
    if(row==col && row<3)
    {
      return (board[0][0] == player && board[1][1] == player && board[2][2] == player);
    }
    else if(row==col && row>3)
    {
      return (board[4][4] == player && board[5][5] == player && board[6][6] == player);
    }
    else if(row<3 && col>3)
    {
      return  (board[0][6] == player && board[1][5] == player && board[2][4] == player);
    }
    else if(row>3 && col<3)
    {
      return (board[4][2] == player && board[5][1] == player && board[6][0] == player);
    }
    else
    {
      return false;
    }
  }
  

  public int[][] getplayerpieces(int[][] boardState, int player, boolean gameVariant) {



    List<int[]> playerPieces = new ArrayList<>();
    List<int[]> playerPieces1 = new ArrayList<>();
    for (int row = 0; row < 7; row++) {
      for (int col = 0; col < 7; col++) {
        if (boardState[row][col] == player) {
          playerPieces.add(new int[] {row, col});
        }
      }
    }

    for (int[] intersection : playerPieces) {
      if(gameVariant==true)
      {
       if(!(millformed(getBoardState(),intersection[0] ,intersection[1], player)))
       {
        // System.out.println("((" + intersection[0] + ", " + intersection[1] + "))");
         playerPieces1.add(new int[]{intersection[0], intersection[1]});
       }
     }
     else{
       if(!(millformed1(getBoardState(),intersection[0] ,intersection[1], player)))
       {
        // System.out.println("((" + intersection[0] + ", " + intersection[1] + "))");
         playerPieces1.add(new int[]{intersection[0], intersection[1]});
       }
     }

   }
    if (playerPieces1.isEmpty()) {
      playerPieces1.addAll(playerPieces);
    }


    return playerPieces1.toArray(new int[0][0]);
  }


  public int[][] findAdjacentValidIntersections(int x, int y) {
    int count = countPlayerPieces();
    ArrayList<int[]> adjacentIntersections = new ArrayList<>();
    if (count == 3) {
      for (int row = 0; row < 7; row++) {
        for (int col = 0; col < 7; col++) {
          if (boardState[row][col] == 0 && isValidIntersecction(row, col)) {
            adjacentIntersections.add(new int[] {row, col});
          }
        }
      }
    } else {
      int[][] possibleNeighbors = {{x - 2, y}, {x + 2, y}, {x, y - 2}, {x, y + 2}};
      if ((x == 0 && y == 0) || (x == 6 && y == 0) || (x == 0 && y == 6) || (x == 6 && y == 6)) {
        possibleNeighbors = new int[][] {{x + 3, y}, {x, y + 3}, {x - 3, y}, {x, y - 3}};
      } else if ((x == 3 && y == 6) || (x == 3 && y == 0)) {
        possibleNeighbors = new int[][] {{x, y - 1}, {x + 3, y}, {x - 3, y}, {x, y + 1}};
      } else if (x == 0 && y == 3 || x == 6 && y == 3) {
        possibleNeighbors = new int[][] {{x, y + 3}, {x, y - 3}, {x + 1, y}, {x - 1, y}};
      }
      // Handle the specific case where the piece is at (1,3)
      else if ((x == 1 && y == 3) || (x == 5 && y == 3)) {
        possibleNeighbors = new int[][] {{x + 1, y}, {x - 1, y}, {x, y - 2}, {x, y + 2}};
      } else if ((x == 3 && y == 1) || (x == 3 && y == 5)) {
        possibleNeighbors = new int[][] {{x, y + 1}, {x, y - 1}, {x - 2, y}, {x + 2, y}};
      } else if ((x == 2 && y == 3) || (x == 3 && y == 4) || (x == 3 && y == 2)
          || (x == 4 && y == 3)) {
        possibleNeighbors = new int[][] {{x - 1, y}, {x, y + 1}, {x, y - 1}, {x + 1, y}};
      } else if ((x == 2 && y == 2) || (x == 2 && y == 4) || (x == 4 && y == 2)
          || (x == 4 && y == 4)) {
        possibleNeighbors = new int[][] {{x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}};
      }

      for (int[] neighbor : possibleNeighbors) {
        int nx = neighbor[0];
        int ny = neighbor[1];
        if (nx >= 0 && nx < 7 && ny >= 0 && ny < 7) {
          if (isValidIntersecction(nx, ny) && boardState[nx][ny] == 0) {
            adjacentIntersections.add(new int[] {nx, ny});
          }
        }
      }
    }
    return adjacentIntersections.toArray(new int[0][0]);
  }
  
  public int[][] findAdjacentValidIntersections1(int x, int y) {
    int count = countPlayerPieces();
    ArrayList<int[]> adjacentIntersections = new ArrayList<>();
    if (count == 3) {
      for (int row = 0; row < 7; row++) {
        for (int col = 0; col < 7; col++) {
          if (boardState[row][col] == 0 && isValidIntersecction(row, col)) {
            adjacentIntersections.add(new int[] {row, col});
          }
        }
      }
    } else {
      int[][] possibleNeighbors = {{x - 2, y}, {x + 2, y}, {x, y - 2}, {x, y + 2},{x-1, y -1},{x+1, y + 1},{x-1, y + 1},{x+1, y -1}};
      if ((x == 0 && y == 0) || (x == 6 && y == 0) || (x == 0 && y == 6) || (x == 6 && y == 6)) {
        possibleNeighbors = new int[][] {{x + 3, y}, {x, y + 3}, {x - 3, y}, {x, y - 3}, {x+1, y+1},{x+1, y - 1},{x-1, y +1},{x-1, y - 1}};
      } else if ((x == 3 && y == 6) || (x == 3 && y == 0)) {
        possibleNeighbors = new int[][] {{x, y - 1}, {x + 3, y}, {x - 3, y}, {x, y + 1}};
      } else if (x == 0 && y == 3 || x == 6 && y == 3) {
        possibleNeighbors = new int[][] {{x, y + 3}, {x, y - 3}, {x + 1, y}, {x - 1, y}};
      }
      // Handle the specific case where the piece is at (1,3)
      else if ((x == 1 && y == 3) || (x == 5 && y == 3)) {
        possibleNeighbors = new int[][] {{x + 1, y}, {x - 1, y}, {x, y - 2}, {x, y + 2}};
      } else if ((x == 3 && y == 1) || (x == 3 && y == 5)) {
        possibleNeighbors = new int[][] {{x, y + 1}, {x, y - 1}, {x - 2, y}, {x + 2, y}};
      } else if ((x == 2 && y == 3) || (x == 3 && y == 4) || (x == 3 && y == 2)
          || (x == 4 && y == 3)) {
        possibleNeighbors = new int[][] {{x - 1, y}, {x, y + 1}, {x, y - 1}, {x + 1, y}};
      } else if ((x == 2 && y == 2) || (x == 2 && y == 4) || (x == 4 && y == 2)
          || (x == 4 && y == 4)) {
        possibleNeighbors = new int[][] {{x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1},{x-1, y - 1},{x-1, y +1},{x+1, y - 1},{x+1, y +1}};
      }

      for (int[] neighbor : possibleNeighbors) {
        int nx = neighbor[0];
        int ny = neighbor[1];
        if (nx >= 0 && nx < 7 && ny >= 0 && ny < 7) {
          if (isValidIntersecction(nx, ny) && boardState[nx][ny] == 0) {
            adjacentIntersections.add(new int[] {nx, ny});
          }
        }
      }
    }
    return adjacentIntersections.toArray(new int[0][0]);
  }

  public int countPlayerPieces() {
    int count = 0;
    for (int row = 0; row < 7; row++) {
      for (int col = 0; col < 7; col++) {
        if (boardState[row][col] == getCurrentPlayer()) {
          count = count + 1;
        }
      }
    }
    return count;

  }
  
  public int countPieces()
  {
    int count=0;
    for (int row = 0; row < 7; row++) {
           for (int col = 0; col < 7; col++) {
               if (boardState[row][col] != 0) {
                count=count+1;
               }
              }
            }
            System.out.println("Coins count is"+count);
    return count;
  }
  
   public void recordMove(MoveType moveType, int sourceRow, int sourceCol, int destRow, int destCol) {
    MoveRecord moveRecord = new MoveRecord(moveType, sourceRow, sourceCol, destRow, destCol);
    recordedMoves.add(moveRecord);
}
  
  public List<MoveRecord> getRecordedMoves() {
    return recordedMoves;
}
  public List<MoveRecord> getRecordedPrevMoves() {
    return recordedPrevMoves;
}
  public void printRecordedMoves() {
    List<MoveRecord> recordedMoves = getRecordedMoves();

    System.out.println("Recorded Moves:");
    for (MoveRecord move : recordedMoves) {
        System.out.println("Move Type: " + move.getMoveType() +
                ", Source: (" + move.getSourceRow() + ", " + move.getSourceCol() +
                "), Destination: (" + move.getDestRow() + ", " + move.getDestCol() + ")");
    }
}

  
  
  public void printRecordedPrevMoves() {
    List<MoveRecord> recordedPrevMoves = getRecordedPrevMoves();

    System.out.println("Recorded Moves:");
    for (MoveRecord move : recordedPrevMoves) {
        System.out.println("Move Type: " + move.getMoveType() +
                ", Source: (" + move.getSourceRow() + ", " + move.getSourceCol() +
                "), Destination: (" + move.getDestRow() + ", " + move.getDestCol() + ")");
    }
}

  
  
  
  
  // New inner class to represent a move with details
  public static class MoveRecord {
    private MoveType moveType;
    private int sourceRow;
    private int sourceCol;
    private int destRow;
    private int destCol;

    public MoveRecord(MoveType moveType, int sourceRow, int sourceCol, int destRow, int destCol) {
        this.moveType = moveType;
        this.sourceRow = sourceRow;
        this.sourceCol = sourceCol;
        this.destRow = destRow;
        this.destCol = destCol;
    }

    // Add getters for move details
    public MoveType getMoveType() {
        return moveType;
    }

    public int getSourceRow() {
        return sourceRow;
    }

    public int getSourceCol() {
        return sourceCol;
    }

    public int getDestRow() {
        return destRow;
    }

    public int getDestCol() {
        return destCol;
    }
}
  



}








