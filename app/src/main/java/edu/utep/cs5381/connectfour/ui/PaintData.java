package edu.utep.cs5381.connectfour.ui;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Data container for the various Paints/Colors used in the UI.
 */
class PaintData {
    private Paint p1Disc;
    private Paint p2Disc;
    private Paint dropDisc;
    private Paint dropBorder;
    private Paint disc;
    private Paint discBorder;
    private Paint winDiscs;
    private Paint butt;
    private Paint buttBorder;
    private Paint text;
    private Paint textSmall;
    private Paint currTurn;
    private Paint board;
    private Paint transparent;

    PaintData() {
        transparent = new Paint();
        transparent.setColor(Color.TRANSPARENT);
        board = new Paint();
        board.setColor(Color.DKGRAY);
        setP1();
        setP2();
        setOtherDiscs();
        setButton();
    }

    /* **************** Assign Methods for Paints **************** */
    private void setP1() {
        p1Disc = new Paint();
        p1Disc.setColor(Color.GREEN);
    }

    private void setP2() {
        p2Disc = new Paint();
        p2Disc.setColor(Color.BLUE);
    }

    private void setOtherDiscs() {
        dropDisc = new Paint();
        dropDisc.setColor(Color.YELLOW);
        dropBorder = new Paint();
        dropBorder.setColor(Color.RED);
        dropBorder.setStyle(Paint.Style.STROKE);
        dropBorder.setStrokeWidth(5);

        disc = new Paint();
        disc.setColor(Color.LTGRAY);
        discBorder = new Paint();
        discBorder.setColor(Color.BLACK);
        discBorder.setStyle(Paint.Style.STROKE);
        discBorder.setStrokeWidth(5);

        winDiscs = new Paint();
        winDiscs.setColor(Color.RED);
    }

    private void setButton() {
        butt = new Paint();
        butt.setColor(Color.WHITE);
        buttBorder = new Paint();
        buttBorder.setColor(Color.DKGRAY);
        buttBorder.setStyle(Paint.Style.STROKE);
        buttBorder.setStrokeWidth(5);

        text = new Paint();
        text.setStyle(Paint.Style.FILL);
        text.setColor(Color.BLACK);
        text.setTextSize(70);

        textSmall = new Paint();
        textSmall.setStyle(Paint.Style.FILL);
        textSmall.setColor(Color.BLACK);
        textSmall.setTextSize(50);

        currTurn = new Paint();
        currTurn.setStyle(Paint.Style.FILL);
        currTurn.setColor(Color.RED);
        currTurn.setTextSize(50);
    }

    /* **************** Getter Methods for Paints **************** */
    Paint player1() {
        return p1Disc;
    }
    Paint player2() {
        return p2Disc;
    }
    Paint headerDisc() {
        return dropDisc;
    }
    Paint headerDiscBorder() {
        return dropBorder;
    }
    Paint disc() {
        return disc;
    }
    Paint discBorder() {
        return discBorder;
    }
    Paint win() {
        return winDiscs;
    }
    Paint button() {
        return butt;
    }
    Paint buttonBorder() {
        return buttBorder;
    }
    Paint text() {
        return text;
    }
    Paint textS() {
        return textSmall;
    }
    Paint textCurrent() {
        return currTurn;
    }
    Paint background() {
        return disc;
    }
    Paint board() {
        return board;
    }
    Paint lines() {
        return disc;
    }
    Paint transparent() {
        return transparent;
    }
}
