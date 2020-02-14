/**
 * 
 */
package lab.labA2;

/**
 * The pawn. Moves two spaces if it's the player first turn. one space 
 * afterwards. Can only move forward. Attacks diagonally.
 * @author Jack Shih
 * @version 2020
 */
public class Pawn extends Piece {

    /** Colour of the Pawn. */
    private String colour;
    /** The Player the pawn belongs to. */
    private Player player;

    /**
     * Pawn needs the Player to determine if it is the Player's first move.
     * Colour needs to be the same as the Player.
     * @param colour as String, black or white
     * @param player black or white
     * @param board Board
     */
    public Pawn(String colour, Player player, Board board) {
        super(colour, board);
        this.colour = colour;
        this.player = player;
    }

    /**
     * If it's player's first move, then move two spaces forward.
     * Else one space only. White is at the bottom of the Board
     * so it can only up (tile position decreses).
     * @param tilePosition int
     * @return int[] for the allowed positions
     */
    int[] howDoYouMove(int tilePosition) {
        boolean firstMove = player.firstMove();
        boolean white = colour.equalsIgnoreCase("white");
        int[] allowed = (firstMove) ? new int[2] : new int[1];        
        allowed[0] = (white) ? --tilePosition : ++tilePosition;
        if (firstMove)
            allowed[1] = (white) ? --tilePosition : ++tilePosition;
        return allowed;
    }


}
