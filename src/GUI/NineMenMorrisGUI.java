package GUI;
import java.awt.*;
import javax.swing.*;
import GUI.NineMenMorrisBoard;

/**
public class NineMenMorrisGUI {
    private JFrame frame;
    private NineMenMorrisBoard board;

    public NineMenMorrisGUI() {
        frame = new JFrame("Nine Men's Morris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        board = new NineMenMorrisBoard();
        frame.add(board);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NineMenMorrisGUI();
            }
        });
    }
}
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NineMenMorrisGUI extends JPanel {
    private NineMenMorrisBoard board; // Reference to the game board

    public NineMenMorrisGUI() {
        board = new NineMenMorrisBoard(); // Initialize the game board

        // Create the graphical user interface
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 700)); // Adjust the width as needed
        leftPanel.setBackground(Color.LIGHT_GRAY);

        // Placeholder label for player 1 information
        JLabel player1Label = new JLabel("Player 1 (Black)");
        leftPanel.add(player1Label);
        JLabel player1PiecesLabel = new JLabel("No. of peices left:"+board.getBlackPieces());
        leftPanel.add(player1PiecesLabel);
        

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, 700)); // Adjust the width as needed
        rightPanel.setBackground(Color.LIGHT_GRAY);

        // Placeholder label for player 2 information
        JLabel player2Label = new JLabel("Player 2 (White)");
        rightPanel.add(player2Label);
        JLabel player2PiecesLabel = new JLabel("No. of peices left:"+board.getWhitePieces());
        rightPanel.add(player2PiecesLabel);

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                
                
                Graphics2D g2d = (Graphics2D) g;

                // Set the stroke width (line thickness)
                g2d.setStroke(new BasicStroke(4)); // Adjust the width (4 in this example)


                // Draw the game board
                int cellWidth = getWidth() / 7;
                int cellHeight = getHeight() / 7;

                int ovalSize = Math.min(cellWidth, cellHeight) - 50; // Adjust size as needed
             
                setBackground(Color.LIGHT_GRAY);
                g2d.setColor(Color.black);
//vertical lines
                g2d.drawLine(3 * cellWidth+ cellWidth / 2, cellWidth / 2, 3 * cellWidth +cellWidth / 2, 2 * cellHeight+cellHeight / 2);
                g2d.drawLine(3 * cellWidth+cellWidth / 2, 4 * cellHeight+cellHeight / 2, 3 * cellWidth+cellWidth / 2, 6 * cellHeight+cellHeight / 2);

                // Draw horizontal lines connecting the middle intersections
                g2d.drawLine( cellHeight / 2, 3 * cellHeight+ cellHeight / 2, 2 * cellWidth+ cellWidth / 2, 3 * cellHeight+ cellHeight / 2);
                g2d.drawLine(4 * cellWidth+cellWidth / 2, 3 * cellHeight+cellHeight / 2, 6 * cellWidth+cellWidth / 2, 3 * cellHeight+cellHeight / 2);

     
                
               //draw edges
              

                g2d.drawLine(cellHeight / 2, 6 * cellHeight+ cellHeight / 2, 6 * cellWidth+cellWidth / 2, 6 * cellHeight+cellHeight / 2);

    
                g2d.drawLine( cellHeight / 2, cellHeight / 2,  6 * cellWidth+cellWidth / 2,cellHeight / 2);

                
                
//draw vertical edges


                g2d.drawLine( cellHeight+ cellHeight / 2,  cellHeight+ cellHeight / 2,  5 * cellWidth+cellWidth / 2, cellHeight+ cellHeight / 2);
                 g2d.drawLine( cellHeight+ cellHeight / 2,  5*cellHeight+ cellHeight / 2,  5 * cellWidth+cellWidth / 2, 5*cellHeight+ cellHeight / 2);

                

                
                //draw horizontal edges middle verticl

                
                g2d.drawLine(1* cellWidth+ cellWidth / 2,  cellHeight+ cellHeight / 2, cellWidth+ cellWidth / 2,  5*cellHeight+ cellHeight / 2);
                //g.drawLine( cellHeight / 2, cellHeight / 2,  6 * cellWidth+cellWidth / 2,cellHeight / 2);
                g2d.drawLine(  5 * cellWidth+cellWidth / 2, cellHeight+ cellHeight / 2, 5 * cellWidth+cellWidth / 2, 5*cellHeight+ cellHeight / 2);

                
                //draw inner verticle
                
                g2d.drawLine(2* cellWidth+ cellWidth / 2,  2*cellHeight+ cellHeight / 2, 2*cellWidth+ cellWidth / 2,  4*cellHeight+ cellHeight / 2);
                g2d.drawLine(  4 * cellWidth+cellWidth / 2, 2*cellHeight+ cellHeight / 2, 4 * cellWidth+cellWidth / 2, 4*cellHeight+ cellHeight / 2);

                
                
                //draw outer verticle line
                
                g2d.drawLine(cellHeight / 2, 6 * cellHeight+ cellHeight / 2, cellHeight / 2, cellHeight / 2);

                g2d.drawLine(6 * cellWidth+cellWidth / 2,cellHeight / 2, 6 * cellWidth+cellWidth / 2, 6 * cellHeight+cellHeight / 2);
         
                
                //draw inner horizontal lines
                g2d.drawLine(2* cellWidth+ cellWidth / 2,  2*cellHeight+ cellHeight / 2, 4 * cellWidth+cellWidth / 2, 2*cellHeight+ cellHeight / 2);
                g2d.drawLine(4 * cellWidth+cellWidth / 2, 4*cellHeight+ cellHeight / 2, 2*cellWidth+ cellWidth / 2,  4*cellHeight+ cellHeight / 2);

                //g.drawLine(2* cellWidth+ cellWidth / 2,  2*cellHeight+ cellHeight / 2, 2*cellWidth+ cellWidth / 2,  4*cellHeight+ cellHeight / 2);
                //g.drawLine(  4 * cellWidth+cellWidth / 2, 2*cellHeight+ cellHeight / 2, 4 * cellWidth+cellWidth / 2, 4*cellHeight+ cellHeight / 2);

                
                
                // Draw lines to connect intersections
                g2d.setColor(Color.black);

                
            
                
                
                // Draw pieces on the board based on boardState
                for (int row = 0; row < 7; row++) {
                    for (int col = 0; col < 7; col++) {

                    	
                    	
                        int player = board.getBoardState()[row][col];
                        if (player == 1) {
                            // Draw black piece
                            ovalSize = Math.min(cellWidth, cellHeight) - 40; // Adjust size as needed

                        	
                            g.setColor(Color.BLACK);
                            g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                                    row * cellHeight + (cellHeight - ovalSize) / 2,
                                    ovalSize, ovalSize);                            } 
                        else if (player == 2) {
                            // Draw white piece
                            ovalSize = Math.min(cellWidth, cellHeight) - 40; // Adjust size as needed

                            g.setColor(Color.WHITE);
                            g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                                    row * cellHeight + (cellHeight - ovalSize) / 2,
                                    ovalSize, ovalSize);                            }
                        else {
                            // Draw markers for initial piece placement
                            ovalSize = Math.min(cellWidth, cellHeight) - 50; // Adjust size as needed

                        	if(board.isValidIntersecction(row,col)) {
                            g.setColor(Color.GRAY); // Change color as needed
                 
                            g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                                    row * cellHeight + (cellHeight - ovalSize) / 2,
                                    ovalSize, ovalSize);       
                      
                            
                            
                          //  g.setColor(Color.white); // Change color as needed

                            // Vertical lines
                            //for (int i =0 ; i < 7;) {
                            System.out.println(row+"row");
                            System.out.println(col+"col");

                               // int x = col * cellWidth + cellWidth / 2;
                               // int y1 = 1;
                                //int y2 = 7 * cellHeight;
                                //g.drawLine(x, y1, x, y2);
                          //  }

                            // Horizontal lines
                           // for (int i = 0; i < 7; i++) {
                              //  int x1 = 0;
                                //int x2 = 7 * cellWidth;
                                //int y = row * cellHeight + cellHeight / 2; ;
                                //g.drawLine(x1, y, x2, y);
                           // }
                            
                        
                        	}
                        }
                    }
                }
                
                
                
                
                
    
                
                
            }
        };

        boardPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked at row " + e);

                int mouseX = e.getX();
                int mouseY = e.getY();
                
                int cellWidth = boardPanel.getWidth() / 7;
                int cellHeight = boardPanel.getHeight() / 7;

                int clickedRow = mouseY / cellHeight;
                int clickedCol = mouseX / cellWidth;
                
                int row=clickedRow;
                int col=clickedCol;

                System.out.println("Mouse clicked at row " + row+ "row - col"+col);
                
                
                board.placePiece(row, col);
                boardPanel.repaint(); 
                player2PiecesLabel.setText("No. of pieces left"+board.getWhitePieces());
                player1PiecesLabel.setText("No. of pieces left"+board.getBlackPieces());


         
                
                // Check if the clicked position is valid for placing a piece
                /**
                if (board.isValidMove(row, col)) {
                	System.out.println(board.isValidMove(row, col));
                    board.getBoardState()[row][col] = board.getCurrentPlayer();
                    boardPanel.repaint(); // Redraw the board
                    // Decrease the count of remaining pieces for the current player
                    if (board.getCurrentPlayer() == 1) {
                        board.setBlackPieces(board.getBlackPieces() - 1);
                        player1PiecesLabel.setText("No. of pieces left"+board.getBlackPieces());
                        
                    } else {
                        board.setWhitePieces(board.getWhitePieces() - 1);
                        player2PiecesLabel.setText("No. of pieces left"+board.getWhitePieces());

                    }

           
                }
                **/
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
            frame.setSize(1000, 700); // Adjust the size as needed
            frame.add(new NineMenMorrisGUI());
            frame.setVisible(true);
        });
    }
}
