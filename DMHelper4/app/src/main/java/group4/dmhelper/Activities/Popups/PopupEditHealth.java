package group4.dmhelper.Activities.Popups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

public class PopupEditHealth extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_edit_health);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Bundle extras = getIntent().getExtras();
        final String PlayerIdentifier = extras.getString("Identifier");
        //Actor player = new Actor(PlayerIdentifier);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.4));
        Button submit = (Button) findViewById(R.id.btn_submit_health);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Edit players health.
                EditText healthchange = (EditText) findViewById(R.id.text_input_health);
                String text = healthchange.getText().toString();
                if(text != "") {
                    int health = Integer.parseInt(text);
                    //TODO EDIT ACTOR HEALTH

                    //player.setHealth(player.getHealth() + health)
                    FragmentFeed.feedItems.add(PlayerIdentifier + " edited health by: " + health); //TODO ADD PLAYER NAME
                    Toast.makeText(getApplicationContext(),PlayerIdentifier + " edited health by: " + health, Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    //TODO ERROR
                    finish();
                }

            }
        });
    }
}

