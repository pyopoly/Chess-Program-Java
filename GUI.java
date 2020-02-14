/**
 * 
 */
package lab.labA2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;

/**
 * GUI for chess program.
 * @author Jack Shih
 * @version 2020
 */
public class GUI extends Application implements UI{
    
    /** The board to be interacting with other classes. */
    private static Board board;
    /** The stage is made available for displayMsg(). */
    private static Stage stage;
    
    /**
     * Sets the stage.
     */
    public void start(Stage stage) throws Exception {    
        GUI.stage = stage;
        GridPane gridPane = new GridPane();             
        gridPane.setAlignment(Pos.CENTER); 
                         
        gridPane.add(new ChessPane(), 0, 1);             
        Scene scene = new Scene(gridPane, 400, 400);
        stage.setTitle("Play Chess");             
        stage.setScene(scene);             
        stage.setResizable(false);             
        stage.show();
    }        
    
    /**
     * ChessPane as a GridPane.
     */
    public class ChessPane extends GridPane {        
        
        /**
         * ChessPane that sets everything into javafx.
         * @throws FileNotFoundException
         */
        public ChessPane() throws FileNotFoundException {
            //sets the tiles on the board
            board.setTile();           
            //sets all the pieces with the colours and the rows.
            //the numbers here indicate the row where the pieces are.
            board.setPieces("black", 0);
            board.setPawn("black", 1);
            board.setPawn("white", 6);
            board.setPieces("white", 7);
            
            setBoard(this);
            displayMsg("Game start: " + board.getCurrPlayer().getColour() + " player's turn");
           
                       
        }       
    }   
    
    /**
     * Sets the Tiles(javafx Buttons) onto the GridPane.
     * @param chesspane ChessPane
     */
    public void setBoard(ChessPane chesspane) {
        int row = board.getBoard().length;
        int column = board.getBoard().length;                  
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                chesspane.add(board.getBoard()[c][r], c, r);
            }
        } 
    }
    
    
    /**
     * Run the GUI. Passes the Board to be used with other classes.
     * @param chessBoard a Board.
     */
    public void run(Board chessBoard) {
        board = chessBoard;
        launch();
    }

    /**
     * Writes the msg to the Title of the Window
     * and also the console.
     * @param msg as String
     */
    public void displayMsg(String msg) {
        System.out.println(msg);
        stage.setTitle(msg);
    }

    /**
     * Writes the Error msg to the Title of the Window
     * and also the console.
     * @param msg as String
     */
    public void displayErrorMsg(String msg) {
        System.out.println(msg);
        stage.setTitle(msg);
    }

}
