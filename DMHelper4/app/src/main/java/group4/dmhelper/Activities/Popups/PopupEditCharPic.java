package group4.dmhelper.Activities.Popups;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import group4.dmhelper.Activities.ActivityGame;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.R;

/**
 * Created by Kyle
 */
public class PopupEditCharPic extends AppCompatActivity {
    private int PlayerIdentifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_user_pictures);

        Bundle extras = getIntent().getExtras();
        PlayerIdentifier = extras.getInt("Identifier");
        

        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        Resources r = getResources();
        int pxW = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, r.getDisplayMetrics());
        int pxH = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 275, r.getDisplayMetrics());
        getWindow().setLayout(pxW, pxH); //pxW = 280dp in pixels, pxH = 200dp in pixels for screen

        
    }
    //Onclick listener
    public void setPlayerImage(View view) {
        Actor player = new Actor(PlayerIdentifier,getApplicationContext());
        ImageButton image = (ImageButton) view;
        Object tag = image.getTag();
        if(tag != null) {
            player.setImageFile((String) tag);
            player.pushToDatabase();
        }
        finish();
    }
}
