package group4.dmhelper.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import group4.dmhelper.Activities.Popups.NumPlayer;
import group4.dmhelper.R;

public class ActivityStart extends AppCompatActivity {

    private Button btnNewGame;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        relativeLayout = (RelativeLayout) findViewById(R.id.rLayout);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityStart.this, NumPlayer.class));
            }
        });
    }

    public void loadAdventure(View view)
    {
        //STEALING THIS BUTTON TO TEST
        //TODO FIX THIS
        Intent intent = new Intent(this, ActivityCharacterSheet.class);
        intent.putExtra("Identifier","1");
        startActivity(intent);
    }
}
