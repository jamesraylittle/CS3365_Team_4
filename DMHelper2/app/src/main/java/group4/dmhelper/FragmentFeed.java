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

    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temp, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = ((TextView) getView().findViewById(R.id.tempText));
        text.setText("Game Feed");
    }
}
