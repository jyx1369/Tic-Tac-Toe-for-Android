/**
 * @author Yuxi Jin 
 * school: CMU
 * the date submitted: Oct 30, 2014
 */
package core;

/**
 * The TicTacToe interface is used by the GUI to report GUI related events to
 * the core implementation.
 */
public interface TicTacToe {


    /**
     * Add a player to the game.
     *
     * @param The player to add to the game.
     */
    public void addPlayer(Player player);

    /**
     * Get the player that currently has the turn.
     * 
     * @return The player that currently has the turn.
     */
    public Player getCurrentPlayer();

    /**
     * Get the game grid's height.
     *
     * @return The game grid's height.
     */
    public int getGridHeight();

    /**
     * Get the game grid's width.
     *
     * @return The game grid's width.
     */
    public int getGridWidth();


    /**
     * Get the player who placed a move in a given location.
     *
     * @param x The x coordinate associated with the desired location.
     * @param y The y coordinate associated with the desired location.
     *
     * @return The player who moved at this location or null if no move has been
     *         played.
     */
    public Player getSquare(int x, int y);

    /**
     * Attempts to place the current player's symbol on a square in the game
     * grid.
     *
     * @param x The x coordinate of the current player's move.
     * @param y The y coordinate of the current player's move.
     *
     * @throws IllegalStateException if the game has not yet been started.
     *
     * @return true if the move was valid and made, false otherwise false can
     *         mean that the coordinates were outside of the board or occupied
     *         already.
     */
    public boolean playMove(int x, int y);

    /**
     * Starts (or restarts) the game.
     */
    public void startNewGame();
    
    public boolean isFull();
    public boolean hasWon();
    public void switchPlayers();

}
