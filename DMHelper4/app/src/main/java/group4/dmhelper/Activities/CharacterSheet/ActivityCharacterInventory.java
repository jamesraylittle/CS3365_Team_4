package group4.dmhelper.Activities.CharacterSheet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import group4.dmhelper.Activities.Search.ActivitySearchEquipment;
import group4.dmhelper.Activities.Search.ActivitySearchItems;
import group4.dmhelper.Actors.Equipment;
import group4.dmhelper.Actors.Item;
import group4.dmhelper.Actors.Spell;
import group4.dmhelper.Database.Actors;
import group4.dmhelper.Database.Equipments;
import group4.dmhelper.Database.Items;
import group4.dmhelper.Database.Spells;
import group4.dmhelper.R;

/**
 * Created by Mose
 * Edited by Kyle
 */
public class ActivityCharacterInventory extends AppCompatActivity {

    int PlayerIdentifier;
    public static List<String> itemNames = new ArrayList<>();
    public static List<String> equipmentNames = new ArrayList<>();
    ListView items, equipments;
    ListAdapter adapterItems, adapterEquipment;
    ArrayList<Item> dbItemObjects;
    ArrayList<group4.dmhelper.Actors.Equipment> dbEquipmentObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_inventory);

        Bundle extras = getIntent().getExtras();
        PlayerIdentifier = extras.getInt("Identifier");

        items = (ListView)findViewById(R.id.listView_inventory_IT);
        equipments = (ListView)findViewById(R.id.listView_inventory_EQ);

        items.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {

                final Item selectedItem = dbItemObjects.get(position);
                final Items ss = new Items(getApplicationContext());

                new AlertDialog.Builder(ActivityCharacterInventory.this, R.style.AlertStyle)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete the spell \"" + selectedItem.getItemName() + "\"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ss.delete(selectedItem.getId());
                                refillItemList();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

        equipments.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {

                final Equipment selectedEquipment = dbEquipmentObjects.get(position);
                final Equipments ss = new Equipments(getApplicationContext());

                new AlertDialog.Builder(ActivityCharacterInventory.this, R.style.AlertStyle)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete the spell \"" + selectedEquipment.getEquipmentName() + "\"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ss.delete(selectedEquipment.getId());
                                refillEquipmentList();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refillItemList();
        refillEquipmentList();
    }

    private void refillItemList() {
        itemNames.clear();
        Items db = new Items(getApplicationContext());
        dbItemObjects = db.getAllByPlayerId(PlayerIdentifier);
        for (int i = 0; i < dbItemObjects.size(); i++) {
            itemNames.add(dbItemObjects.get(i).getItemName());
        }
        adapterItems = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_layout, R.id.list_text, itemNames);
        items.setAdapter(adapterItems);
    }

    private void refillEquipmentList() {
        equipmentNames.clear();
        Equipments db = new Equipments(getApplicationContext());
        dbEquipmentObjects = db.getAllByPlayerId(PlayerIdentifier);
        for (int i = 0; i < dbEquipmentObjects.size(); i++) {
            Equipment e = dbEquipmentObjects.get(i);
            if (e.getIsEquipped() == 1) {
                equipmentNames.add(e.getEquipmentName() + "*");
            }
            else {
                equipmentNames.add(e.getEquipmentName());
            }
        }
        adapterEquipment = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_layout, R.id.list_text, equipmentNames);
        equipments.setAdapter(adapterEquipment);
    }

    public void addInventoryItem(View v) {
        Intent intent = new Intent(ActivityCharacterInventory.this, ActivitySearchItems.class);
        intent.putExtra("Identifier", PlayerIdentifier);
        startActivity(intent);
    }

    public void addInventoryEquipment(View v) {
        Intent intent = new Intent(ActivityCharacterInventory.this, ActivitySearchEquipment.class);
        intent.putExtra("Identifier", PlayerIdentifier);
        startActivity(intent);
    }
}
