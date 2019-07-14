package edu.utep.cs5381.connectfour.game;

/**
 * A Place may or may not have a Chip in it.
 */
class Chip {
    private int owner;

    /**
     * Create a new Chip for this Player's ID.
     * @param playerID, saves the Player's ID to this Chip
     */
    Chip(int playerID) {
        owner = playerID;
    }

    /**
     * Get the owner of this chip.
     * @return a character-representation of who owns this Chip
     */
    int whoOwns() {
        return owner;
    }
}