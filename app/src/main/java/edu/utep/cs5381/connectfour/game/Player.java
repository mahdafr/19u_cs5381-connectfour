package edu.utep.cs5381.connectfour.game;

class Player {
    private String playerName;
    private int id;
    private boolean isPlaying; //is this Player's turn?

    /**
     * Creates a new Player in the game.
     * @param name, a string of this Player's name
     * @param ID, a character representation of this Player's ID
     */
    public Player(String name, int ID) {
        playerName = name;
        id = ID;
    }

    /**
     * Gets this Player's ID.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Begins this Player's turn.
     */
    public void startTurn() {
        isPlaying = true;
    }

    /**
     * End this Player's turn.
     */
    public void endTurn() {
        isPlaying = false;
    }

    /**
     * Does the current turn belong to this Player?
     * @return T if this Player has not made a move
     */
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Get this Player's name.
     * @return the Player's saved name
     */
    public String getName() {
        return playerName;
    }
}
