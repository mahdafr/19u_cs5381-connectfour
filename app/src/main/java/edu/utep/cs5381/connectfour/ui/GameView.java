package edu.utep.cs5381.connectfour.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import edu.utep.cs5381.connectfour.game.*;

public class GameView extends View {
    private Parameters params; //bounds for gui elements
    private PaintData paints; //paints for each color
    private Drawable player1; //p1 image
    private Drawable player2; //p2 image
    private Board board; //the board of the game
    private int[] wins; //the win sequence
    private int winner; //winning player's ID
    private String player1Name;
    private String player2Name;
    private String currPlayer;

    /**
     * Creates a new UI for the ConnectFourGame model.
     * @param context, the Activity (MainActivity)
     */
    public GameView(Context context) {
        super(context);
        calculateWidthAndHeight();
        paints = new PaintData();
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

    /* **************** Methods for ScreenClickListener **************** */
    /**
     * Tracks events for the Discs (Circles in UI).
     */
    public interface ScreenClickListener {
        void clickedBoard(int index);
        void clickedButton();
    }

    private ScreenClickListener clickListener;

    /**
     * Create the listener for events on Discs (Circles in UI).
     * @param listener the DiscClickListener for events
     */
    public void setClickListener(ScreenClickListener listener) {
        clickListener = listener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                int index = locateDisc(event.getX(),event.getY());
                if (index >= 0) { clickListener.clickedBoard(index); }
                else if ( buttClickCheck(event.getX(),event.getY()) )
                    clickListener.clickedButton();
                break;
        }
        return true;
    }

    /**
     * Locates which Disc (column in the Board) the game will play.
     * @param x the x-coordinate whether the TouchEvent occurred
     * @return the column where the Disc should be dropped
     */
    public int locateDisc(float x, float y) {
        //if the touch is beyond the Board's height, it will not be detected
        if ( y>params.getBoardBottom() )
            return -1;
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
     * Checks if the Button was clicked to handle the event.
     * @param x the x-coordinate whether the TouchEvent occurred
     * @param y the y-coordinate whether the TouchEvent occurred
     */
    public boolean buttClickCheck(float x, float y) {
        if ( x>=params.getButtonLeft() && x<=params.getButtonRight() )
            if ( y>=params.getButtonTop() && y<=params.getButtonBottom() )
                return true;
            else return false;
        return false;
    }

    /* **************** Methods for Fixed Items on Screen **************** */
    /**
     * Draws the Button for New Game events.
     * @param canvas the canvas to draw on
     */
    private void drawButton(Canvas canvas) {
        String txt = "New";
        int left = params.getButtonLeft();
        int right = params.getButtonRight();
        int top = params.getButtonTop();
        int bottom = params.getButtonBottom();
        int radius = params.getRadius();

        //draw the Button
        canvas.drawRoundRect(left,top,right,bottom,radius,radius,paints.button());
        canvas.drawRoundRect(left,top,right,bottom,radius,radius,paints.buttonBorder());

        android.text.TextPaint text = paints.text();
        text.setTextSize(30 * getResources().getDisplayMetrics().density);

        StaticLayout sl = new StaticLayout(txt, paints.text(), params.getWidth(),
                Layout.Alignment.ALIGN_CENTER, 1, 1, true);
        canvas.save();
        canvas.translate(0, top);
        sl.draw(canvas);
        canvas.restore();
    }

    /**
     * Draws the Images for Player1 and Player2.
     */
    private void drawImages(Canvas canvas) {
        player1.setBounds(params.player1Left(),params.player1Top(),params.player1Right(),params.player1Bottom());
        player1.draw(canvas);
        int x = 2*params.getRadius();
        int y = params.player1Bottom() + 2*params.getRadius();
        if ( currPlayer!=null && currPlayer==player1Name )
            canvas.drawText(player1Name,x,y,paints.textP1());
        else
            canvas.drawText(player1Name,x,y,paints.textS());
        if ( winner==1 )
            canvas.drawText(player1Name,x,y,paints.textWin());

        player2.setBounds(params.player2Left(),params.player2Top(),params.player2Right(),params.player2Bottom());
        player2.draw(canvas);
        x = params.player2Left();
        y = params.player2Bottom() + 2*params.getRadius();
        if ( currPlayer!=null && currPlayer==player2Name )
            canvas.drawText(player2Name,x,y,paints.textP2());
        else
            canvas.drawText(player2Name,x,y,paints.textS());
        if ( winner==2 )
            canvas.drawText(player2Name,x,y,paints.textWin());
    }

    /**
     * Saves the Images for the Players.
     * @param img1 the first image
     * @param img2 the second image
     */
    public void setImages(Drawable img1, Drawable img2) {
        player1 = img1;
        player2 = img2;
    }

    /**
     * Saves the String names of the Players.
     * @param n1 Player1's name
     * @param n2 Player2's name
     */
    public void setNames(String n1, String n2) {
        player1Name = n1;
        player2Name = n2;
    }

    /* **************** Methods for Drawing on Screen **************** */
    /**
     * Draws the custom canvas for the ConncetFourGame.
     * @param canvas, the custom design
     */
    @Override
    public void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawDiscs(canvas);
        drawBoard(canvas);
        drawButton(canvas);
        drawImages(canvas);
        super.onDraw(canvas);
    }

