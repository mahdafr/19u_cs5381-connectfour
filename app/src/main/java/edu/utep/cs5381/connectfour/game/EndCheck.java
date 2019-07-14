package edu.utep.cs5381.connectfour.game;

class EndCheck {
    private Board b;
    private Player p;
    private final int CHIPS = 4;
    private int chips; //track for draws

    EndCheck() {
        chips = 0;
    }

    void set(Board board) {
        b = board;
    }

    /**
     * Checks whether the last played Chip was a win.
     * @param player the Player who (might) have won.
     * @param r the row the Chip was played in
     * @param c tge column the Chip was played in
     * @return true if Player played the winning Chip
     */
    boolean checkWins(Player player, int r, int c) {
        p = player;
        return ( checkDiag(r,c) || checkRow(r) || checkCol(c) );
    }

    /**
     * Checks whether the last played Chip was a Diagonal win
     * @param r the row to check for a win
     * @param c the column to check for a win
     * @return true if there is a Diagonal win because of this Chip
     */
    private boolean checkDiag(int r, int c) {
        //todo
        return false;
    }

    /**
     * Checks whether the last played Chip was a win by Rows
     * @param r the row to check for a win
     * @return true if there is a win in this row
     */
    private boolean checkRow(int r) {
        int counter = 0;
        for ( int i=0 ; i<b.Cols() && counter!=CHIPS ; i++ )
            counter = ( b.getOwnerAt(r,i)==p.getId() ) ? counter+1 : 0;
        if ( counter>=CHIPS )
            return true;
        return false;
    }

    /**
     * Checks whether the last played Chip was a win by Columns
     * @param c the column to check for a win
     * @return true if there is a win in this column
     */
    private boolean checkCol(int c) {
        int counter = 0;
        for ( int i=0 ; i<b.Rows() && counter!=CHIPS ; i++ )
            counter = ( b.getOwnerAt(i,c)==p.getId() ) ? counter+1 : 0;
        if ( counter>=CHIPS )
            return true;
        return false;
    }

    /**
     * Checks whether the Game resulted in a draw.
     * @return whether there are no more Chips to be played
     */
    public boolean checkDraw() {
        return ( chips==b.Rows()*b.Cols() );
    }
}
