package group4.dmhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Kyle on 10/14/2015.
 */
public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void newAdventure(View view)
    {
        /*
        int numPlayers;
        EditText editText = (EditText)findViewById(R.id.number_players);
        try {
            numPlayers = Integer.parseInt(editText.getText().toString());
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a number 1-5", Toast.LENGTH_LONG).show();
            return;
        }
        if (numPlayers < 1 || numPlayers > 5) {
            Toast.makeText(getApplicationContext(), "Please enter a number 1-5", Toast.LENGTH_LONG).show();
            return;
        }*/
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("numPlayers", numPlayers);
        startActivity(intent);
    }
}
