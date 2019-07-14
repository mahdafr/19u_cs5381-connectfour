package edu.utep.cs5381.connectfour.ui;

/**
 * Calculates dimensions for Discs and the Board to be displayed in UI.
 */
class Parameters {
    private int screenWidth, screenHeight;
    private int boardLeft, boardRight;
    private int boardTop, boardBottom;
    private int radius;
    private int scaleH = 8;
    private int scaleW = 9;
    private int discsRow, discsLeft;
    private int discsRowSkip;
    private int magicOffset;

    Parameters(int width, int height) {
        screenWidth = width;
        screenHeight = height;
        setParameters();
    }

    /**
     * Sets the Board's display bounds and Discs' dimensions.
     */
    private void setParameters() {
        radius = screenWidth/(scaleW*3);
        magicOffset = radius+(radius/2);

        boardLeft = screenWidth/scaleW;
        boardRight = (scaleW-1)*screenWidth/scaleW;
        boardTop = 3*screenHeight/scaleH; //fixed

        discsRow = boardTop - magicOffset;
        discsLeft = 2*boardLeft - magicOffset;
        discsRowSkip = 2*magicOffset;
        boardBottom = boardTop + 6*discsRowSkip;
    }

    /* ****************** Screen Dimension Getters ****************** */
    int getWidth() {
        return screenWidth;
    }
    int getHeight() {
        return screenHeight;
    }

    /* ****************** Board Dimension Getters ****************** */
    int getBoardLeft() {
        return boardLeft;
    }
    int getBoardRight() {
        return boardRight;
    }
    int getBoardTop() {
        return boardTop;
    }
    int getBoardBottom() {
        return boardBottom;
    }

    /* ****************** Disc Dimension Getters ****************** */
    int getRadius() {
        return radius;
    }
    int getDiscsRow() {
        return discsRow;
    }
    int getDiscsLeft() {
        return discsLeft;
    }
    int getDiscsSkip() { return boardLeft; }
    int getDiscsRowSkip() {
        return discsRowSkip;
    }
}
