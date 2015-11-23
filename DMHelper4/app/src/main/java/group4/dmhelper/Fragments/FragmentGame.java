package group4.dmhelper.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import group4.dmhelper.Activities.ActivityGame;
import group4.dmhelper.Activities.Popups.PopupDiceRoller;
import group4.dmhelper.Activities.Popups.PopupMonsterInfo;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/13/2015.
 * Edited by Emilie on 11/1/2015.
 */
public class FragmentGame extends Fragment implements View.OnClickListener{

    private final String tagName = "android:switcher:" + R.id.viewpager + ":" + 0;

    public int numPlayers;
    public TableLayout tableLayout;
    public int whoseTurn;

    public Button playerOne;
    public Button playerTwo;
    public Button playerThree;
    public Button playerFour;
    public Button playerFive;
    public Button nextTurn;

    public static ArrayList<String[]> monsters = new ArrayList<String[]>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_game, container, false);
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);
        playerOne = (Button)view.findViewById(R.id.playerOne);
        playerTwo = (Button)view.findViewById(R.id.playerTwo);
        playerThree = (Button)view.findViewById(R.id.playerThree);
        playerFour = (Button)view.findViewById(R.id.playerFour);
        playerFive = (Button)view.findViewById(R.id.playerFive);
        nextTurn = (Button) view.findViewById(R.id.nextTurn);
        nextTurn.setOnClickListener(this);


        numPlayers = ((ActivityGame) getActivity()).getNumPlayers();
        switch (numPlayers) {
            case 5:
                setBackgroundPlayer(4,playerFive);
            case 4:
                setBackgroundPlayer(3,playerFour);
            case 3:
                setBackgroundPlayer(2,playerThree);
            case 2:
                setBackgroundPlayer(1,playerTwo);
            case 1:
                setBackgroundPlayer(0,playerOne);
        }
