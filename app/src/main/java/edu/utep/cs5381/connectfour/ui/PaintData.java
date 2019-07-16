package edu.utep.cs5381.connectfour.ui;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

/**
 * Data container for the various Paints/Colors used in the UI.
 */
class PaintData {
    private final int p1 = Color.RED;
    private final int p2 = Color.BLUE;
    private final int win = Color.GREEN;
    private Paint p1Disc;
    private Paint p2Disc;
    private Paint dropDisc;
    private Paint dropBorder;
    private Paint disc;
    private Paint discBorder;
    private Paint winDiscs;
    private Paint butt;
    private Paint buttBorder;
    private TextPaint text;
    private Paint textSmall;
    private Paint textP1;
    private Paint textP2;
    private Paint textWin;
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
        setWin();
        setText();
    }

    /* **************** Assign Methods for Paints **************** */
    private void setP1() {
        p1Disc = new Paint();
        p1Disc.setColor(p1);

        textP1 = new Paint();
        textP1.setStyle(Paint.Style.FILL);
        textP1.setColor(p1);
        textP1.setTextSize(50);
    }

    private void setP2() {
        p2Disc = new Paint();
        p2Disc.setColor(p2);

        textP2 = new Paint();
        textP2.setStyle(Paint.Style.FILL);
        textP2.setColor(p2);
        textP2.setTextSize(50);
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
    }

    private void setButton() {
        butt = new Paint();
        butt.setColor(Color.WHITE);
        buttBorder = new Paint();
        buttBorder.setColor(Color.DKGRAY);
        buttBorder.setStyle(Paint.Style.STROKE);
        buttBorder.setStrokeWidth(5);

        text = new TextPaint();
        text.setAntiAlias(true);
        text.setColor(Color.BLACK);
    }

    private void setWin() {
        winDiscs = new Paint();
        winDiscs.setColor(win);

        textWin = new Paint();
        textWin.setStyle(Paint.Style.FILL);
        textWin.setColor(win);
        textWin.setTextSize(50);
    }

    private void setText() {
        textSmall = new Paint();
        textSmall.setStyle(Paint.Style.FILL);
        textSmall.setColor(Color.BLACK);
        textSmall.setTextSize(50);
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
    TextPaint text() {
        return text;
    }
    Paint textS() {
        return textSmall;
    }
    Paint textP1() {
        return textP1;
    }
    Paint textP2() {
        return textP2;
    }
    Paint textWin() {
        return textWin;
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