    /**
     * Paints the background gameplay.
     * @param canvas the canvas to draw on
     */
    private void drawBackground(Canvas canvas) {
        canvas.drawRect(0,0,params.getWidth(),params.getHeight(),paints.background());
    }

    /**
     * Paints the Circles for the player to drop in tne Board.
     * @param canvas the canvas to draw on
     */
    private void drawDiscs(Canvas canvas) {
        int radius = params.getRadius();
        int cx = params.getDiscsLeft();
        int cy = params.getDiscsRow();

        //draw 7 circles as Chips to drop in Board
        for ( int i=1 ; i<=7 ; i++,cx+=params.getDiscsSkip() ) {
            if ( currPlayer!=null && currPlayer==player1Name )
                canvas.drawCircle(cx, cy, radius, paints.player1());
            if ( currPlayer!=null && currPlayer==player2Name )
                canvas.drawCircle(cx, cy, radius, paints.player2());
            if ( currPlayer==null ) {
                canvas.drawCircle(cx, cy, radius, paints.headerDisc());
                canvas.drawCircle(cx, cy, radius, paints.headerDiscBorder());
            }
        }
    }

    /**
     * Draws the Board for gameplay.
     * @param canvas the canvas to draw on
     */
    private void drawBoard(Canvas canvas) {
        int left = params.getBoardLeft();
        int right = params.getBoardRight();
        int top = params.getBoardTop();
        int bottom = params.getBoardBottom();
        int radius = params.getRadius();

        //draw the Board
        canvas.drawRoundRect(left,top,right,bottom,radius,radius,paints.board());

        //draw the lines
        int x = left;
        int y = params.getDiscsRow() + params.getDiscsSkip();
        for ( int i=1; i<7 ; i++,x+=params.getDiscsSkip() )
            canvas.drawLine(x+left,top,x+left,bottom,paints.lines());

        fillBoard(canvas);
    }

    /* **************** Methods for Maintaining Game State **************** */
    /**
     * Saves the current Player for this turn.
     * @param p the Player's name
     */
    public void setCurrentPlayer(String p) {
        currPlayer = p;
    }

    /**
     * Saves the Board to reflect state changes.
     * @param b the Board for state changes
     */
    public void setBoard(Board b) {
        board = b;
    }

    /**
     * Fills the Board with discs for gameplay.
     * @param canvas the canvas to draw on
     */
    private void fillBoard(Canvas canvas) {
        //draw the chip-holders and Chips
        int radius = params.getRadius();
        int cy = params.getDiscsRow();
        for ( int j=1 ; j<=board.Rows(); j++ ) {
            int cx = params.getDiscsLeft();
            cy+=params.getDiscsRowSkip();
            for (int i = 1; i <= board.Cols() ; i++, cx += params.getDiscsSkip()) {
                android.graphics.Paint paint = getColor(j-1,i-1);
                canvas.drawCircle(cx, cy, radius, paint);
                canvas.drawCircle(cx, cy, radius, paints.discBorder());
            }
        }
    }

    /**
     * Checks if this (r,c) in the Board is filled, and returns the Paint for the Player (owner).
     * @param r the row
     * @param c the column
     * @return the appropriate paint
     */
    private android.graphics.Paint getColor(int r, int c) {
        int nwR = board.Rows()-r-1;
        if ( wins!=null ) {
            for ( int i=0 ; i< wins.length && board.getOwnerAt(nwR,c)==winner ; i++ )
                if ( (i%2==0) && wins[i]==nwR && i!=wins.length-1 && wins[i+1]==c )
                    return paints.win();
        }
        switch ( board.getOwnerAt(nwR,c) ) {
            case 1:
                return paints.player1();
            case 2:
                return paints.player2();
            default:
                return paints.disc();
        }
    }

    /**
     * Saves the list of winning Chips for appropriate portrayal in the UI.
     * @param seq the list of coordinates of the chips in the winning sequence
     * @param winID the winner ID (Player's)
     */
    public void setWinSequence(int[] seq, int winID) {
        wins = seq;
        winner = winID;
    }
}
