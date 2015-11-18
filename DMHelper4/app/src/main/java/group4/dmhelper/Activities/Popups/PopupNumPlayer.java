package group4.dmhelper.Activities.Popups;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import group4.dmhelper.Activities.ActivityGame;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.R;

public class PopupNumPlayer extends AppCompatActivity {

    private static RadioGroup g;
    private static RadioButton rb;
    private static EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_num_player);
        g = (RadioGroup)findViewById(R.id.radio_select_num);
        e = (EditText)findViewById(R.id.editText_start_adventure_name);

        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        Resources r = getResources();
        int pxW = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, r.getDisplayMetrics());
        int pxH = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
        getWindow().setLayout(pxW, pxH); //pxW = 280dp in pixels, pxH = 200dp in pixels for screen
    }

    public void startNewAdventure(View view)
    {
        int selected_id = g.getCheckedRadioButtonId();
        if (selected_id == -1) {
            Toast.makeText(getApplicationContext(), "Please select number of players", Toast.LENGTH_SHORT).show();
            return;
        }
        rb = (RadioButton)findViewById(selected_id);
        if (e.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter an adventure name", Toast.LENGTH_SHORT).show();
            return;
        }
        int numPlayers = Integer.parseInt(rb.getText().toString());
        int[] playerIds = new int[numPlayers];
        for (int i = 0; i < numPlayers; i ++)
        {
            Actor player = new Actor(getApplicationContext());
            playerIds[i] = player.getId();
        }
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", numPlayers);
        intent.putExtra("playerids", playerIds);

        startActivity(intent);
        finish();
    }
}
