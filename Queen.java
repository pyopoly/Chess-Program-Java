/**
 * 
 */
package lab.labA2;

/**
 * Queen. Moves in any direction as long as way is clear of other Peices.
 * @author Jack Shih
 * @version 2020
 */
public class Queen extends Piece {

    /**
     * Queen.
     * @param colour color of the piece as String
     * @param board Board
     */
    public Queen(String colour, Board board) {
        super(colour, board);
    }

    /**
     * Moves in any direction as long as way is clear of other Peices.
     * Uses same methods from Rook and Bishop
     * @param tilePosition int for the starting position
     * @return int[] for the allowed positions
     */
    @Override
    int[] howDoYouMove(int tilePosition) {
        int[] bishop = new Bishop("", board).howDoYouMove(tilePosition);
        int[] rook = new Rook("", board).howDoYouMove(tilePosition);
        int[] result = new int[bishop.length + rook.length];
        for (int i = 0; i < bishop.length; i++) {
            result[i] = bishop[i];
        }
        
        for (int i = 0; i < rook.length; i++) {
            result[i + bishop.length] = rook[i];
        }  
        return result;
    }
}
