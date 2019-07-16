package edu.utep.cs5381.connectfour;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Toast;
import edu.utep.cs5381.connectfour.ui.*; //the UI
import edu.utep.cs5381.connectfour.game.Runner; //the model

/**
 * The Controller in the MVC for the Connect Four game. Listens for and tells View/Model about events.
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MAINACT ";
    private final String restartQuest = "Do you want to start a new Game?";
    private final String restartMsg = "\nThis will forfeit the current game.";
    private Runner game;
    private GameView gameUI;
    private SoundPool player;
    private int player1Sound;
    private int player2Sound;
    private int drawSound;
    private int winningSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameUI = new GameView(this);
        setContentView(gameUI);

        game = new Runner();
        gameUI.setBoard(game.getBoard());
        listenForTouches();

        Drawable d0 = getResources().getDrawable(R.drawable.player1);
        Drawable d1 = getResources().getDrawable(R.drawable.player2);
        gameUI.setImages(d0,d1);
        gameUI.setNames(game.getPlayer1Name(),game.getPlayer2Name());

        player = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        player1Sound = player.load(this, R.raw.doh, 1);
        player2Sound = player.load(this,R.raw.haha,1);
        winningSound = player.load(this, R.raw.futurama, 1);
        drawSound = player.load(this, R.raw.rickandmortysong, 1);
    }

    /**
     * Add a disc in this column for the current player.
     * @param i the column to drop the disc
     */
    private void dropDisc(int i) {
        if ( !game.dropChip(i-1) && game.chipPlayed() ) {
            if ( game.getCurrentPlayerID()==1 ) play(player1Sound);
            else play(player2Sound);
            game.switchTurns();
            gameUI.setCurrentPlayer(game.getCurrentPlayerName());
            gameUI.setBoard(game.getBoard());
        } else
            if ( !game.chipPlayed() )
                toast("Column " +i+ " is full.");
            else
                gameFinished();
        gameUI.invalidate();
    }

    /**
     * When the game is finished, show the winning Chip sequence and disable Board touch events.
     */
    private void gameFinished() {
        if ( game.wonByDraw() ) play(drawSound);
        else {
            gameUI.setWinSequence(game.getWinningSequence(),game.getWinnerID());
            play(winningSound);
        }
        gameUI.setClickListener(new GameView.ScreenClickListener() {
            @Override
            public void clickedBoard(int index) {

            }

            @Override
            public void clickedButton() {
                newGame();
            }
        });
        gameUI.invalidate();
        showAlertDialog(game.getWinnerName() + "!\n" + restartQuest);
    }

    /**
     * Start a new Game on user request.
     */
    private void newGame() {
        //ask for user confirmation of forfeit
        if ( game.inProgress() )
            showAlertDialog(restartQuest+restartMsg);
        //ask user confirmation of restart
        else
            showAlertDialog(restartQuest);
        gameUI.invalidate();
    }

    /**
     * Starts a new Connect Four Game.
     */
    private void startGame() {
        listenForTouches();
        game.startOver();
        gameUI.setBoard(game.getBoard());
        gameUI.setWinSequence(null, -1);
        gameUI.setCurrentPlayer(game.getCurrentPlayerName());
        gameUI.invalidate();
    }

    /**
     * Displays an Alert Dialog for the user to confirm selection.
     */
    private void showAlertDialog(String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                (dialog, id) -> startGame());

        builder1.setNegativeButton(
                "No",
                (dialog, id) -> toast("Ok!"));

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    /**
     * Makes a Toast message of msg in the app.
     * @param msg the message to print on Toast
     */
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Plays the soundID sound on call.
     * @param soundID the sound to play
     */
    private void play(int soundID) {
        player.play(soundID, 1, 1, 0, 0, 1);
    }

    /**
     * Creates the ClickListener for touch events.
     */
    private void listenForTouches() {
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
    }
}
