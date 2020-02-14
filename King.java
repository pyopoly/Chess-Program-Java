/**
 * 
 */
package lab.labA2;

/**
 * The King. Moves in any direction one time.
 * @author Jack Shih
 * @version 2020
 */
public class King extends Piece {

    /**
     * The king.
     * @param colour as String, black or white
     * @param board Board
     */
    public King(String colour, Board board) {
        super(colour, board);
    }

    /**
     * Calculates all possible positions.
     * @param tilePosition as int for starting position
     * @return int[] for allowed positions
     */
    int[] howDoYouMove(int tilePosition) {
        int[] allowed = {
                tilePosition + 1,
                tilePosition - 1,
                tilePosition + 9,
                tilePosition - 9,
                tilePosition + 10,
                tilePosition - 10,
                tilePosition + 11,
                tilePosition - 11
        };     

      //Calculates the number of allowed movements.   
        int count = 0;
        for (int x: allowed)
            if (inBounds(x))
                count++;      
        
         //Checks if the calculated move is out of bounds
        int[] result = new int[count];
        for (int x: allowed)
            if (inBounds(x))
                result[--count] = x; 
                 
        return result;
    }
}
