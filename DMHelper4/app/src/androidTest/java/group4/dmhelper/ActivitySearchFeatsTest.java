package group4.dmhelper;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import group4.dmhelper.Activities.Search.ActivitySearchFeats;

/**
 * Created by Kyle on 11/2/2015.
 */
public class ActivitySearchFeatsTest extends ActivityInstrumentationTestCase2<ActivitySearchFeats> {
    public ActivitySearchFeatsTest() {
        super(ActivitySearchFeats.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testedittext() {
        EditText et=(EditText)getActivity().findViewById(R.id.editText_search_feat_name);
        assertNotNull(et);
    }

    @SmallTest
    public void testbutton() {
        Button bt = (Button)getActivity().findViewById(R.id.button_search_feat);
        assertNotNull(bt);
    }

    @SmallTest
    public void testspinner() {
        Spinner sp = (Spinner)getActivity().findViewById(R.id.spinner_search_feat_type);
        assertNotNull(sp);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
