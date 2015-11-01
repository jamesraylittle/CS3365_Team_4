package group4.dmhelper.Activities.Popups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import group4.dmhelper.R;

public class PopupEditExperience extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_edit_experience);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.4));
        Button submit = (Button) findViewById(R.id.btn_submit_experience);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Edit players health.
                finish();
            }
        });
    }
}
