package lab.labA2;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Board for the chess Game. Tiles are set on the Board.
 * Board controls and oversees everything.
 * @author Jack Shih
 * @version 2020
 */
public class Board {
    /** Array for Tiles. */
    private Tile[][] chessBoard;
    /** The current Piece that is waiting to be moved. */
    private Piece piece;
    /** Determines if the click is first or second. */
    private boolean firstClick = true;
    /** The Tile that was clicked before. */
    private Tile previousTile;
    /** The current Player. */
    private Player currentPlayer;
    /** The Player that is waiting. */
    private Player waitingPlayer;   
    /** UI. */
    private UI ui;
    
    /**
     * Starts the Board with two Players.
     * Player one starts the game first.
     * @param one Player
     * @param two Player
     */
    Board(UI ui, Player one, Player two) {
        this.ui = ui;
        currentPlayer = one;
        waitingPlayer = two;    
    }
    
    /**
     * Determine who the current plater is.
     * @return Player
     */
    public Player whoseTurn() {
        return currentPlayer;
    }
    
    /**
     * Switch the two player's turn.
     */
    public void switchPlayer() {
        Player temp;
        temp = currentPlayer;
        currentPlayer = waitingPlayer;
        waitingPlayer = temp;
        waitingPlayer.notFirstMove();
        ui.displayMsg(currentPlayer.getColour() + "'s turn");
    }
 
    /**
     * Sets Tiles on the Board.
     * Tile Objs are stored into chessBoard (Tile[][])
     * Size is 8 * 8
     */
    public void setTile() {
        chessBoard = new Tile[8][8];
        //for-loop that alternate tile colours. i is row num, j is column num.
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[0].length; j++) {
                String colour = (isOdd(i + j)) ? "Darkseagreen" : "White"; 
                Tile tile = new Tile(this, colour, j, i);
                chessBoard[i][j] = tile;
            }
        }       
    }
    
    /**
     * Sets the initial pieces on Tiles to start the game.
     * @param colour String, black or white only
     * @param row int, 0 to 7
     * @throws FileNotFoundException will be thrown if colour is wrong
     */
    public void setPieces(String colour, int row) throws FileNotFoundException {
        chessBoard[0][row].setPiece(new Rook(colour, this));
        chessBoard[1][row].setPiece(new Bishop(colour, this));
        chessBoard[2][row].setPiece(new Knight(colour, this));
        chessBoard[3][row].setPiece(new King(colour, this));
        chessBoard[4][row].setPiece(new Queen(colour, this));
        chessBoard[5][row].setPiece(new Knight(colour, this));
        chessBoard[6][row].setPiece(new Bishop(colour, this));
        chessBoard[7][row].setPiece(new Rook(colour, this));
    }
    
    /**
     * Sets the pawns in the whole row.
     * @param colour String, black or white
     * @param row int 0 to 7
     * @throws FileNotFoundException will be thrown if colour is wrong
     */
    public void setPawn(String colour, int row) throws FileNotFoundException {
        //Grabs the player and assigns it to the pawn that has the same colour.
        Player player = (colour.equalsIgnoreCase(currentPlayer.getColour()))? currentPlayer : waitingPlayer;
        for (int i = 0; i < 8; i++)
            chessBoard[i][row].setPiece(new Pawn(colour, player, this));  
    }
    
    /**
     * This returns the Tile with the tile position that is passed in.
     * @param tilePosition as an int
     * @return Tile 
     */
    Tile getTileAt(int tilePosition) {
        int column = piece.extractPosition(tilePosition)[0];
        int row = piece.extractPosition(tilePosition)[1];
        return chessBoard[column][row];
    }
    
    /**
     * Checks if the int is odd or not.
     * @param x int
     * @return boolean true is it's odd
     */
    public static boolean isOdd(int x) {
        return (x % 2 == 1) ? true : false;
    }
    
    /**
     * Setter for piece.
     * @param thePiece Piece
     */
    public void savePiece(Piece thePiece) {
        this.piece = thePiece;
    }
    
    /**
     * Getter for piece.
     * @return Piece
     */
    public Piece getPiece() {
        return piece;
    }
    
    /**
     * Getter for boolean firstclick.
     * Checks the current click.
     * @return boolean
     */
    public boolean getFirstClick() {
        return firstClick;
    }
    
    /**
     * Sets the current click.
     * @param value boolean
     */
    public void setFirstClick(boolean value) {
        firstClick = value;
    }
    
    /**
     * Getter for the Tile that was clicked previously.
     * @return Tile
     */
    public Tile getPreviousTile() {
        return previousTile;
    }
    
    /**
     * Stores the Tile that was clicked previously.
     * @param tile Tile
     */
    public void savePreviousTile(Tile tile) {
        previousTile = tile;
    }
    
    /**
     * Getter for the Board.
     * @return Tile[][]
     */
    public Tile[][] getBoard() {
        return chessBoard;
    }
    
    /**
     * Getter for the current Player.
     * @return Player
     */
    public Player getCurrPlayer() {
        return currentPlayer;
    }
    
    /**
     * This returns the position of every Piece on the Board.
     * This method is all used anywhere yet.
     * @return int[] of all positions of all pieces
     */
    private int[] getAllPositions() {
        int[] pieces = new int[32];
        Arrays.fill(pieces, -1);
        int count = 0;
        for (Tile[] t : getBoard()) {
            for (Tile y : t) {
                if (!y.emptyTile()) {
                    pieces[count] = y.getPosition();
                    count++;
                }            
            }                
        }
        int[] result = new int[count];
        for (int x : pieces) {
            if (x >= 0)
                result[--count] = x;
        }
     return result;   
    }
    
}
