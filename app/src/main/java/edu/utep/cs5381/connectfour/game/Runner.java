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
    private boolean playedChip;

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
     * Drop a disc into column i in the Board.
     * @param i the column to drop the Chip in
     */
    public boolean dropChip(int i) {
        if ( player1.isPlaying() ) {
            int r = board.dropChipIn(i,player1);
            if ( r==-1 ) {
                playedChip = false;
                return false;
            }
            else playedChip = true;
            checkForWin(player1,r,i);
            if ( winner==player1 ) player1.wonGame();
        } else {
            int r = board.dropChipIn(i,player2);
            if ( r==-1 ) {
                playedChip = false;
                return false;
            }
            else playedChip = true;
            checkForWin(player2,r,i);
            if ( winner==player2 ) player2.wonGame();
        }
        return ( winner!=null || checker.checkDraw() );
    }

    /**
     * Checks if the Chip could be played or not.
     * @return true if the Chip was played successfully
     */
    public boolean chipPlayed() {
        return playedChip;
    }

    /**
     * Ends the current Player's turn and starts the other Player's turn.
     */
    public void switchTurns() {
        playedChip = false;
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
     * Gets the winning chips as a list: {(R,C),(R,C),(R,C),(R,C)}.
     * @return the winning sequence of Chips
     */
    public int[] getWinningSequence() {
        return checker.getWinningSequence();
    }

    /**
     * Resets the Board and starts a new Connect Four Game.
     */
    public void startOver() {
        board = new Board();
        checker = new EndCheck();
        switchTurns();
        if ( winner==player2 ) player2.wonGame();
        else player1.wonGame();
        winner = null;
    }

    /* **************** Getters **************** */
    /**
     * Checks if the game ended by rules of a draw.
     * @return true if the game was ended by a draw
     */
    public boolean wonByDraw() {
        checker.set(board);
        return checker.checkDraw();
    }

    /**
     * Checks if Players can still make moves in the Board.
     * @return true if the game has no winner or is not in a draw
     */
    public boolean inProgress() {
        return winner==null && !checker.checkDraw();
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
     * Gets the current Player in the game.
     * @return the Player's ID
     */
    public int getCurrentPlayerID() {
        if ( player1.isPlaying() )
            return player1.getId();
        return player2.getId();
    }

    /**
     * Get the winning Player of the game.
     * @return the Player who won
     */
    public String getWinnerName() {
        if ( winner==null )
            return "Everybody's a Winner"; //game was draw
        return winner.getName();
    }

    /**
     * Gets the winning Player's ID.
     * @return the winner's ID
     */
    public int getWinnerID() {
        return winner.getId();
    }

    /**
     * Get the Board of the gampelay.
     * @return the Board in play
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get Player1's name.
     * @return a string
     */
    public String getPlayer1Name() {
        return player1.getName();
    }

    /**
     * Get Player1's name.
     * @return a string
     */
    public String getPlayer2Name() {
        return player2.getName();
    }
}
