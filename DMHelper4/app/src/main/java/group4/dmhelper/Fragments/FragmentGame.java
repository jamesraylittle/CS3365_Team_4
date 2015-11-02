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
import android.widget.Toast;

import group4.dmhelper.Activities.ActivityCharacterSheet;
import group4.dmhelper.Activities.ActivityGame;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/13/2015.
 * Edited by Emilie on 11/1/2015.
 */
public class FragmentGame extends Fragment implements View.OnClickListener{

    public int numPlayers;
    public TableLayout tableLayout;
    public int whoseTurn;

    public Button playerOne;
    public Button playerTwo;
    public Button playerThree;
    public Button playerFour;
    public Button playerFive;
    public Button nextTurn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_game, container, false);

        playerOne = (Button)view.findViewById(R.id.playerOne);
        playerTwo = (Button)view.findViewById(R.id.playerTwo);
        playerThree = (Button)view.findViewById(R.id.playerThree);
        playerFour = (Button)view.findViewById(R.id.playerFour);
        playerFive = (Button)view.findViewById(R.id.playerFive);
        nextTurn = (Button) view.findViewById(R.id.nextTurn);
        nextTurn.setOnClickListener(this);



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
        Toast.makeText(getContext().getApplicationContext(), "Number of players: "+numPlayers, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        //FragmentFeed feed = (FragmentFeed)getActivity().getSupportFragmentManager().findFragmentByTag(tagName);
        //feed.addFeed("Search monsters clicked");
        switch (v.getId()) {
            case R.id.nextTurn:
                nextTurn(v);
                break;
            case R.id.playerOne:
                intent = new Intent(getContext(), ActivityCharacterSheet.class);
                intent.putExtra("Identifier","PlayerOne");
                startActivity(intent);
                break;
            case R.id.playerTwo:
                intent = new Intent(getContext(), ActivityCharacterSheet.class);
                intent.putExtra("Identifier","PlayerTwo");
                startActivity(intent);
                break;
            case R.id.playerThree:
                intent = new Intent(getContext(), ActivityCharacterSheet.class);
                intent.putExtra("Identifier","PlayerThree");
                startActivity(intent);
                break;
            case R.id.playerFour:
                intent = new Intent(getContext(), ActivityCharacterSheet.class);
                intent.putExtra("Identifier","PlayerFour");
                startActivity(intent);
                break;
            case R.id.playerFive:
                intent = new Intent(getContext(), ActivityCharacterSheet.class);
                intent.putExtra("Identifier","PlayerFive");
                startActivity(intent);
                break;
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
                break;
            case 2:


                playerOne.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                playerTwo.setBackgroundColor(getContext().getResources().getColor(R.color.highlight));
                break;
            case 3:
                playerTwo.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                playerThree.setBackgroundColor(getContext().getResources().getColor(R.color.highlight));
                break;
            case 4:
                playerThree.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                playerFour.setBackgroundColor(getContext().getResources().getColor(R.color.highlight));
                break;
            case 5:
                playerFour.setBackgroundColor(getContext().getResources().getColor(R.color.colorButton));
                playerFive.setBackgroundColor(getContext().getResources().getColor(R.color.highlight));
                break;
        }


    }
}