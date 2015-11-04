package group4.dmhelper.Activities.Popups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import group4.dmhelper.Activities.ActivityGame;
import group4.dmhelper.R;

public class PopupNumPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_num_player);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.4));
    }

    public void one(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 1);
        startActivity(intent);
        finish();
    }
    public void two(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 2);
        startActivity(intent);
        finish();
    }
    public void three(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 3);
        startActivity(intent);
        finish();
    }
    public void four(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 4);
        startActivity(intent);
        finish();
    }
    public void five(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("numplayers", 5);
        startActivity(intent);
        finish();
    }
}
