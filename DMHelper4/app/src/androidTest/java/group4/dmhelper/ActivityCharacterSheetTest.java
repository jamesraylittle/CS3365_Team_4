package group4.dmhelper;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import group4.dmhelper.Activities.ActivityCharacterSheet;

/**
 * Created by Kyle on 11/2/2015.
 */
public class ActivityCharacterSheetTest extends ActivityInstrumentationTestCase2<ActivityCharacterSheet> {
    public ActivityCharacterSheetTest() {
        super(ActivityCharacterSheet.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent;
        intent = new Intent(getInstrumentation().getTargetContext(), ActivityCharacterSheet.class);
        intent.putExtra("Identifier", "PlayerOne");
        setActivityIntent(intent);
    }

    @SmallTest
    public void testedittext() {
        EditText et=(EditText)getActivity().findViewById(R.id.editText_character_name);
        assertNotNull(et);
        EditText et2=(EditText)getActivity().findViewById(R.id.editText_player_name);
        assertNotNull(et2);
        EditText et3=(EditText)getActivity().findViewById(R.id.editText_religion);
        assertNotNull(et3);
        EditText et4=(EditText)getActivity().findViewById(R.id.editText_Height);
        assertNotNull(et4);
        EditText et5=(EditText)getActivity().findViewById(R.id.editText_Weight);
        assertNotNull(et5);
    }

    @SmallTest
    public void testbutton() {
        Button bt = (Button)getActivity().findViewById(R.id.btn_change_health);
        assertNotNull(bt);
        Button bt2 = (Button)getActivity().findViewById(R.id.btn_change_experience);
        assertNotNull(bt2);
        Button bt3 = (Button)getActivity().findViewById(R.id.btn_abilities);
        assertNotNull(bt3);
        Button bt4 = (Button)getActivity().findViewById(R.id.btn_feats);
        assertNotNull(bt4);
        Button bt5 = (Button)getActivity().findViewById(R.id.btn_inventory);
        assertNotNull(bt5);
        Button bt6 = (Button)getActivity().findViewById(R.id.btn_magic);
        assertNotNull(bt6);
        Button bt7 = (Button)getActivity().findViewById(R.id.btn_racialclass);
        assertNotNull(bt7);
        Button bt8 = (Button)getActivity().findViewById(R.id.btn_skills);
        assertNotNull(bt8);
    }

    @SmallTest
    public void testspinner() {
        Spinner sp = (Spinner)getActivity().findViewById(R.id.spinner_search_class);
        assertNotNull(sp);
        Spinner sp2 = (Spinner)getActivity().findViewById(R.id.spinner_search_race);
        assertNotNull(sp2);
        Spinner sp3 = (Spinner)getActivity().findViewById(R.id.spinner_search_alignment);
        assertNotNull(sp3);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
