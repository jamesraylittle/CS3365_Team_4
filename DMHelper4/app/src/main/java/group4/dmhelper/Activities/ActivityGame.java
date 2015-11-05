package group4.dmhelper.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

import group4.dmhelper.Actors.ClassType;
import group4.dmhelper.Actors.Item;
import group4.dmhelper.Actors.Player;
import group4.dmhelper.Actors.PlayerAbility;
import group4.dmhelper.Actors.Race;
import group4.dmhelper.Actors.Skill;
import group4.dmhelper.Actors.Spell;
import group4.dmhelper.Actors.Equipment;

import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.Database.ClassTypes;
import group4.dmhelper.Database.Items;
import group4.dmhelper.Database.PlayerAbilities;
import group4.dmhelper.Database.Players;
import group4.dmhelper.Database.Races;
import group4.dmhelper.Database.Skills;
import group4.dmhelper.Database.Equipments;

import group4.dmhelper.Fragments.FragmentAdapter;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.Fragments.FragmentGame;
import group4.dmhelper.R;

public class ActivityGame extends AppCompatActivity  {

    private final String tagNameFeed = "android:switcher:" + R.id.viewpager + ":" + 0;
    private final String tagNameGame = "android:switcher:" + R.id.viewpager + ":" + 1;
    private int players;
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

        //get the intent extras here, then send them to the fragment with setters
        Bundle b = getIntent().getExtras();
        int numPlayers = (int) b.get("numplayers");
        if(numPlayers>0 && numPlayers<6)
        {
            players = numPlayers;
        }
    }

    @Override protected void onResume() {
        super.onResume();
        try {
            FragmentFeed feed = (FragmentFeed) getSupportFragmentManager().findFragmentByTag(tagNameFeed);
            FragmentGame game = (FragmentGame) getSupportFragmentManager().findFragmentByTag(tagNameGame);
            feed.updateFeed();

            game.updateMonsters();
        }
        catch (Exception e) {}
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
                        ActivityStart.start.finish();
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public int getNumPlayers()
    {
        return players;
    }
}
