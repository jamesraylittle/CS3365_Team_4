package group4.dmhelper.Activities.Popups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import group4.dmhelper.Activities.ActivityGame;
import group4.dmhelper.R;

public class NumPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_player);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.4));
    }

    public void one(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 1);
        startActivity(intent);
    }
    public void two(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 2);
        startActivity(intent);
    }
    public void three(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 3);
        startActivity(intent);
    }
    public void four(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 4);
        startActivity(intent);
    }
    public void five(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 5);
        startActivity(intent);
    }
}
