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
import android.widget.Toast;

import group4.dmhelper.Activities.ActivitySearchFeats;
import group4.dmhelper.Activities.ActivitySearchItems;
import group4.dmhelper.Activities.ActivitySearchMonsters;
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
        Intent intent;
        switch (v.getId()) {
            case R.id.btnSearchMonster:
                intent = new Intent(getContext(), ActivitySearchMonsters.class);
                startActivity(intent);
                break;
            case R.id.btnSearchItems:
                intent = new Intent(getContext(), ActivitySearchItems.class);
                startActivity(intent);
                break;
            case R.id.btnSearchEquipment:
                Toast.makeText(getContext().getApplicationContext(), "Non Functional", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnSearchPowers:
                Toast.makeText(getContext().getApplicationContext(), "Non Functional", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnSearchClasses:
                Toast.makeText(getContext().getApplicationContext(), "Non Functional", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnSearchFeats:
                intent = new Intent(getContext(), ActivitySearchFeats.class);
                startActivity(intent);
                break;
        }
    }
}