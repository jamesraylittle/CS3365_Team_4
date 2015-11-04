package group4.dmhelper;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import group4.dmhelper.Activities.Search.ActivitySearchMonsters;

/**
 * Created by Kyle on 11/2/2015.
 */
public class ActivitySearchMonsterTest extends ActivityInstrumentationTestCase2<ActivitySearchMonsters> {
    public ActivitySearchMonsterTest() {
        super(ActivitySearchMonsters.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testedittext() {
        EditText et=(EditText)getActivity().findViewById(R.id.editTxt_search_monster_name);
        assertNotNull(et);
        EditText et2=(EditText)getActivity().findViewById(R.id.editTxt_seach_monster_family);
        assertNotNull(et2);
    }

    @SmallTest
    public void testbutton() {
        Button bt = (Button)getActivity().findViewById(R.id.btn_search_monster);
        assertNotNull(bt);
    }

    @SmallTest
    public void testspinner() {
        Spinner sp = (Spinner)getActivity().findViewById(R.id.spinner_search_monster_size);
        assertNotNull(sp);
        Spinner sp2 = (Spinner)getActivity().findViewById(R.id.spinner_search_monster_type);
        assertNotNull(sp2);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