//        if(monsters != null)
//        {
//            tableLayout.removeAllViews();
//        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        numPlayers = ((ActivityGame) getActivity()).getNumPlayers();
        switch (numPlayers) {
            case 5:
                setBackgroundPlayer(4,playerFive);
            case 4:
                setBackgroundPlayer(3,playerFour);
            case 3:
                setBackgroundPlayer(2,playerThree);
            case 2:
                setBackgroundPlayer(1,playerTwo);
            case 1:
                setBackgroundPlayer(0,playerOne);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity parent = getActivity();
        if(parent instanceof ActivityGame)
        {
            numPlayers = ((ActivityGame) parent).getNumPlayers();
        }
        whoseTurn = 1;
        switch (numPlayers)
        {
            case 1:
                playerOne.setVisibility(View.VISIBLE);
                playerOne.setOnClickListener(this);

                break;
            case 2:
                playerOne.setVisibility(View.VISIBLE);
                playerOne.setOnClickListener(this);
                playerTwo.setVisibility(View.VISIBLE);
                playerTwo.setOnClickListener(this);
                break;
            case 3:
                playerOne.setVisibility(View.VISIBLE);
                playerOne.setOnClickListener(this);
                playerTwo.setVisibility(View.VISIBLE);
                playerTwo.setOnClickListener(this);
                playerThree.setVisibility(View.VISIBLE);
                playerThree.setOnClickListener(this);
                break;
            case 4:
                playerOne.setVisibility(View.VISIBLE);
                playerOne.setOnClickListener(this);
                playerTwo.setVisibility(View.VISIBLE);
                playerTwo.setOnClickListener(this);
                playerThree.setVisibility(View.VISIBLE);
                playerThree.setOnClickListener(this);
                playerFour.setVisibility(View.VISIBLE);
                playerFour.setOnClickListener(this);
                break;
            case 5:
                playerOne.setVisibility(View.VISIBLE);
                playerOne.setOnClickListener(this);
                playerTwo.setVisibility(View.VISIBLE);
                playerTwo.setOnClickListener(this);
                playerThree.setVisibility(View.VISIBLE);
                playerThree.setOnClickListener(this);
                playerFour.setVisibility(View.VISIBLE);
                playerFour.setOnClickListener(this);
                playerFive.setVisibility(View.VISIBLE);
                playerFive.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.nextTurn:
                nextTurn(v);
                break;
            case R.id.playerOne:
                intent = new Intent(getContext(), PopupDiceRoller.class);
                intent.putExtra("Identifier", ((ActivityGame) getActivity()).getPlayerId(0));
                startActivity(intent);
                break;
            case R.id.playerTwo:
                intent = new Intent(getContext(), PopupDiceRoller.class);
                intent.putExtra("Identifier", ((ActivityGame) getActivity()).getPlayerId(1));
                startActivity(intent);
                break;
            case R.id.playerThree:
                intent = new Intent(getContext(), PopupDiceRoller.class);
                intent.putExtra("Identifier", ((ActivityGame) getActivity()).getPlayerId(2));
                startActivity(intent);
                break;
            case R.id.playerFour:
                intent = new Intent(getContext(), PopupDiceRoller.class);
                intent.putExtra("Identifier", ((ActivityGame) getActivity()).getPlayerId(3));
                startActivity(intent);
                break;
            case R.id.playerFive:
                intent = new Intent(getContext(), PopupDiceRoller.class);
                intent.putExtra("Identifier", ((ActivityGame) getActivity()).getPlayerId(4));
                startActivity(intent);
                break;
            default :
                intent = new Intent(getContext(), PopupMonsterInfo.class);
                //// TODO: 11/2/2015 Change to monster sheet class
                intent.putExtra("monster_values", monsters.get(v.getId()));
                startActivity(intent);
                break;
        }
    }

    public void updateMonsters()
    {
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableRowParams.setMargins(0,10,0,0);
        int count = tableLayout.getChildCount();
        for(int i=0;i<count;i++)
        {
            View child = tableLayout.getChildAt(i);
            if(child instanceof TableRow)
            {
                ((ViewGroup)child).removeAllViews();
            }
        }
        for(int i=0;i<monsters.size();i++)
        {
            TableRow tableRow = new TableRow(getContext());
            tableRow.setLayoutParams(tableRowParams);
            Button button = new Button(getContext());
            button.setText(monsters.get(i)[1]);
            button.setTextColor(Color.WHITE);
            button.setTextSize(9);
            button.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(300, 225);
            //layoutParams.setMargins(0,15,0,0);
            button.setLayoutParams(layoutParams);
            String image = monsters.get(i)[4];
            image = image.replace(" ", "_");
            image = image.toLowerCase();
            int imageResource = getResources().getIdentifier(image, "drawable", getContext().getPackageName());
            button.setBackgroundResource(imageResource);
            button.setId(i);
            tableRow.addView(button);
            tableLayout.addView(tableRow, monsters.size());
            button.setOnClickListener(this);
        }

    }

    public void nextTurn(View view)
    {

        if(whoseTurn == numPlayers)
        {
            whoseTurn = 1;
        }
        else
        {
            whoseTurn++;
        }

        switch (whoseTurn)
        {
            case 1:
                switch (numPlayers){
                    case 1: break;
                    case 2:
                        setBackgroundPlayer(1,playerTwo);
                        break;
                    case 3:
                        setBackgroundPlayer(2, playerThree);
                        break;
                    case 4:
                        setBackgroundPlayer(3, playerFour);
                        break;
                    case 5:
                        setBackgroundPlayer(4, playerFive);
                        break;
                }
                setBackgroundPlayerHighlighted(0, playerOne);
                break;
            case 2:
                setBackgroundPlayer(0, playerOne);
                setBackgroundPlayerHighlighted(1, playerTwo);
                break;
            case 3:
                setBackgroundPlayer(1, playerTwo);
                setBackgroundPlayerHighlighted(2, playerThree);
                break;
            case 4:
                setBackgroundPlayer(2, playerThree);
                setBackgroundPlayerHighlighted(3, playerFour);
                break;
            case 5:
                setBackgroundPlayer(3, playerFour);
                setBackgroundPlayerHighlighted(4, playerFive);
                break;
        }
    }

    public void setBackgroundPlayer(int PlayerID, Button button) {
        Actor player = new Actor(((ActivityGame) getActivity()).getPlayerId(PlayerID),getContext());
        String image = player.getImageFile();
        String name = player.getPlayerName();
        if (image != null) {
            int imageResource = getResources().getIdentifier(image, "drawable", getContext().getPackageName());
            button.setBackgroundResource(imageResource);
        }
        else {
            button.setBackgroundResource(R.drawable.player1);
        }
        button.setText(name);
    }
    public void setBackgroundPlayerHighlighted(int PlayerID, Button button) {
        Actor player = new Actor(((ActivityGame) getActivity()).getPlayerId(PlayerID),getContext());
        String image = player.getImageFile();
        String name = player.getPlayerName();
        if (image != null) {
            image = image.concat("h");
            int imageResource = getResources().getIdentifier(image, "drawable", getContext().getPackageName());
            button.setBackgroundResource(imageResource);
        }
        else {
            button.setBackgroundResource(R.drawable.player1);
        }
        button.setText(name);
        FragmentFeed feed = (FragmentFeed)getActivity().getSupportFragmentManager().findFragmentByTag(tagName);
        feed.addFeed(player.getPlayerName()+"'s Turn");
    }
}