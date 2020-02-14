package lab.labA2;


/**
 * UI interface. Only GUI was made to use this interface.
 * @author Jack Shih
 * @version 2020
 */
public interface UI {
    
    /**
     * To display Msg if needed. Prints out in console and window title bar.
     * @param msg A String.
     */
    void displayMsg(String msg);
    
    /** 
     * To display Error if needed. Prints out in console and window title bar.
     * @param msg A String.
     */
    void displayErrorMsg(String msg);

    /**
     * Launches the program with the board Obj.
     * @param board Board obj to launch the program.
     */
    void run(Board board);
    

}
