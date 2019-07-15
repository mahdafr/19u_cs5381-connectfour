package edu.utep.cs5381.connectfour.ui;

/**
 * Calculates dimensions for Discs, Board, and Button to be displayed in UI.
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
    private int buttonLeft, buttonRight;
    private int buttonTop, buttonBottom;
    private int p1Left, p1Right, p1Top, p1Bottom;
    private int p2Left, p2Right, p2Top, p2Bottom;

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

        buttonTop = (scaleH-1)*screenHeight/scaleH; //fixed
        buttonBottom = buttonTop + discsRowSkip;
        buttonLeft = 3*screenWidth/scaleW;
        buttonRight = (scaleW-3)*screenWidth/scaleW;

        p1Left = screenWidth/scaleW - magicOffset;
        p1Right = p1Left + 250;
        p1Top = screenHeight/scaleH - magicOffset;
        p1Bottom = p1Top + 250;
        p2Left = (scaleW-1)*screenWidth/scaleW - 2*magicOffset;
        p2Right = p2Left + 250;
        p2Top = screenHeight/scaleH - magicOffset;
        p2Bottom = p2Top + 250;
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

    /* ****************** Button Dimension Getters ****************** */
    int getButtonLeft() {
        return buttonLeft;
    }
    int getButtonRight() {
        return buttonRight;
    }
    int getButtonTop() {
        return buttonTop;
    }
    int getButtonBottom() {
        return buttonBottom;
    }

    /* ****************** Player Item Dimension Getters ****************** */
    int player1Left() {
        return p1Left;
    }
    int player1Right() {
        return p1Right;
    }
    int player1Top() {
        return p1Top;
    }
    int player1Bottom() {
        return p1Bottom;
    }
    int player2Left() {
        return p2Left;
    }
    int player2Right() {
        return p2Right;
    }
    int player2Top() {
        return p2Top;
    }
    int player2Bottom() {
        return p2Bottom;
    }
}
