package group4.dmhelper.Activities.Popups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

/**
 * created by Mose
 */
public class PopupEditExperience extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_edit_experience);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Bundle extras = getIntent().getExtras();
        final int PlayerIdentifier = extras.getInt("Identifier");
        Actor player = new Actor(PlayerIdentifier,getApplicationContext());
        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.3));
        Button submit = (Button) findViewById(R.id.btn_submit_experience);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText xpchange = (EditText) findViewById(R.id.change_experience);
                String text = xpchange.getText().toString();
                if(!text.equals("")) {
                    int xp = Integer.parseInt(text);
                    Actor player = new Actor(PlayerIdentifier,getApplicationContext());
                    player.setXP(player.getXP() + xp);
                    FragmentFeed.feedItems.add(player.getPlayerName() + " edited experience by: " + xp);
                    Toast.makeText(getApplicationContext(),player.getPlayerName() + " edited experience by: " + xp, Toast.LENGTH_LONG).show();
                    player.pushToDatabase();
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
