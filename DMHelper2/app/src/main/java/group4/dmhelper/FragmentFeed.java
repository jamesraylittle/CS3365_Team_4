package group4.dmhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kyle on 10/13/2015.
 */
public class FragmentFeed extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_temporary, container, false);
        ((TextView) rootView.findViewById(android.R.id.text1)).setText("Game Feed");
        return rootView;
    }
}
