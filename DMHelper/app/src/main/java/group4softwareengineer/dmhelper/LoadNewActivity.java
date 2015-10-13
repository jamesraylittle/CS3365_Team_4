package group4softwareengineer.dmhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class LoadNewActivity extends AppCompatActivity {

    public final static String NUM_PLAYERS = "group4softwareengineer.dmhelper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_new);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newAdventure(View view)
    {
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
        }
        Intent intent = new Intent(this, NewAdventureActivity.class);
        intent.putExtra("numPlayers", numPlayers);
        startActivity(intent);
    }
}