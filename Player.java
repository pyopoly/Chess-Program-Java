package lab.labA2;

/**
 * A Player for the chess program. Player is given a colour that
 * corresponds to the chess piece's colour. Only the piece of same colour
 * can be moved.
 * @author Jack Shih
 * @version 2020
 */
public class Player {
    /** Colour of the player. */
    private String playerColour;
    /** First move. */
    private boolean firstMove;
    
    /**
     * Constructor for Player. Colour of Player determines which
     * chess piece can be moved.
     * @param colour as a String
     */
    public Player(String colour) {
        playerColour = colour;
        firstMove = true;
    }
    
    /**
     * Returns the colour of the Player.
     * @return Player colour as String
     */
    public String getColour() {
        return playerColour;
    }

    /**
     * Checks if it's the Player's first move.
     * @return boolean
     */
    boolean firstMove() {
        return firstMove;
    }
    
    /**
     * First move is over.
     */
    void notFirstMove() {
        firstMove = false;
    }
    
    /**
     * A toString.
     * @return Returns the player colour.
     */
    public String toString() {
        return "The player is: " + playerColour;
    }
}
