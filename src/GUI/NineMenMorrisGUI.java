package GUI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import GUI.NineMenMorrisBoard;

public class NineMenMorrisGUI extends JPanel {
  private NineMenMorrisBoard board;

  public NineMenMorrisGUI() {

    board = new NineMenMorrisBoard();

    // To create user interface
    setLayout(new BorderLayout());
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    leftPanel.setPreferredSize(new Dimension(200, 700)); // Adjust the width as needed
    leftPanel.setBackground(Color.LIGHT_GRAY);

    // Player 1 information
    JLabel player1Label = new JLabel("Player 1 (White)");
    leftPanel.add(player1Label, gbc);
    Font newFont = new Font(player1Label.getFont().getName(), Font.BOLD, 20);
    player1Label.setFont(newFont);
    JLabel player1PiecesLabel = new JLabel("Number Of Pieces Remaining :");
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.CENTER;
    leftPanel.add(player1PiecesLabel, gbc);
    Font newFont2 = new Font(player1PiecesLabel.getFont().getName(), Font.BOLD, 12);
    player1PiecesLabel.setFont(newFont2);
    JLabel player1PiecesCount = new JLabel("" + board.getWhitePieces());
    gbc.gridy = 2;
    leftPanel.add(player1PiecesCount, gbc);
    Font newFont1 = new Font(player1PiecesCount.getFont().getName(), Font.BOLD, 42); // Adjust the
                                                                                     // font size as
                                                                                     // needed
    player1PiecesCount.setFont(newFont1);
    JLabel player1TurnLabel = new JLabel("Player 1 Turn");
    gbc.gridy = 3;
    leftPanel.add(player1TurnLabel, gbc);
    player1TurnLabel.setFont(newFont);
    JPanel rightPanel = new JPanel();
    rightPanel.setPreferredSize(new Dimension(200, 700));
    rightPanel.setBackground(Color.LIGHT_GRAY);
    rightPanel.setLayout(new GridBagLayout());


    // Player 2 information
    JLabel player2Label = new JLabel("Player 2 (Black)");
    GridBagConstraints gbc1 = new GridBagConstraints();
    rightPanel.add(player2Label, gbc1);
    player2Label.setFont(newFont);
    JLabel player2PiecesLabel = new JLabel("Number Of Pieces Remaining :");
    gbc1.gridy = 1;
    rightPanel.add(player2PiecesLabel, gbc1);
    player2PiecesLabel.setFont(newFont2);
    JLabel player2PiecesCount = new JLabel("" + board.getBlackPieces());
    gbc1.gridy = 2;
    rightPanel.add(player2PiecesCount, gbc1);
    player2PiecesCount.setFont(newFont1);
    JLabel player2TurnLabel = new JLabel("Player 2 Turn");
    player2TurnLabel.setVisible(false);
    player2TurnLabel.setFont(newFont);
    gbc1.gridy = 3;
    rightPanel.add(player2TurnLabel, gbc1);


    // Board Panel
    JPanel boardPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4)); // Adjust the width (4 in this example)

        int cellWidth = getWidth() / 7;
        int cellHeight = getHeight() / 7;
        int ovalSize = Math.min(cellWidth, cellHeight) - 50; // Adjust size as needed

        setBackground(Color.LIGHT_GRAY);
        g2d.setColor(Color.black);
        g2d.drawLine(3 * cellWidth + cellWidth / 2, cellWidth / 2, 3 * cellWidth + cellWidth / 2,
            2 * cellHeight + cellHeight / 2);
        g2d.drawLine(3 * cellWidth + cellWidth / 2, 4 * cellHeight + cellHeight / 2,
            3 * cellWidth + cellWidth / 2, 6 * cellHeight + cellHeight / 2);
        g2d.drawLine(cellHeight / 2, 3 * cellHeight + cellHeight / 2, 2 * cellWidth + cellWidth / 2,
            3 * cellHeight + cellHeight / 2);
        g2d.drawLine(4 * cellWidth + cellWidth / 2, 3 * cellHeight + cellHeight / 2,
            6 * cellWidth + cellWidth / 2, 3 * cellHeight + cellHeight / 2);
        g2d.drawLine(cellHeight / 2, 6 * cellHeight + cellHeight / 2, 6 * cellWidth + cellWidth / 2,
            6 * cellHeight + cellHeight / 2);
        g2d.drawLine(cellHeight / 2, cellHeight / 2, 6 * cellWidth + cellWidth / 2, cellHeight / 2);
        g2d.drawLine(cellHeight + cellHeight / 2, cellHeight + cellHeight / 2,
            5 * cellWidth + cellWidth / 2, cellHeight + cellHeight / 2);
        g2d.drawLine(cellHeight + cellHeight / 2, 5 * cellHeight + cellHeight / 2,
            5 * cellWidth + cellWidth / 2, 5 * cellHeight + cellHeight / 2);
        g2d.drawLine(1 * cellWidth + cellWidth / 2, cellHeight + cellHeight / 2,
            cellWidth + cellWidth / 2, 5 * cellHeight + cellHeight / 2);
        g2d.drawLine(5 * cellWidth + cellWidth / 2, cellHeight + cellHeight / 2,
            5 * cellWidth + cellWidth / 2, 5 * cellHeight + cellHeight / 2);
        g2d.drawLine(2 * cellWidth + cellWidth / 2, 2 * cellHeight + cellHeight / 2,
            2 * cellWidth + cellWidth / 2, 4 * cellHeight + cellHeight / 2);
        g2d.drawLine(4 * cellWidth + cellWidth / 2, 2 * cellHeight + cellHeight / 2,
            4 * cellWidth + cellWidth / 2, 4 * cellHeight + cellHeight / 2);
        g2d.drawLine(cellHeight / 2, 6 * cellHeight + cellHeight / 2, cellHeight / 2,
            cellHeight / 2);
        g2d.drawLine(6 * cellWidth + cellWidth / 2, cellHeight / 2, 6 * cellWidth + cellWidth / 2,
            6 * cellHeight + cellHeight / 2);
        g2d.drawLine(2 * cellWidth + cellWidth / 2, 2 * cellHeight + cellHeight / 2,
            4 * cellWidth + cellWidth / 2, 2 * cellHeight + cellHeight / 2);
        g2d.drawLine(4 * cellWidth + cellWidth / 2, 4 * cellHeight + cellHeight / 2,
            2 * cellWidth + cellWidth / 2, 4 * cellHeight + cellHeight / 2);

        g2d.setColor(Color.black);

        int[][] validIntersections = board.getHighlightedIntersections(); // Retrieve the stored valid intersections

        // Draw pieces on the board based on boardState
        for (int row = 0; row < 7; row++) { 
          for (int col = 0; col < 7; col++) {

            int player = board.getBoardState()[row][col];
            
            
           
            
            boolean highlight = false;
            
   
            
            
            if (player == 1) {
              ovalSize = Math.min(cellWidth, cellHeight) - 40; // Adjust size as needed
              g.setColor(Color.WHITE);
              g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                  row * cellHeight + (cellHeight - ovalSize) / 2, ovalSize, ovalSize);
            } else if (player == 2) {
              ovalSize = Math.min(cellWidth, cellHeight) - 40; // Adjust size as needed
              g.setColor(Color.BLACK);
              g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                  row * cellHeight + (cellHeight - ovalSize) / 2, ovalSize, ovalSize);
            } else {
              ovalSize = Math.min(cellWidth, cellHeight) - 50; // Adjust size as needed
              if (board.isValidIntersecction(row, col)) {
                g.setColor(Color.GRAY); // Change color as needed
                g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                    row * cellHeight + (cellHeight - ovalSize) / 2, ovalSize, ovalSize);
              }
              
              
              
              
              if (validIntersections != null && validIntersections.length > 0) {

                
                
                for (int i = 0; i < validIntersections.length; i++) {
                    
                    
                    if (validIntersections[i][0] == row && validIntersections[i][1] == col) {

                      System.out.println("In repaint to paint ");

                      System.out.print(validIntersections[i][0] + " " +validIntersections[i][1]);
                      ovalSize = Math.min(cellWidth, cellHeight) - 50; // Adjust size as needed

                      g.setColor(Color.yellow); // Change color as needed
                      g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                          row * cellHeight + (cellHeight - ovalSize) / 2, ovalSize, ovalSize);
                      
                    
                  }
              }  
              
                
                
                
                
                
                /*
              for (int[] intersection : validIntersections) {
                  if (intersection[0] == row && intersection[1] == col) {
                      highlight = true;
                      System.out.println("In repaint to paint ");
                      System.out.println(intersection[0]+ intersection[1]);
                      
                      g.setColor(Color.yellow); // Change color as needed
                      g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                          row * cellHeight + (cellHeight - ovalSize) / 2, ovalSize, ovalSize);
                       }
              }
              */
              }
              
              
              
              
              
              
              
              
              
            }
          }
        }
        
        
        
        

      }
    };

    boardPanel.addMouseListener(new MouseAdapter() {
      
      

      public void mouseClicked(MouseEvent e) {
        
        System.out.println("in first  mouse listerner");
 
        board.setHighlightedIntersections(new int[0][0]);
        System.out.println("Mouse clicked at row " + e);

        int mouseX = e.getX();
        int mouseY = e.getY();

        int cellWidth = boardPanel.getWidth() / 7;
        int cellHeight = boardPanel.getHeight() / 7;

        int clickedRow = mouseY / cellHeight;
        int clickedCol = mouseX / cellWidth;

        int row = clickedRow;
        int col = clickedCol;

     

        if (board.getBlackPieces() > 0 || board.getWhitePieces() > 0) {

          boolean successstatus = board.placePiece(row, col);
          if (successstatus) {
            board.changePlayerTurn();
            boardPanel.repaint();
            
            //var a=board.millformed( board.getBoardState(),row,col,   board.getCurrentPlayer() == 1 ?  2: 1);   
            //System.out.println("A is");
            
            //System.out.println(a);
            
            
         //   if(a) {
           //   System.out.println("mill formed");
              //Highlight pieces to remove
              

              //board.setHighlightedIntersections(board.getplayerpieces(board.getBoardState(), board.getCurrentPlayer() == 1 ?  2: 1));
              
            //}
            
            
            
            
            
            
            
            
            
            
            
            
            
          }

          player1PiecesCount.setText("" + board.getWhitePieces());
          player2PiecesCount.setText("" + board.getBlackPieces());
          if (board.getCurrentPlayer() == 2) {
            player1TurnLabel.setVisible(false);
            player2TurnLabel.setVisible(true);

          } else {
            player1TurnLabel.setVisible(true);
            player2TurnLabel.setVisible(false);

          }


        } else {
          player1TurnLabel.setVisible(false);
          player2TurnLabel.setVisible(false);
          player2PiecesCount.setVisible(false);
          player1PiecesCount.setVisible(false);
          player1PiecesLabel.setVisible(false);
          player2PiecesLabel.setVisible(false);
          
          int player = board.getBoardState()[row][col];
          System.out.println("it is player 1 o 2"+ player);

          
          if(player ==1 || player ==2) {
            
            System.out.println("it is player 1 o 2");
            int[][] intersections = board.findAdjacentValidIntersections(row, col);

            board.setHighlightedIntersections(intersections);
            repaint();
            System.out.println("Available INtersections");

            for (int i = 0; i < intersections.length; i++) {
              for (int j = 0; j < intersections[i].length; j++) {
                  System.out.print(intersections[i][j] + " ");
              }
              System.out.println(); // Move to the next line for the next row
          }  
            
            
            
            // Add a new mouse listener for move functionality in the "else" case
            boardPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    // Handle move piece functionality
                    // You can use the same structure as the first mouse listener
                    // for handling move piece actions.
                  
                  System.out.println("in second mouse listerner");
                  int mouseX2 = e.getX();
                  int mouseY2 = e.getY();

                

                  int clickedRow = mouseY2 / cellHeight;
                  int clickedCol = mouseX2 / cellWidth;

                  int row2 = clickedRow;
                  int col2 = clickedCol;
                  System.out.print("new to");      
                  System.out.print(row2);      
                  System.out.print(col2);    
                  System.out.print("old from");      
                  System.out.print(row);      
                  System.out.print(col);      
                  
                  
                  boolean contains=false;
                  for (int[] row : intersections) {
                    for (int value : row) {
                        if (value == row2 && value == col2) {
                            contains = true;
                            break;
                        }
                    }
                }
                  
                  if (contains) {
                    System.out.println("in contains");
                    var succ=  board.movePiece(row2, col2, row, col);
                    
                    
                    
                    
                    
                    boardPanel.repaint();
                    
                    for (int i = 0; i < 7; i++) {
                      for (int j = 0; j < 7; j++) {
                          System.out.println("Row: " + i + ", Column: " + j + ", Value: " + board.getBoardState()[i][j]);
                      }
                  }
                    board.changePlayerTurn();
                    boardPanel.removeMouseListener(this);
                } else {
                  System.out.println("in not contains");
                  
                }
                  
                 
                  
                }
            });
            
            
          
            
            
            
          
            
            
            
            
            
            
          }
          
          
        
          
          

        }



      }

    });

    setLayout(new BorderLayout());
    add(leftPanel, BorderLayout.WEST);
    add(rightPanel, BorderLayout.EAST);
    add(boardPanel, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Nine Men's Morris");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 700);
      frame.add(new NineMenMorrisGUI());
      frame.setVisible(true);
    });
  }
}






























