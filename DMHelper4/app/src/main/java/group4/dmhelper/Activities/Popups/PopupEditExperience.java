package group4.dmhelper.Activities.Popups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

public class PopupEditExperience extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_edit_experience);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Bundle extras = getIntent().getExtras();
        final String PlayerIdentifier = extras.getString("Identifier");
        //Actor player = new Actor(PlayerIdentifier);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.4));
        Button submit = (Button) findViewById(R.id.btn_submit_experience);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Edit players health.
                EditText xpchange = (EditText) findViewById(R.id.change_experience);
                String text = xpchange.getText().toString();
                if(text != "") {
                    int xp = Integer.parseInt(text);
                    //TODO EDIT ACTOR XP
                    //player.setXP(player.getxp() + xp)
                    FragmentFeed.feedItems.add("Player"+  PlayerIdentifier + "edited experience by: " + xp); //TODO ADD PLAYER NAME
                    Toast.makeText(getApplicationContext(), "Player" + PlayerIdentifier + "edited experience by: " + xp, Toast.LENGTH_LONG).show();
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
