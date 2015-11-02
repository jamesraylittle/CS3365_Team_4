package group4.dmhelper.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import group4.dmhelper.R;

/**
 * Created by Kyle on 10/13/2015.
 * Edited by Emilie on 11/1/2015.
 */
public class FragmentGame extends Fragment {

    public int numPlayers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        numPlayers = (int)getArguments().get("numplayers");
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}