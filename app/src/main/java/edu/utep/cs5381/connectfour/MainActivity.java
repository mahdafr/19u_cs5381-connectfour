package edu.utep.cs5381.connectfour;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import edu.utep.cs5381.connectfour.ui.GameView;
import edu.utep.cs5381.connectfour.game.Runner; //my implementation
import edu.utep.cs5381.connectfour.model.*;
import edu.utep.cs5381.connectfour.ui.GameView.DiscClickListener;

public class MainActivity extends AppCompatActivity {
    //private Runner game;
    private Board game;
    private Player pl1;
    private Player pl2;
    private GameView gameUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameUI = new GameView(this);
        setContentView(gameUI);

        game = new Board();
        gameUI.setBoard(game);
        gameUI.setDiscClickListener(index -> dropDisc(index));
    }

    /**
     * Add a disc in this column for the current player.
     * @param i the column to drop the disc
     */
    private void dropDisc(int i) {
        //todo
        toast("meow :" +i);
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
