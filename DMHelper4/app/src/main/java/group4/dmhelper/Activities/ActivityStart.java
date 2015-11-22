package group4.dmhelper.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import group4.dmhelper.Activities.Popups.PopupNumPlayer;
import group4.dmhelper.Activities.Popups.PopupSelectGame;
import group4.dmhelper.Actors.Item;
import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.Database.Items;
import group4.dmhelper.R;
import group4.dmhelper.globalVariables;

public class ActivityStart extends AppCompatActivity {

    private Button btnNewGame;
    private RelativeLayout relativeLayout;
    public static Activity start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        relativeLayout = (RelativeLayout) findViewById(R.id.rLayout);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityStart.this, PopupNumPlayer.class));
            }
        });

        start = this;
    }

    public void loadAdventure(View view)
    {
        Intent intent = new Intent(ActivityStart.this, PopupSelectGame.class);
        startActivity(intent);
    }
}
