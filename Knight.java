/**
 * 
 */
package lab.labA2;

/**
 * The Kight moves two spaces any direction and then one space.
 * It can move regardless of obstacles.
 * @author Jack Shih
 * @version 2020
 */
public class Knight extends Piece {

    /**
     * The knight.
     * @param colour of the piece
     * @param board Board
     */
    public Knight(String colour, Board board) {
        super(colour, board);
    }

    /**
     * The eight possible movements of the knight are calculated.
     * @param tilePosition as int
     * @return int[] for allowed positions.
     */
    int[] howDoYouMove(int tilePosition) {
        
      int[] allowed = {
              tilePosition + 8,
              tilePosition - 8,
              tilePosition + 12,
              tilePosition - 12,
              tilePosition + 19,
              tilePosition - 19,
              tilePosition + 21,
              tilePosition - 21
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
