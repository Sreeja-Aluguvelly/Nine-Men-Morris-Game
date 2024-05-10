package GUI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import GUI.NineMenMorrisBoard.MoveRecord;

public class NineMenMorrisGUI extends JPanel {
  private NineMenMorrisBoard board;
  boolean successstatus = false;
  boolean removepiece = false;
  static String gameTypeG;
  static boolean replay=false;
  public static boolean gameVariant=false;
  static private List<MoveRecord> recordedMoves;
  static private List<MoveRecord> recordedPrevMoves;


  public NineMenMorrisGUI(String gameType) throws InterruptedException {
    if(gameVariant==true)
    {
      board = new NineMenMorrisBoard(gameType,9,9);
    }
    else{
      board = new NineMenMorrisBoard(gameType,12,12);
    }

    // To create user interface
    setLayout(new BorderLayout());
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    leftPanel.setPreferredSize(new Dimension(200, 700)); // Adjust the width as needed
    leftPanel.setBackground(Color.LIGHT_GRAY);

    // Player 1 information
    gbc.insets = new Insets(10, 10, 10, 10);
    ImageIcon imageIcon = loadImageIcon("src/images/white.png");
    JLabel label = new JLabel(imageIcon);
    leftPanel.add(label, gbc);
    JLabel player1Label = new JLabel("Player 1 (White)");
    
    if(gameType=="Human vs Computer") {
      
      
      player1Label = new JLabel("Computer (White)");

    }    
    
    
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.SOUTH;
    leftPanel.add(player1Label, gbc);
    Font newFont = new Font(player1Label.getFont().getName(), Font.BOLD, 20);
    player1Label.setFont(newFont);
    JLabel player1PiecesLabel = new JLabel("  Number Of Pieces Remaining:");
    gbc.gridy = 2;
    leftPanel.add(player1PiecesLabel, gbc);
    Font newFont2 = new Font(player1PiecesLabel.getFont().getName(), Font.BOLD, 12);
    player1PiecesLabel.setFont(newFont2);
    JLabel player1PiecesCount = new JLabel("" + board.getBlackPieces());
    gbc.gridy = 3;
    leftPanel.add(player1PiecesCount, gbc);
    Font newFont1 = new Font(player1PiecesCount.getFont().getName(), Font.BOLD, 42); // Adjust the
                                                                                     // font size as
                                                                                     // needed
    player1PiecesCount.setFont(newFont1);
    JLabel player1TurnLabel = new JLabel("Player 1 Turn");
    
    gbc.gridy = 4;
    leftPanel.add(player1TurnLabel, gbc);
    player1TurnLabel.setFont(newFont);
    JLabel player1MillFormed = new JLabel("Mill is formed");
    gbc.gridy = 5;
    leftPanel.add(player1MillFormed, gbc);
    player1MillFormed.setVisible(false);
    Font newFont4 = new Font(player1MillFormed.getFont().getName(), Font.BOLD, 16);
    player1MillFormed.setFont(newFont4);
    JLabel player1ToRemovePiece = new JLabel(" Remove opponent piece");
    gbc.gridy = 6;
    leftPanel.add(player1ToRemovePiece, gbc);
    player1ToRemovePiece.setVisible(false);
    player1ToRemovePiece.setFont(newFont4);



    // Player 2 information
    JPanel rightPanel = new JPanel();
    rightPanel.setPreferredSize(new Dimension(200, 700)); // Adjust the width as needed
    rightPanel.setBackground(Color.LIGHT_GRAY);
    rightPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc1 = new GridBagConstraints();
    gbc1.insets = new Insets(10, 10, 10, 10);
    ImageIcon imageIcon1 = loadImageIcon("src/images/black.png");
    JLabel label1 = new JLabel(imageIcon1);
    rightPanel.add(label1, gbc1);
    JLabel player2Label = new JLabel("Player 2 (Black)");

 
    
    
    
    gbc1.gridy = 1;
    gbc1.anchor = GridBagConstraints.SOUTH;
    rightPanel.add(player2Label, gbc1);
    player2Label.setFont(newFont);
    JLabel player2PiecesLabel = new JLabel("Number Of Pieces Remaining :");
    gbc1.gridy = 2;
    rightPanel.add(player2PiecesLabel, gbc1);
    player2PiecesLabel.setFont(newFont2);
    JLabel player2PiecesCount = new JLabel("" + board.getWhitePieces());
    gbc1.gridy = 3;
    rightPanel.add(player2PiecesCount, gbc1);
    player2PiecesCount.setFont(newFont1);
    JLabel player2TurnLabel = new JLabel("Player 2 Turn");
    player2TurnLabel.setVisible(false);
    player2TurnLabel.setFont(newFont);
    gbc1.gridy = 4;
    rightPanel.add(player2TurnLabel, gbc1);
    JLabel player2MillFormed = new JLabel("Mill is formed");
    gbc.gridy=5;
    rightPanel.add(player2MillFormed,gbc);
    player2MillFormed.setVisible(false);
    player2MillFormed.setFont(newFont4);
    JLabel player2ToRemovePiece = new JLabel("Remove opponent piece");
    gbc.gridy=6;
    rightPanel.add(player2ToRemovePiece,gbc);
    player2ToRemovePiece.setVisible(false);
    player2ToRemovePiece.setFont(newFont4);
    
    
    // Board Panel
    JPanel boardPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
       System.out.println("Painting");
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));

        int cellWidth = getWidth() / 7;
        int cellHeight = getHeight() / 7;
        int ovalSize = Math.min(cellWidth, cellHeight) - 50;

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
        

          if(gameVariant==false)
{
          
          System.out.println("in yes");
          g2d.drawLine(cellHeight / 2, cellHeight / 2, 2 * cellWidth + cellWidth / 2, 2 * cellHeight + cellHeight / 2);
         
          
          g2d.drawLine( 6 * cellWidth + cellWidth / 2, cellHeight / 2,
              4 * cellWidth + cellWidth / 2, 2 * cellHeight + cellHeight / 2);
          
          
          g2d.drawLine(cellHeight / 2, 6 * cellHeight + cellHeight / 2, 
              2 * cellWidth + cellWidth / 2, 4 * cellHeight + cellHeight / 2);
          
          
          
          g2d.drawLine(4 * cellWidth + cellWidth / 2, 4 * cellHeight + cellHeight / 2,
              6 * cellWidth + cellWidth / 2, 6 * cellHeight + cellHeight / 2);
          
   
        }
        
        
        
        
        
        
        
        

        g2d.setColor(Color.black);

        int[][] validIntersections = board.getHighlightedIntersections();

        // Draw pieces on the board based on boardState
        for (int row = 0; row < 7; row++) {
          for (int col = 0; col < 7; col++) {

            int player = board.getBoardState()[row][col];
            if (player == 1) {
              ovalSize = Math.min(cellWidth, cellHeight) - 40;
              g.setColor(Color.WHITE);
              g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                  row * cellHeight + (cellHeight - ovalSize) / 2, ovalSize, ovalSize);
            } else if (player == 2) {
              
              System.out.println("in repaint printing 2nd plauer only"+row+col+player);
              
              
              ovalSize = Math.min(cellWidth, cellHeight) - 40;
              g.setColor(Color.BLACK);
              g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                  row * cellHeight + (cellHeight - ovalSize) / 2, ovalSize, ovalSize);
            } else {
              ovalSize = Math.min(cellWidth, cellHeight) - 50;
              if (board.isValidIntersecction(row, col)) {
                g.setColor(Color.GRAY);
                g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                    row * cellHeight + (cellHeight - ovalSize) / 2, ovalSize, ovalSize);
              }
            }



            if (validIntersections != null && validIntersections.length > 0) {


              for (int k = 0; k < validIntersections.length; k++) {

                if (validIntersections[k][0] == row && validIntersections[k][1] == col) {


                  System.out.println("In repaint to paint ");

                  ovalSize = Math.min(cellWidth, cellHeight) - 50;

                  g.setColor(Color.yellow);
                  g.fillOval(col * cellWidth + (cellWidth - ovalSize) / 2,
                      row * cellHeight + (cellHeight - ovalSize) / 2, ovalSize, ovalSize);


                }
              }
            }
          }
        }
      }
    };
    
