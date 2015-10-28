package group4.dmhelper.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.Fragments.FragmentAdapter;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

public class ActivityGame extends AppCompatActivity  {

    private final String tagName = "android:switcher:" + R.id.viewpager + ":" + 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
                ActivityGame.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(3);

        DataBaseHelper myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
    }

    @Override protected void onResume() {
        super.onResume();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data.getStringArrayExtra("feedData").equals(null)) return;
            try {
                FragmentFeed feed = (FragmentFeed)getSupportFragmentManager().findFragmentByTag(tagName);
                feed.addFeed(data.getStringArrayExtra("feedData"));
            }
            catch (Exception e) {}
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this, R.style.AlertStyle)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .setTitle("Leaving Adventure")
                .setMessage("Are you sure you want to leave this adventure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
