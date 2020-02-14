package lab.labA2;

/**
 * Bishop. Moves diagonally as long as way is clear of Pieces.
 * @author Jack Shih
 * @version 2020
 */
public class Bishop extends Piece {
    
    /**
     * Bishop. 
     * @param colour of the Bishop as a String
     * @param board Board
     */
    public Bishop(String colour, Board board) {
        super(colour, board);
    }

    /**
     * Moves diagonally as long as long is clear of Pieces.
     * +-9 and +- 11 is the movement relative to the starting position.
     * @param tilePosition starting position as an int
     * @return int[] for the allowed positions
     */
    @Override
    int[] howDoYouMove(int tilePosition) {
        //+-9 and +- 11 is the movement relative to the starting position.
        final int nine = 9;
        final int eleven = 11;
        int[] allowed = new int[0];
        allowed = movement(allowed, tilePosition, nine);
        allowed = movement(allowed, tilePosition, -nine);
        allowed = movement(allowed, tilePosition, eleven);
        allowed = movement(allowed, tilePosition, -eleven);       
        return allowed;
    }
    


}