//    while(true)
//    {    
    
    System.out.println("here"); 
    
//    
    //add gametype

    if("Human vs Computer".equals(gameTypeG) && board.getCurrentPlayer() == 1 ){
      TimeUnit.SECONDS.sleep(2);

      computerplay( boardPanel, player1PiecesCount,  player2PiecesCount,  player1TurnLabel, player2TurnLabel, player1PiecesLabel, player2PiecesLabel );
   

    }
    
    
    
//    else {
    
    
    boardPanel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        System.out.println("In human play");

  
        int mouseX = e.getX();
        int mouseY = e.getY();

        int cellWidth = boardPanel.getWidth() / 7;
        int cellHeight = boardPanel.getHeight() / 7;

        int clickedRow = mouseY / cellHeight;
        int clickedCol = mouseX / cellWidth;

        int row = clickedRow;
        int col = clickedCol;


        if (removepiece) {
          
          
          
          int[][] intersections =  board.getHighlightedIntersections();
          boolean canremove = false;
          
          for (int i = 0; i < intersections.length; i++) {

            if (intersections[i][0] == row && intersections[i][1] == col) {
              canremove = true;
              
              break;
            }
 
          }
          
          if(canremove) {
          

          boolean succ = board.removePiece(board.getBoardState(), row, col);
       // Assuming row and col are the coordinates where the piece is placed
          board.recordMove(NineMenMorrisBoard.MoveType.REMOVING, -1, -1, row, col);

          board.setHighlightedIntersections(new int[0][0]);

          if (succ) {
            removepiece = false;
            boardPanel.repaint();
            player1MillFormed.setVisible(false);
            player1ToRemovePiece.setVisible(false);
            player2MillFormed.setVisible(false);
            player2ToRemovePiece.setVisible(false);
            if (board.gameover(board.getBoardState(), board.getCurrentPlayer())) {
              showGameOverDialog();
            }
            board.changePlayerTurn();
          }
        }

        } else {
          if (board.getBlackPieces() > 0 || board.getWhitePieces() > 0) {
            successstatus = board.placePiece(row, col);
         // Assuming row and col are the coordinates where the piece is placed
         // Assuming row and col are the coordinates where the piece is placed
            board.recordMove(NineMenMorrisBoard.MoveType.PLACING, -1, -1, row, col);

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

            player1PiecesLabel.setVisible(false);
            player2PiecesLabel.setVisible(false);
            player1PiecesCount.setText("" + board.getWhitePieces());
            player2PiecesCount.setText("" + board.getBlackPieces());
            player1PiecesCount.setVisible(false);
            player2PiecesCount.setVisible(false);
            int player = board.getBoardState()[row][col];
            if (player == board.getCurrentPlayer() && successstatus == false) {
              int[][] intersections = board.findAdjacentValidIntersections(row, col);
              board.setHighlightedIntersections(intersections);
              repaint();
              boardPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                  int mouseX2 = e.getX();
                  int mouseY2 = e.getY();

                  int clickedRow = mouseY2 / cellHeight;
                  int clickedCol = mouseX2 / cellWidth;

                  int row2 = clickedRow;
                  int col2 = clickedCol;
                 
                  int[][] intersections = board.findAdjacentValidIntersections(row, col);
                  boolean contains = false;
                  for (int i = 0; i < intersections.length; i++) {

                    if (intersections[i][0] == row2 && intersections[i][1] == col2) {
                      contains = true;
                      break;
                    }
                  }

                  if (contains) {
                    successstatus = board.movePiece(row, col, row2, col2);
                    board.recordMove(NineMenMorrisBoard.MoveType.MOVING, row, col, row2, col2);

                    board.setHighlightedIntersections(new int[0][0]);

                  }

                  boardPanel.removeMouseListener(this);
                }
              });
            }
          }
        }
        if (successstatus) {
          boolean millformed=false;
          if(gameVariant==true)
          {
          millformed =board.millformed( board.getBoardState(),row,col,board.getCurrentPlayer()); 
          }
          else
          {
            millformed =board.millformed1( board.getBoardState(),row,col,board.getCurrentPlayer()); 
          }

          if (millformed) {
            board.setHighlightedIntersections(board.getplayerpieces(board.getBoardState(),
                board.getCurrentPlayer() == 1 ? 2 : 1,gameVariant));
            removepiece = true;
            if(board.getCurrentPlayer()==1)
            {
            player1MillFormed.setVisible(true);
             player1ToRemovePiece.setVisible(true);
            }
            else
            {
             player2MillFormed.setVisible(true);
             player2ToRemovePiece.setVisible(true);
            }
          }
          else {
            if (board.gameover(board.getBoardState(), board.getCurrentPlayer())) {
              //System.out.println("Game over");
              if(board.countPieces()==24)
              {
               showGameDrawDialog();
              }
              else{
               showGameOverDialog();
              }
            }
            board.changePlayerTurn();
          }
          boardPanel.repaint();
          successstatus = false;
        }
        if (board.getCurrentPlayer() == 2) {
          player1TurnLabel.setVisible(false);
          player2TurnLabel.setVisible(true);

        } else {
          player1TurnLabel.setVisible(true);
          player2TurnLabel.setVisible(false);

        }
        if("Human vs Computer".equals(gameTypeG) && board.getCurrentPlayer() == 1 ){
          try {
            computerplay( boardPanel, player1PiecesCount,  player2PiecesCount,  player1TurnLabel, player2TurnLabel, player1PiecesLabel, player2PiecesLabel );
          } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
       

        }
        
      }
    });
    
    JPanel bottomPanel = createBottomPanel();
    bottomPanel.setBackground(Color.LIGHT_GRAY);
    setLayout(new BorderLayout());
    add(leftPanel, BorderLayout.WEST);
    add(rightPanel, BorderLayout.EAST);
    add(boardPanel, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);
    bottomPanel.setVisible(false);
    System.out.println("Replay is" +replay);
    if(replay==true)
    {
      bottomPanel.setVisible(true);
    }
  }
  
  public JPanel createBottomPanel() {
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JButton previousButton = new JButton(">>Previous");
    JButton nextButton = new JButton("Next<<");
//    JButton button1 = new JButton("1 Sec");
//    JButton button2 = new JButton("2 Sec");
//    JButton button3 = new JButton("3 Sec");
    Dimension buttonSize = new Dimension(150, 50);
    previousButton.setPreferredSize(buttonSize);
    nextButton.setPreferredSize(buttonSize);
//    button1.setPreferredSize(buttonSize);
//    button2.setPreferredSize(buttonSize);
//    button3.setPreferredSize(buttonSize);
    Font buttonFont = new Font(previousButton.getFont().getName(), Font.PLAIN, 18);
    previousButton.setFont(buttonFont);
    nextButton.setFont(buttonFont);
//    button1.setFont(buttonFont);
//    button2.setFont(buttonFont);
//    button3.setFont(buttonFont);
    previousButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
          previousButtonClicked();
      }
    });

  nextButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
          nextButtonClicked();
      }
  });
