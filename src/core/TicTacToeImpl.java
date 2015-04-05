/**
 * @author Yuxi Jin 
 * school: CMU
 * the date submitted: Oct 30, 2014
 */
package core;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeImpl implements TicTacToe {

    private static final int DEFAULT_GRID_WIDTH = 3;
    private static final int DEFAULT_GRID_HEIGHT = 3;
    private static final int DEFAULT_WHOLE_GRID = 9;
    private final int gridWidth;
    private final int gridHeight;
    private final List<Player> players;
    private final Player gameGrid[][];

    private int currentPlayerIndex;
    private boolean hasStarted;

    /**
     * Initialize a game of Tic-Tac-Toe with a 3x3 game grid.
     */
    public TicTacToeImpl() {
        this(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
    }

    /**
     * Initialize a game of Tic-Tac-Toe with a custom grid size.
     */
    public TicTacToeImpl(int width, int height) {
        gridWidth = width;
        gridHeight = height;
        players = new ArrayList<Player>();
        gameGrid = new Player[gridHeight][gridWidth];
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public Player getCurrentPlayer() {
        checkStarted();
        checkHasPlayers();
        return players.get(currentPlayerIndex);
    }

    @Override
    public int getGridHeight() {
        return gridHeight;
    }

    @Override
    public int getGridWidth() {
        return gridWidth;
    }

    @Override
    public Player getSquare(int x, int y) {
        checkStarted();
        checkHasPlayers();
        return gameGrid[y][x];
    }

    @Override
    public boolean playMove(int x, int y) {
        checkStarted();
        checkHasPlayers();

        if (x < 0 || x > gridWidth || y < 0 || y > gridHeight) {
            return false;
        }
        if (gameGrid[y][x] != null) {
            return false;
        }

        gameGrid[y][x] = getCurrentPlayer();
        
        return true;
    }
    
    /**
     * this method is optionally used
     * only used when choose PVE version
     */
    public int computerMove(){
    	int move = (int)(Math.random()*DEFAULT_WHOLE_GRID);
    	int row=move/gridWidth;
    	int col=move%gridWidth;
    	while(gameGrid[row][col] != null){
    		move = (int)(Math.random()*DEFAULT_WHOLE_GRID);
    		row=move/gridWidth;
    		col=move%gridWidth;
    	}
    	gameGrid[row][col] = getCurrentPlayer();
    	return move;
    }


    @Override
    public void startNewGame() {
        hasStarted = true;
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                gameGrid[y][x] = null;
            }
        }
        currentPlayerIndex = 0;
    }

    public void switchPlayers() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    /**
     * Checks if the current player has won the game.
     */
    public boolean hasWon() {
        checkStarted();
        checkHasPlayers();

        Player currentPlayer = getCurrentPlayer();

        // Check for a horizontal win.
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                if (gameGrid[y][x] == null || !gameGrid[y][x].equals(currentPlayer)) {
                    break;
                }
                if (x == gridWidth - 1) {
                    return true;
                }
            }
        }

        // Check for a vertical win.
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                if (gameGrid[y][x] == null || !gameGrid[y][x].equals(currentPlayer)) {
                    break;
                }
                if (y == gridHeight - 1) {
                    return true;
                }
            }
        }

        // Check for a diagonal win.
        for (int x = 0, y = 0; x < gridWidth && y < gridHeight; x++) {
            if (gameGrid[y][x] == null || !gameGrid[y][x].equals(currentPlayer)) {
                break;
            }
            if (x == gridWidth - 1 && y == gridHeight - 1) {
                return true;
            }
            y++;
        }

        // Check for anti-diagonal win.
        for (int x = gridWidth - 1, y = 0; x >= 0 && y < gridHeight; x--) {
            if (gameGrid[y][x] == null || !gameGrid[y][x].equals(currentPlayer)) {
                break;
            }
            if (x == 0 && y + 1 == gridHeight) {
                return true;
            }
            y++;
        }

        return false;
    }

    /**
     * Checks if all of the squares in the game grid are occupied.
     */
    public boolean isFull() {
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                if (gameGrid[y][x] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkStarted() {
        if (!hasStarted) {
            throw new IllegalStateException("Game not yet started.");
        }
    }

    private void checkHasPlayers() {
        if (players.size() < 2) {
            throw new IllegalStateException("Game must be played with at least two players.");
        }
    }
    
}
