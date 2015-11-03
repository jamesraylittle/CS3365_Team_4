package group4.dmhelper;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import group4.dmhelper.Activities.ActivitySearchEquipment;
import group4.dmhelper.Activities.ActivitySearchMonsters;

/**
 * Created by Kyle on 11/2/2015.
 */
public class ActivitySearchEquipmentTest extends ActivityInstrumentationTestCase2<ActivitySearchEquipment> {
    public ActivitySearchEquipmentTest() {
        super(ActivitySearchEquipment.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testedittext() {
        EditText et=(EditText)getActivity().findViewById(R.id.editTxt_search_equipment_name);
        assertNotNull(et);
    }

    @SmallTest
    public void testbutton() {
        Button bt = (Button)getActivity().findViewById(R.id.btn_search_equipment);
        assertNotNull(bt);
    }

    @SmallTest
    public void testspinner() {
        Spinner sp = (Spinner)getActivity().findViewById(R.id.spinner_search_equipment_category);
        assertNotNull(sp);
        Spinner sp2 = (Spinner)getActivity().findViewById(R.id.spinner_search_equipment_family);
        assertNotNull(sp2);
        Spinner sp3 = (Spinner)getActivity().findViewById(R.id.spinner_search_equipment_subcategory);
        assertNotNull(sp3);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
