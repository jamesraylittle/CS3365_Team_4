package group4.dmhelper.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import group4.dmhelper.Activities.ActivityGame;
import group4.dmhelper.Activities.Popups.PopupMonsterInfo;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.R;
import group4.dmhelper.popup_dice_roll;

/**
 * Created by Kyle on 10/13/2015.
 * Edited by Emilie on 11/1/2015.
 * Edited by Emilie on 11/16/2015
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

    private Actor one;
    private Actor two;
    private Actor three;
    private Actor four;
    private Actor five;

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

//        if(monsters != null)
//        {
//            tableLayout.removeAllViews();
//        }
        return view;
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
//                one = new Actor(1,getContext());
                break;
            case 2:
                playerOne.setVisibility(View.VISIBLE);
                playerOne.setOnClickListener(this);
                playerTwo.setVisibility(View.VISIBLE);
                playerTwo.setOnClickListener(this);
//                one = new Actor(1,getContext());
//                two = new Actor(2,getContext());
                break;
            case 3:
                playerOne.setVisibility(View.VISIBLE);
                playerOne.setOnClickListener(this);
                playerTwo.setVisibility(View.VISIBLE);
                playerTwo.setOnClickListener(this);
                playerThree.setVisibility(View.VISIBLE);
                playerThree.setOnClickListener(this);
//                one = new Actor(1,getContext());
//                two = new Actor(2,getContext());
//                three = new Actor(3,getContext());
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
//                one = new Actor(1,getContext());
//                two = new Actor(2,getContext());
//                three = new Actor(3,getContext());
//                four = new Actor(4,getContext());
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
//                one = new Actor(1,getContext());
//                two = new Actor(2,getContext());
//                three = new Actor(3,getContext());
//                four = new Actor(4,getContext());
//                five = new Actor(5,getContext());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        int id;
        switch (v.getId()) {
            case R.id.nextTurn:
                nextTurn(v);
                break;
            case R.id.playerOne:
                intent = new Intent(getContext(), popup_dice_roll.class);
//                id = one.getId();
                id = 1;
                intent.putExtra("Identifier",id);
                startActivity(intent);
                break;
            case R.id.playerTwo:
                intent = new Intent(getContext(), popup_dice_roll.class);
//                id = two.getId();
                id = 2;
                intent.putExtra("Identifier",id);
                startActivity(intent);
                break;
            case R.id.playerThree:
                intent = new Intent(getContext(), popup_dice_roll.class);
//                id = three.getId();
                id = 3;
                intent.putExtra("Identifier",id);
                startActivity(intent);
                break;
            case R.id.playerFour:
                intent = new Intent(getContext(), popup_dice_roll.class);
//                id = four.getId();
                id = 4;
                intent.putExtra("Identifier",id);
                startActivity(intent);
                break;
            case R.id.playerFive:
                intent = new Intent(getContext(), popup_dice_roll.class);
//                id = five.getId();
                id = 5;
                intent.putExtra("Identifier", id);
                startActivity(intent);
                break;
            default :
                intent = new Intent(getContext(), PopupMonsterInfo.class);
                //// TODO: 11/2/2015 Change to monster sheet class
                intent.putExtra("monster_values",monsters.get(v.getId()-1));
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
            button.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
            button.setId(monsters.size());
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
        FragmentFeed feed = (FragmentFeed)getActivity().getSupportFragmentManager().findFragmentByTag(tagName);
        switch (whoseTurn)
        {
            case 1:
                switch (numPlayers){
                    case 1: break;
                    case 2:
                        playerTwo.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                        break;
                    case 3:
                        playerThree.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                        break;
                    case 4:
                        playerFour.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                        break;
                    case 5:
                        playerFive.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                        break;
                }
                playerOne.setBackgroundColor(getContext().getResources().getColor(R.color.highlight));
                feed.addFeed("PlayerOne's Turn");
                break;
            case 2:
                playerOne.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                playerTwo.setBackgroundColor(getContext().getResources().getColor(R.color.highlight));
                feed.addFeed("PlayerTwo's Turn");
                break;
            case 3:
                playerTwo.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                playerThree.setBackgroundColor(getContext().getResources().getColor(R.color.highlight));
                feed.addFeed("PlayerThree's Turn");
                break;
            case 4:
                playerThree.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                playerFour.setBackgroundColor(getContext().getResources().getColor(R.color.highlight));
                feed.addFeed("PlayerFour's Turn");
                break;
            case 5:
                playerFour.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                playerFive.setBackgroundColor(getContext().getResources().getColor(R.color.highlight));
                feed.addFeed("PlayerFive's Turn");
                break;
        }
    }
}