package edu.utep.cs5381.connectfour.game;

/**
 * Controls the Connect Four gameplay.
 */
public class Runner {
    private Board board;
    private Player player1;
    private Player player2;
    private EndCheck checker;
    private Player winner;

    /**
     * Creates an instance of the Connect Four game.
     */
    public Runner() {
        board = new Board();
        checker = new EndCheck();
        player1 = new Player("Player 1",1);
        player2 = new Player("Player 2",2);
        player1.startTurn();
    }

    /**
     * Gets the current Player in the game.
     * @return the Player
     */
    public String getCurrentPlayerName() {
        if ( player1.isPlaying() )
            return player1.getName();
        return player2.getName();
    }

    /**
     * Drop a disc into column i in the Board.
     * @param i the column to drop the Chip in
     */
    public boolean dropChip(int i) {
        if ( player1.isPlaying() ) {
            int r = board.dropChipIn(i,player1);
            checkForWin(player1,r,i);
        } else {
            int r = board.dropChipIn(i,player2);
            checkForWin(player2,r,i);
        }
        return ( winner!=null || checker.checkDraw() );
    }

    /**
     * Ends the current Player's turn and starts the other Player's turn.
     */
    public void switchTurns() {
        if ( player1.isPlaying() ) {
            player1.endTurn();
            player2.startTurn();
        } else {
            player2.endTurn();
            player1.startTurn();
        }
    }

    /**
     * Checks if any Player has won the game after this play.
     * @return true if there was a winner after this play
     */
    private void checkForWin(Player p, int r, int c) {
        checker.set(board);
        if ( checker.checkWins(p,r,c) )
            winner = p;
    }

    /**
     * Get the winning Player of the game.
     * @return the Player who won
     */
    public String getWinnerName() {
        return winner.getName();
    }

    /**
     * Get the Board of the gampelay.
     * @return the Board in play
     */
    public Board getBoard() {
        return board;
    }
}
