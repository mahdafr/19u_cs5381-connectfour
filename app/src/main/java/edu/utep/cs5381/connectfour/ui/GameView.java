package edu.utep.cs5381.connectfour.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import edu.utep.cs5381.connectfour.model.*;

public class GameView extends View {
    private Parameters params; //bounds for gui elements
    private Board board;

    /**
     * Creates a new UI for the ConnectFourGame model.
     * @param context, the Activity (MainActivity)
     */
    public GameView(Context context) {
        super(context);
        calculateWidthAndHeight();
    }

    /**
     * Saves the Board of the game for state changes.
     * @param b the Board
     */
    public void setBoard(Board b) {
        board = b;
    }

    /**
     * Gets the dimensions of this View (width/height) in pixels.
     */
    private void calculateWidthAndHeight() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int width = Math.min(getWidth(), getHeight());
                    int height = Math.max(getWidth(), getHeight());
                    params = new Parameters(width,height);
                }
            });
        }
    }

    /**
     * Tracks events for the Discs (Circles in UI).
     */
    public interface DiscClickListener {
        void clicked(int index);
    }

    //the listener for this class
    private DiscClickListener discClickListener;

    /**
     * Create the listener for events on Discs (Circles in UI).
     * @param listener the DiscClickListener for events
     */
    public void setDiscClickListener(DiscClickListener listener) {
        discClickListener = listener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                int index = locateDisc(event.getX(), event.getY());
                if (index >= 0) { discClickListener.clicked(index); }
                break;
        }
        return true;
    }

    /**
     * Locates which Disc (column in the Board) the game will play.
     * @param x the x-coordinate whether the TouchEvent occurred
     * @param y the y-coordinate whether the TouchEvent occurred
     * @return the column where the Disc should be dropped
     */
    public int locateDisc(float x, float y) {
        int w = params.getWidth();
        int factor = w/9; //scale in X
        if ( x>=factor && x<2*factor )
            return 1;
        if ( x>=2*factor && x<3*factor )
            return 2;
        if ( x>=3*factor && x<4*factor )
            return 3;
        if ( x>=4*factor && x<5*factor )
            return 4;
        if ( x>=5*factor && x<6*factor )
            return 5;
        if ( x>=6*factor && x<7*factor )
            return 6;
        if ( x>=7*factor && x<8*factor )
            return 7;
        return -1;
    }

    /**
     * Draws the custom canvas for the ConncetFourGame.
     * @param canvas, the custom design
     */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawDiscs(canvas);
        drawBoard(canvas);
    }

    /**
     * Paints the background gameplay.
     * @param c  the canvas to draw on
     */
    private void drawBackground(Canvas c) {
        Paint bg = new Paint();
        bg.setColor(Color.LTGRAY);
        c.drawRect(0,0,params.getWidth(),params.getHeight(),bg);
    }

    /**
     * Paints the Circles for the player to drop in tne Board.
     * @param c  the canvas to draw on
     */
    private void drawDiscs(Canvas c) {
        int radius = params.getRadius();
        int cx = params.getDiscsLeft();
        int cy = params.getDiscsRow();

        //draw 7 circles as Chips to drop in Board
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        Paint paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(5);
        for ( int i=1 ; i<=7 ; i++,cx+=params.getDiscsSkip() ) {
            c.drawCircle(cx, cy, radius, paint);
            c.drawCircle(cx, cy, radius, paint2);
        }
    }

    /**
     * Draws the Board for gameplay.
     * @param c the canvas to modify
     */
    private void drawBoard(Canvas c) {
        int left = params.getBoardLeft();
        int right = params.getBoardRight();
        int top = params.getBoardTop();
        int bottom = params.getBoardBottom();
        int radius = params.getRadius();

        //draw the Board
        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);
        c.drawRoundRect(left,top,right,bottom,radius,radius,paint);

        //draw the lines
        paint.setColor(Color.LTGRAY);
        int x = left;
        int y = params.getDiscsRow() + params.getDiscsSkip();
        for ( int i=1; i<7 ; i++,x+=params.getDiscsSkip() )
            c.drawLine(x+left,top,x+left,bottom,paint);

        //draw the chip-holders
        paint.setColor(Color.LTGRAY);
        Paint paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(5);
        int cy = params.getDiscsRow();
        for ( int j=1 ; j<=6; j++ ) {
            int cx = params.getDiscsLeft();
            cy+=params.getDiscsRowSkip();
            for (int i = 1; i <= 7; i++, cx += params.getDiscsSkip()) {
                c.drawCircle(cx, cy, radius, paint);
                c.drawCircle(cx, cy, radius, paint2);
            }
        }
    }
}
