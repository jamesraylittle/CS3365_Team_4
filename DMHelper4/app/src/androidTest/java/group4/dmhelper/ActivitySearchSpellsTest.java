package group4.dmhelper;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import group4.dmhelper.Activities.Search.ActivitySearchSpells;

/**
 * Created by Kyle on 11/2/2015.
 */
public class ActivitySearchSpellsTest extends ActivityInstrumentationTestCase2<ActivitySearchSpells> {
    public ActivitySearchSpellsTest() {
        super(ActivitySearchSpells.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testedittext() {
        EditText et=(EditText)getActivity().findViewById(R.id.editTxt_search_spell_name);
        assertNotNull(et);
    }

    @SmallTest
    public void testbutton() {
        Button bt = (Button)getActivity().findViewById(R.id.btn_search_spell);
        assertNotNull(bt);
    }

    @SmallTest
    public void testspinner() {
        Spinner sp = (Spinner)getActivity().findViewById(R.id.spinner_search_spell_subschool);
        assertNotNull(sp);
        Spinner sp2 = (Spinner)getActivity().findViewById(R.id.spinner_search_spell_school);
        assertNotNull(sp2);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