//
//  button1.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(java.awt.event.ActionEvent e) {
//          button1Clicked();
//      }
//  });
//  button3.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(java.awt.event.ActionEvent e) {
//          button2Clicked();
//      }
//    });
//      button3.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(java.awt.event.ActionEvent e) {
//          button3Clicked();
//      }
//    });
    bottomPanel.add(previousButton);
    bottomPanel.add(nextButton);
//    bottomPanel.add(button1);
//    bottomPanel.add(button2);
//    bottomPanel.add(button3);
    //bottomPanel.setVisible(false);
    return bottomPanel;
}

//  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Morris Game");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 700);
      //JLabel label = new JLabel("Select Play Type");
      JButton nineMenMorrisButton = new JButton("Nine Men Morris");
      Font font = new Font(nineMenMorrisButton.getFont().getName(), Font.PLAIN, 18);
        JButton twelveMenMorrisButton = new JButton("Twelve Men Morris");
        // JButton humanVsHumanButton = new JButton("Human vs Human");
        // JButton humanVsComputerButton = new JButton("Human vs Computer");
        //Font font = new Font(humanVsHumanButton.getFont().getName(), Font.PLAIN, 18);
        nineMenMorrisButton.setFont(font);
        twelveMenMorrisButton.setFont(font);
        // humanVsHumanButton.setFont(font);
        // humanVsComputerButton.setFont(font);
        //Font newFont = new Font(label.getFont().getName(), Font.BOLD, 30);
        nineMenMorrisButton.addActionListener(e ->
        { 
          gameVariant=true;
          startGame1("Nine Men Morris");
      });
        twelveMenMorrisButton.addActionListener(e -> startGame1("Twelve Men Morris"));
        // humanVsHumanButton.addActionListener(e -> startGame("Human vs Human"));
        // humanVsComputerButton.addActionListener(e -> startGame("Human vs Computer"));
        frame.setLayout(new GridBagLayout());
        // humanVsHumanButton.setPreferredSize(new Dimension(250, 150));
        // humanVsComputerButton.setPreferredSize(new Dimension(250, 150));
        nineMenMorrisButton.setPreferredSize(new Dimension(250, 150));
        twelveMenMorrisButton.setPreferredSize(new Dimension(250, 150));
        frame.setVisible(true);
       // frame.add(label);
        //label.setFont(newFont);
        // frame.add(humanVsHumanButton);
        // frame.add(humanVsComputerButton);
        frame.add(nineMenMorrisButton);
        frame.add(twelveMenMorrisButton);
    });
  }

  public ImageIcon loadImageIcon(String imagePath) {
    try {
      File imageFile = new File(imagePath);
      BufferedImage image = ImageIO.read(imageFile);
      return new ImageIcon(image);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void resetBoard(String gameType)
  {
    //System.out.println("Inside reset board");
    gameTypeG=gameType;
    recordedMoves=board.getRecordedMoves();
    recordedPrevMoves=board.getRecordedPrevMoves();

    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Morris Game");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 700);
      System.out.println("Replay inside reset"+replay);
      try {
        frame.add(new NineMenMorrisGUI(gameType));
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      frame.setVisible(true);
    });
  
  }
  public static void startGame1(String gameVariant) {

    JOptionPane.showMessageDialog(null, "Starting " + gameVariant + " game!");
    //showCustomDialog(gameType);
    SwingUtilities.invokeLater(() -> {
      //showCustomDialog(gameType);
      JFrame frame = new JFrame("Morris Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 700);
       //JButton nineMenMorrisButton = new JButton("Nine Men Morris");
       JButton humanVsHumanButton = new JButton("Human vs Human");
       Font font = new Font(humanVsHumanButton.getFont().getName(), Font.PLAIN, 18);
        JButton humanVsComputerButton = new JButton("Human vs Computer");
        humanVsHumanButton.setFont(font);
        humanVsComputerButton.setFont(font);
        humanVsHumanButton.addActionListener(e -> 
        {
          startGame("Human vs Human");
        });
        humanVsComputerButton.addActionListener(e -> startGame("Human vs Computer"));
        frame.setLayout(new GridBagLayout());
        humanVsHumanButton.setPreferredSize(new Dimension(250, 150));
        humanVsComputerButton.setPreferredSize(new Dimension(250, 150));
        frame.setVisible(true);
       // frame.add(label);
        //label.setFont(newFont);
        frame.add(humanVsHumanButton);
        frame.add(humanVsComputerButton);
      frame.setVisible(true);
    });
    // Add logic to start the game based on the selected type
}
  
  public static void startGame(String gameType) {
    JOptionPane.showMessageDialog(null, "Starting " + gameType + " game!");
    gameTypeG=gameType;
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Morris Game");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 700);
      
      try {
        frame.add(new NineMenMorrisGUI(gameType));
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      frame.setVisible(true);
    });
    // Add logic to start the game based on the selected type
}
  public void showGameDrawDialog() {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Game is a draw");
    JButton okButton = new JButton("OK");
    JButton customButton = new JButton("Replay");
          
    okButton.addActionListener(e ->{
    resetBoard(gameTypeG);
    JOptionPane.getRootFrame().dispose();
  });
    customButton.addActionListener(e -> {
        // Handle custom button action here
        replay=true;
        resetBoard(gameTypeG);
        // JPanel bottomPanel = new JPanel();
        // bottomPanel=createBottomPanel();
        // add(bottomPanel, BorderLayout.SOUTH);
        // bottomPanel.setBackground(Color.LIGHT_GRAY);
        // bottomPanel.setVisible(true);
       // System.out.println("Custom button clicked!");
        JOptionPane.getRootFrame().dispose();
    });

    panel.add(label);
    panel.add(okButton);
    panel.add(customButton);
    JOptionPane.showOptionDialog(
      null,
      panel,
      "Game Over",
      JOptionPane.DEFAULT_OPTION,
      JOptionPane.INFORMATION_MESSAGE,
      null,
      new Object[]{},
      null
  );
      }
  public void showGameOverDialog() {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Player " + board.getCurrentPlayer() + " wins!");
    JButton okButton = new JButton("OK");
    JButton customButton = new JButton("Replay");
          
    okButton.addActionListener(e ->{
    resetBoard(gameTypeG);
    JOptionPane.getRootFrame().dispose();
  });
    customButton.addActionListener(e -> {
        // Handle custom button action here
        replay=true;
        resetBoard(gameTypeG);
        // JPanel bottomPanel = new JPanel();
        // bottomPanel=createBottomPanel();
        // add(bottomPanel, BorderLayout.SOUTH);
        // bottomPanel.setBackground(Color.LIGHT_GRAY);
        // bottomPanel.setVisible(true);
       // System.out.println("Custom button clicked!");
        JOptionPane.getRootFrame().dispose();
    });

    panel.add(label);
    panel.add(okButton);
    panel.add(customButton);
    JOptionPane.showOptionDialog(
      null,
      panel,
      "Game Over",
      JOptionPane.DEFAULT_OPTION,
      JOptionPane.INFORMATION_MESSAGE,
      null,
      new Object[]{},
      null
  );
      }
      private  void previousButtonClicked() {
        board.prevClickOnReplay(recordedPrevMoves);
        repaint();
        
        
        // Define the action to be taken when Button 1 is clicked
        //System.out.println("Previous Button clicked!");
    }
  public  void nextButtonClicked() {
    
    board.nextClickOnReplay(recordedMoves);
    System.out.println("calling paint");
    repaint();
    System.out.println("calling paint done");

        // Define the action to be taken when Button 1 is clicked
        //System.out.println("Next Button clicked!");
    }

      private  void button1Clicked() {
        
        while(true) {
          try {
            TimeUnit.SECONDS.sleep(5);
            nextButtonClicked();

          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          
        }
        
        
        // Define the action to be taken when Button 1 is clicked
        //System.out.println("Button 1 clicked!");
    }
    
  public void removepiecefromGUI(JPanel boardPanel,int row,int col)
  {
    System.out.println("in remove from gui");
    int[][] intersections =  board.getHighlightedIntersections();
    System.out.println("Intersections:");
    for (int i = 0; i < intersections.length; i++) {
        System.out.print("{");
        for (int j = 0; j < intersections[i].length; j++) {
            System.out.print(intersections[i][j]);
            if (j < intersections[i].length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("}");
        if (i < intersections.length - 1) {
            System.out.println();
        }
    }
    boolean canremove = false;
    
    for (int i = 0; i < intersections.length; i++) {

      if (intersections[i][0] == row && intersections[i][1] == col) {
        canremove = true;
        
        break;
      }

    }
    
    if(canremove) {
    

    boolean succ = board.removePiece(board.getBoardState(), row, col);
    System.out.println("removed");
    board.recordMove(NineMenMorrisBoard.MoveType.REMOVING, -1, -1, row, col);


    board.setHighlightedIntersections(new int[0][0]);

    if (succ) {
      removepiece = false;
      boardPanel.repaint();
      if (board.gameover(board.getBoardState(), board.getCurrentPlayer())) {
        JOptionPane.showMessageDialog(null, "Player " + board.getCurrentPlayer() + " wins!",
            "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetBoard(gameTypeG);
      }
      board.changePlayerTurn();
    }
  }
    
  }
  
  
  
  public void computerplay(JPanel boardPanel,JLabel player1PiecesCount, JLabel player2PiecesCount, JLabel player1TurnLabel,JLabel player2TurnLabel,JLabel player1PiecesLabel,JLabel player2PiecesLabel ) throws InterruptedException {
    
    System.out.println("In Computer play");    


    
    
    // Assign the values to two separate integers
    int row =0;
    int col =0;
    
    
    System.out.println("In inhere play");    

    if (removepiece) {
      
      int[] randomToRemoveIntersection= board.getRandomElement(board.getplayerpieces(board.getBoardState(),
          board.getCurrentPlayer() == 1 ? 2 : 1,gameVariant));
      int row3 = randomToRemoveIntersection[0];
      int col3 = randomToRemoveIntersection[1];
      removepiecefromGUI(boardPanel,row3,col3);
      

    } else {
      System.out.println("In do not remove piece");    

     

      if (board.getBlackPieces() > 0 || board.getWhitePieces() > 0) 
      {
        
        int[] randomValidIntersection1=board.makeStrategicMove(board.getBoardState());
        row = randomValidIntersection1[0];
        col = randomValidIntersection1[1];
        System.out.println("Got strategic move to bloc"+ row+col);    

        if(row==-1&&col==-1)
        {
          
          int[] randomValidIntersection2=board.makeStrategicMoveToPlace(board.getBoardState());
          row = randomValidIntersection2[0];
          col = randomValidIntersection2[1];
          System.out.println("Got strategic move to place"+ row+col);   
          
          if(row==-1&&col==-1)
          {
            
            
            
            int[] randomValidIntersection = board.findRandomValidIntersection();

             row = randomValidIntersection[0];
             col = randomValidIntersection[1];
             System.out.println("Got not  strategic move");    
            
          }
          
          
          
          
          
          


        }
        System.out.println("In computer placing piece");    

        successstatus = board.placePiece(row, col);
        board.recordMove(NineMenMorrisBoard.MoveType.PLACING, -1, -1, row, col);

     // Assuming row and col are the coordinates where the piece is placed
     // Assuming row and col are the coordinates where the piece is placed

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
        
        
        int[] randomComputerPieces = board.getRandomElementToMove();

        int row2 = randomComputerPieces[0];
      int   col2 = randomComputerPieces[1];
        
        
        
        System.out.println("in moving else  row: "+row2+"  col:  "+ col2);

        player1PiecesLabel.setVisible(false);
        player2PiecesLabel.setVisible(false);
        player1PiecesCount.setText("" + board.getWhitePieces());
        player2PiecesCount.setText("" + board.getBlackPieces());
        player1PiecesCount.setVisible(false);
        player2PiecesCount.setVisible(false);
        int player = board.getBoardState()[row2][col2];
        if (player == board.getCurrentPlayer() && successstatus == false) {
          int[][] intersections = board.findAdjacentValidIntersections(row2, col2);
          board.setHighlightedIntersections(intersections);
          repaint();

          
          
          int[] randomToMoveIntersection= board.getRandomElement(intersections);
           row = randomToMoveIntersection[0];
           col = randomToMoveIntersection[1];
          
          System.out.println("in moving else  row: "+row+"  col:  "+ col);
          

          
//          if(board.getWhitePieces()<3) {
//            List<int[]> playerPieces = new ArrayList<>();
//
//            
//            
//            for (int i = 0; row < 7; row++) {
//              for (int j = 0; col < 7; col++) {
//                if (board.getBoardState()[i][j] == 1) {
//                  playerPieces.add(new int[] {i, j});
//                }
//              }
//            }
//            int array[][]= playerPieces.toArray(new int[0][0]);
//
//            
//            
//            
//            for(int i=0;i<3;i++) {
//              if(board.isValidMove(array[i][0],array[i][0]))
//              {
//                System.out.println("is valid move");
//                board.getBoardState()[array[i][0]][array[i][1]]=1;
//                if(board.millformed(board.getBoardState(),array[i][0],array[i][1],1))
//                {
//                  System.out.println("is valid move");
//
//                  board.getBoardState()[array[i][0]][array[i][1]]=0;
//                  row=array[i][0];
//                  col=array[i][1];
//                }
//                board.getBoardState()[row][col]=0;
//
//
//              }
//              
//              
//              
//              
//            }
//            
//            
//            
//          }
          
          
          
          
          
          
          
          
          
          
 
          boolean contains = false;
          for (int i = 0; i < intersections.length; i++) {

            if (intersections[i][0] == row && intersections[i][1] == col) {
              contains = true;
              break;
            }
          }

          if (contains) {

            successstatus = board.movePiece(row2, col2, row, col);
            board.recordMove(NineMenMorrisBoard.MoveType.MOVING, row2, col2, row, col);
            board.setHighlightedIntersections(new int[0][0]);

          }

    
          
        }
      }
    }
    System.out.println("Printing success states");

    if (successstatus) {
      boolean millformed =
          board.millformed(board.getBoardState(), row, col, board.getCurrentPlayer());

      if (millformed) {
        board.setHighlightedIntersections(board.getplayerpieces(board.getBoardState(),
            board.getCurrentPlayer() == 1 ? 2 : 1,gameVariant));
        removepiece = true;
        System.out.println("In computer removing piece");    

        
        int[] randomToRemoveIntersection= board.getRandomElement(board.getplayerpieces(board.getBoardState(),
            board.getCurrentPlayer() == 1 ? 2 : 1,gameVariant));
        int row3 = randomToRemoveIntersection[0];
        int col3 = randomToRemoveIntersection[1];
        removepiecefromGUI(boardPanel,row3,col3);
        
      }
      else {
System.out.println("In mill not formed ");
  if (board.gameover(board.getBoardState(), board.getCurrentPlayer())) {
          JOptionPane.showMessageDialog(null, "Player " + board.getCurrentPlayer() + " wins!",
              "Game Over", JOptionPane.INFORMATION_MESSAGE);

          resetBoard(gameTypeG);
        }
        board.changePlayerTurn();
      }
      repaint();
      successstatus = false;
    }
    if (board.getCurrentPlayer() == 2) {
      player1TurnLabel.setVisible(false);
      player2TurnLabel.setVisible(true);

    } else {
      player1TurnLabel.setVisible(true);
      player2TurnLabel.setVisible(false);

    
    

    
    
    
  }
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}


