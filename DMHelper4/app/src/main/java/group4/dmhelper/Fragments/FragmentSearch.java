package group4.dmhelper.Fragments;

/**
 * Created by Kyle on 10/13/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import group4.dmhelper.Activities.ActivitySearchMonsters;
import group4.dmhelper.Activities.ActivitySearchMonsters;
import group4.dmhelper.Activities.ActivityStart;
import group4.dmhelper.R;


public class FragmentSearch extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Button b1 = (Button) view.findViewById(R.id.btnSearchMonster);
        Button b2 = (Button) view.findViewById(R.id.btnSearchClasses);
        Button b3 = (Button) view.findViewById(R.id.btnSearchEquipment);
        Button b4 = (Button) view.findViewById(R.id.btnSearchFeats);
        Button b5 = (Button) view.findViewById(R.id.btnSearchPowers);
        Button b6 = (Button) view.findViewById(R.id.btnSearchItems);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearchMonster:
                Intent intent = new Intent(getContext(), ActivitySearchMonsters.class);
                startActivity(intent);
                break;
        }
    }
}