package edu.utep.cs5381.connectfour.game;

/**
 * The Board on which the Connect Four game is played.
 */
public class Board {
    private Place[][] board;
    private int rows;
    private int columns;

    /**
     * Creates a Board of size numRows x numCols.
     * @param numRows, the number of rows in the Board
     * @param numCols, the number of columns in the Board
     */
    Board(int numRows, int numCols) {
        rows = numRows;
        columns = numCols;
        clear();
    }

    /**
     * Creates a square Board of size 7x6 (rows x columns).
     */
    Board() {
        rows = 6;
        columns = 7;
        clear();
    }

    /**
     * Inserts a Chip for Player in the col.
     * @param col the column the Chip was dropped in
     * @param p the Player who dropped the Chip
     */
    public int dropChipIn(int col, Player p) {
        if ( isColumnFull(col) )
            return -1;
        int i=0;
        while ( i<rows && board[i][col].getOwner()!=0 )
            i++;
        board[i][col].placeChip(p);
        return i;
    }

    /**
     * Checks if the column i is full.
     * @param i the column to check
     * @return true if no more chips can be placed in column i
     */
    private boolean isColumnFull(int i) {
        int r = 0;
        while ( r<rows && board[r][i].getOwner()!=0 )
            r++;
        if ( r>= rows ) return true;
        return false;
    }

    /**
     * Get the number of rows in the Board.
     * @return the number of rows
     */
    public int Rows() {
        return rows;
    }

    /**
     * Get the number of columns in the Board.
     * @return the number of columns
     */
    public int Cols() {
        return columns;
    }

    /**
     * CLears the board.
     */
    private void clear() {
        board = new Place[rows][columns];
        for ( int i=0 ; i<rows ; i++ )
            for ( int j=0 ; j<columns; j++ )
                board[i][j] = new Place(i,j);
    }

    /**
     * Who owns the Chip in Place(i,j)?
     * @param i, the row
     * @param j, the column
     * @return the occupying Player's ID
     */
    public int getOwnerAt(int i, int j) {
        return board[i][j].getOwner();
    }

    /**
     * A Board consists of (rows x columns) Places.
     */
    class Place {
        private int row;
        private int col;
        private Chip chip;

        /**
         * Creates a Place at coordinate (x,y) in the Board.
         * @param x, an int for the Row of this Place
         * @param y, an int for the Col of this Place
         */
        Place(int x, int y) {
            row = x;
            col = y;
            chip = null; //empty Place
        }

        /**
         * Does Player1 own the Chip in this Place?
         * @return true if Player1 owns the chip in this Place
         */
        boolean occupiedBy(Player p) {
            return chip.whoOwns()==p.getId();
        }

        /**
         * Which Player's Chip owns this Place?
         * @return the occupying Player's ID
         */
        int getOwner() {
            if ( chip==null )
                return 0;
            return chip.whoOwns();
        }

        /**
         * Add a chip to this Place for a Player
         * @param p1, the Player inserting this Chip
         */
        void placeChip(Player p1) {
            chip = new Chip(p1.getId());
        }
    }
}
