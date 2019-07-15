package edu.utep.cs5381.connectfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import edu.utep.cs5381.connectfour.ui.GameView; //the UI
import edu.utep.cs5381.connectfour.game.Runner; //the model

/**
 * The Controller in the MVC for the Connect Four game. Listens for and tells View/Model about events.
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MAINACT ";
    private Runner game;
    private GameView gameUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameUI = new GameView(this);
        setContentView(gameUI);

        game = new Runner();
        gameUI.setBoard(game.getBoard());
        gameUI.setClickListener(new GameView.ScreenClickListener() {
            @Override
            public void clickedBoard(int index) {
                dropDisc(index);
            }

            @Override
            public void clickedButton() {
                newGame();
            }
        });

        Drawable d0 = getResources().getDrawable(R.drawable.player1);
        Drawable d1 = getResources().getDrawable(R.drawable.player2);
        gameUI.setImages(d0,d1);
        gameUI.setNames(game.getPlayer1Name(),game.getPlayer2Name());
    }

    /**
     * Add a disc in this column for the current player.
     * @param i the column to drop the disc
     */
    private void dropDisc(int i) {
        if ( !game.dropChip(i-1) && game.chipPlayed() ) {
            game.switchTurns();
        } else
            if ( !game.chipPlayed() )
                toast(TAG + " not played in " +i);
            else {
                toast(TAG + game.getWinnerName() + " won!");
                gameUI.setWinSequence(game.getWinningSequence(),game.getWinnerID());
                showAlertDialog();
            }
        gameUI.setCurrentPlayer(game.getCurrentPlayerName());
        gameUI.setBoard(game.getBoard());
        gameUI.invalidate();
    }

    /**
     * Start a new Game on user request.
     */
    private void newGame() {
        if ( game.inProgress() )
            showAlertDialog();
        game.startOver();
        gameUI.setBoard(game.getBoard());
        gameUI.setWinSequence(null,-1);
        gameUI.setCurrentPlayer(game.getCurrentPlayerName());
        gameUI.invalidate();
    }

    /**
     * Displays an Alert Dialog for the user to confirm selection.
     */
    private void showAlertDialog() {
        toast(TAG + "want to start new game?");
        //todo
//        FragmentManager fm = getSupportFragmentManager();
//        MyAlertDialogFragment alertDialog = MyAlertDialogFragment.newInstance("Some title");
//        alertDialog.show(fm, "fragment_alert");
    }


    /**
     * Makes a Toast message of msg in the app.
     * @param msg the message to print on Toast
     */
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
