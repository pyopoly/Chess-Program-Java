package lab.labA2;

//import javafx.application.Application;
import javafx.scene.control.Button;
import java.io.FileNotFoundException;

/**
 * Tiles on the Board.
 * @author Jack Shih
 * @version 2020
 */
public class Tile extends Button {
    /** The board the Tile is on. */
    private Board board;
    /** The row of the Tile.*/
    private int row;
    /** The coloumn of the Tile.*/
    private int column;
    /** Is there a piece on the Tile. */
    private boolean pieceHere;
    /** To initialize the Piece. */
    private Piece piece;
    /** The position of the Tile on the Board. */
    private int position;
    /** Colour of the Tile. */
    private String colour;
    
    /**
     * Construct a Tile on a Board. 
     * Javafx uses the colour to determine tile's colour.
     * @param board Board
     * @param colour String
     * @param row int
     * @param column column
     */
    Tile(Board board, String colour, int row, int column) {        
        this.row = row;
        this.column = column;
        this.board = board;
        this.colour = colour;
        position = Integer.parseInt(column + "" + row);
        style(colour);
        mouseClick();
    }

    /**
     * Sets the colour of Tile by Javafx.
     * @param tileColour String
     */
    private void style(String tileColour) {
        this.setStyle("-fx-background-color: " + tileColour + ";");
        this.setMinWidth(50);
        this.setMaxWidth(50);
        this.setMinHeight(50);
        this.setMaxHeight(50);
    }
    
    /**
     * Sets a Piece on the Tile.
     * @param piece Piece
     * @throws FileNotFoundException no File
     */
    public void setPiece(Piece piece) throws FileNotFoundException {
        this.piece = piece;
        this.setGraphic(piece.setPiece());
        pieceHere = true;
    }
    
    /**
     * Get the Piece on the Tile.
     * @return Piece
     */
    public Piece getPiece() {
        return piece;
    }
    
    /**
     * Checks if there is a Piece.
     * @return boolean
     */
    public boolean emptyTile() {
        return !pieceHere;
    }
    
    /**
     * Setter for boolean pieceHere.
     * @param value true is there is a Piece on the Tile.
     */
    public void pieceOnTile(boolean value) {
        pieceHere = value;
    }
    /**
     * mouseClick event.
     */
    private void mouseClick() {      
        this.setOnAction((event) -> {
            System.out.println(this);
          
            boolean playerAllowed = false;            
            if (pieceHere) {
                playerAllowed = board.whoseTurn().getColour().equalsIgnoreCase(piece.getColour());
            }
            
            //PlayerAllowed is true if playColour matches piece colour 
            //Or if the Tile clicked is empty, then move()
            if (playerAllowed || emptyTile())
                try {                    
                    move();
                } catch (FileNotFoundException e) {                
                    e.printStackTrace();
                    }            
        });  
    }      
    
    
    /**
     * Move the pieces.
     * @param board Board obj with the Pieces.
     * @param tile Tile obj with the Piece.
     */
    void move() throws FileNotFoundException{   
        //Checks if it's the firstclick
        //Then checks if the Tile is empty.
        //We also set the FirstClick boolean to false so next click will be second click
        if (board.getFirstClick()) {          
            if (!emptyTile()) {
                selectPiece();
                board.setFirstClick(false);   
                }
            //If this is the second click.
            //We check if the second Tile is empty.
            //We move the Piece if Tile is empty.
            //Else we select the clicked piece again
        } else {  
            if (emptyTile()) {
                if (isLegalMove(board.getPiece(), position)) {
                    board.setFirstClick(true); 
                    setPiece(board.getPiece());
                    removePiece(board.getPreviousTile());    
                    board.switchPlayer(); 
                    resetTileColour();
                }                                 
            } else {
                resetTileColour();
                selectPiece();
            }
        }
    }    

    /**
     * Checks if the new position is allowed. By verifying it with the
     * list of allowed move according to the specific Piece.
     * @param piece Piece
     * @param newPosition int
     * @return boolean true if move is allowed
     */
    public boolean isLegalMove(Piece piece, int newPosition) {
        int[] allowed = piece.howDoYouMove(board.getPreviousTile().getPosition());
        for (int x: allowed)  
            if (x == newPosition) 
                return true;
        return false;
    }
        
    /**
     * If there is a Piece on the Tile. Select the piece.
     * (Stores the piece in Board with savePiece())
     * Then memorize the clicked Tile so we can remove the piece if needed.
     */
    public void selectPiece() {
        board.savePiece(getPiece());
        board.savePreviousTile(this);       
        this.setStyle("-fx-background-color: CornFlowerBlue;");
    }
    
    /**
     * Removes the Piece on the Tile.
     * And empties the boolean for tile.pieceHere
     * @param tile Tile
     */
    public void removePiece(Tile tile) {
        tile.setGraphic(null);
        tile.pieceOnTile(false);
        tile.setPieceNull();
    }
    
    /**
     * Highlight Possible movements.
     * Not implemented yet.
     */
    public void highlightMove() {     
        int[] allowedPos = this.getPiece().howDoYouMove(this.getPosition());
        for (int x : allowedPos) {
            Tile tile = board.getTileAt(x);
            tile.setStyle("-fx-background-color: Red;");
        }
    }
    
    /**
     * Return all Tiles to their original colour.
     */
    private void resetTileColour() {
        Tile preTile = board.getPreviousTile();
        preTile.setStyle("-fx-background-color: " + preTile.getColour() + ";");
        //Code below is not implemented yet.
//        int[] allowedPos = this.getPiece().howDoYouMove(preTile.getPosition());
//        for (int x : allowedPos) {
//            Tile tile = board.getTileAt(x);
//            tile.setStyle("-fx-background-color: " + tile.getColour() + ";");
//        }
    }
    
    /**
     * Get original colour of the Tile.
     * @return colour as a String
     */
    String getColour() {
        return colour;
    }
    
    /**
     * Sets the Piece on the Tile to be null.
     * (Removing the piece)
     */
    void setPieceNull() {
        this.piece = null;
    }
    
    /**
     * Get the position of the Tile.
     * @return int as position
     */
    public int getPosition() {
        return position;
    }
    
    /**
     * Gets the row of the Tile.
     * @return int
     */
    public int getRow() {
        return row;
    }
    
    /**
     * gets the column of the Tile.
     * @return int
     */
    public int getColumn() {
        return column;
    }
      
    /**
     * toString for the position of Tile.
     * @return String 
     */
    public String toString() {
        return "Tile at: [" + column + "][" + row + "]";
    }

}
