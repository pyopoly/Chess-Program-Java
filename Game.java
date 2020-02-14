package lab.labA2;

/**
 * A javafx chess program. Two players can play the game.
 * Pieces can move anywhere on the board.
 * @author Jack Shih
 * @version 2020
 */
public class Game {

    /**
     * Drives the Program.
     * @param args unused
     */
    public static void main(String[] args) {
        
        //Player colour must be black or white(same as chess piece colour).
        Player player1 = new Player("white");
        Player player2 = new Player("black");
        UI ui = new GUI();       
        Board chessBoard = new Board(ui, player1, player2);
        ui.run(chessBoard);       
    }
}
