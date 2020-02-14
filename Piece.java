package lab.labA2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Piece for the chess program.
 * @author Jack Shih
 * @version 2020
 */
public abstract class Piece {

    
    /** Colour of the Piece. */
    private String colour;
    /** Board. */
    protected Board board;
    
    /**
     * Constructor. Takes in the name and colour of the Piece.
     * These are used to locate the png file with the same name and colour.
     * @param colour String
     * @param board Board
     */
    public Piece(String colour, Board board) {
        this.colour = colour;
        this.board = board;
    }
    
    /**
     * Method to be defined by different child Pieces.
     * @param tilePosition the starting tile position
     * @return int[] of all allowed positions
     */
    abstract int[] howDoYouMove(int tilePosition);
    
    /**
     * Sets the piece on Tiles by finding the image with the 
     * same name and colour.
     * @return ImageView to be used by javafx Button
     * @throws FileNotFoundException if file not found
     */
    ImageView setPiece() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/lab/labA2/img/" + whoAreYou() + colour + ".png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        //40 is to change the size of the image, smaller than the tile size(50).
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        return imageView;
    }
    
    /**
     * Colour of the Piece.
     * @return String
     */
    public String getColour() {
        return colour;
    }
    
    /**
     * Extracts the Position(column and row) of the Tile.
     * Position is calculated by concatenation of column and row.
     * column 2, row 4 = 24.
     * @param tilePosition position of the Tile
     * @return int[] of the coloumn(index 0), and row(index 1)
     */
    int[] extractPosition(int tilePosition) {
        //Uses ten to separate the digit
        final int ten = 10;
        int column = tilePosition / ten;
        int row = tilePosition % ten;
        int[] position = {column, row};
        return position;
    }
    
    /**
     * Checks if the tile position is insides the bounds of the Board.
     * 8x8 Board is (0 - 7) and (0 - 7)
     * @param tilePosition as an int.
     * @return boolean true if the position is in-bounds
     */
    boolean inBounds(int tilePosition) {
        // 7 because thats the maximum index on 8 x 8 board.
        final int seven = 7;
        int column = extractPosition(tilePosition)[0];
        int row = extractPosition(tilePosition)[1];
        if (row <= seven && column <= seven && tilePosition >= 0)
            return true;
        return false;
    }
    
    /**
     * Calculates the allowed movement of the Piece, by using it's 
     * starting position, and it's movement characteristic(different for 
     * each Piece). The result can be repeatedly passed back into the method to
     * account for multiple directions the Piece may want to move
     * The calculation loop breaks, if an obstacle is detected, or
     * if the movement is out-of-bounds.
     * @param allowed int[] to store the allowed positions
     * @param tilePosition int for starting position
     * @param movement int for the behaviour of the specific Piece
     * @return int[] for added allowed positions
     */
    int[] movement(int[] allowed, int tilePosition, int movement) {
        //7 because that is the maximum movements on a 8x8 Board.
        final int seven = 7; 
        int[] temp = new int[seven];
        int count = 0;
        for (int i = 0; i < seven; i++) {
            int move = tilePosition + movement * (i + 1);
            if (inBounds(move)) {
                temp[i] = move;
                count++;
                if (board.getTileAt(move).getPiece() != null) {
                    break; 
                }                 
            } else {
                break;
            }       
        }
        //The two loops below concatenates the original Array
        //with the new Array into a result Array
        int[] result = new int[count + allowed.length];
        int index = 0;
        for (int i : allowed) {
            result[index] = i;
            index++;
        }
        for (int x = 0; x < count; x++) {
            result[index] = temp[x];
            index++;
        }
        return result;        
    }
    
    /**
     * Get the Name (identity) of the Piece.
     * @return String
     */
    public String whoAreYou() {
        return this.getClass().getSimpleName();
    }
    
    /**
     * toString.
     * @return String
     */
    public String toString() {
        return "piece is: " + whoAreYou() + "  And Colour is: " + colour;
    }  
    
}
