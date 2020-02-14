/**
 * 
 */
package lab.labA2;


/**
 * Rook Piece. Moves vertically or horizontally unlimitedly
 * as long as path is clear of other Pieces.
 * @author Jack Shih
 * @version 2020
 */
public class Rook extends Piece {

    /**
     * Constructor. colour corresponds to player colour.
     * @param colour String, White or Black
     * @param board Board
     */
    public Rook(String colour, Board board) {
        super(colour, board);
    }


    /**
     * Rook moves horizontally (column +1, -1) which is +10, -10.
     * And vertically (row +1, -1) which is +1, -1.
     * @param tilePosition starting tile position of the Piece rook
     * @return int[] of all allowed positions
     */
    int[] howDoYouMove(int tilePosition) {
        int[] result = new int[0];
        result = movement(result, tilePosition,  1);
        result = movement(result, tilePosition,  -1);
        result = movement(result, tilePosition,  10);
        result = movement(result, tilePosition, -10);
        return result;
    }

}
