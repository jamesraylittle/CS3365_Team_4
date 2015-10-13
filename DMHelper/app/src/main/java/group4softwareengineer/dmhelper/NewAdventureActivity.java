package group4softwareengineer.dmhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class NewAdventureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_adventure);
        Intent intent = getIntent();
//        TextView textView = (TextView) findViewById(R.id.Testtext);
//        textView.setText("testString");
        Bundle b = intent.getExtras();
        if(b!=null)
        {
            int numPlayers = (int)b.get("numPlayers");
//            textView.setText(Integer.toString(numPlayers));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_adventure, menu);
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

    public void addPlayer (View view)
    {
        Intent intent = new Intent(this, CharacterSheet.class);
        startActivity(intent);
    }

    public void addMonster (View view)
    {
        Intent intent = new Intent(this, SearchScreen.class);
        startActivity(intent);
    }

    public void showCharacterSheet (View view)
    {
        Intent intent = new Intent(this, CharacterSheet.class);
        startActivity(intent);
    }
}