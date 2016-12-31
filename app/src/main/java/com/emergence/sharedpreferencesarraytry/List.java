package com.emergence.sharedpreferencesarraytry;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends Activity{

    public final static String filename = "data"; // define file name for data
    SharedPreferences sharedpreferences;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        sharedpreferences = getSharedPreferences( filename, Context.MODE_PRIVATE); // initialize shared preferences
        listView = (ListView) findViewById(R.id.list);

        final ArrayList arrayList = new ArrayList();

        int i = 1;
        while(sharedpreferences.contains(Integer.toString(i))) {
            arrayList.add(sharedpreferences.getString(Integer.toString(i),""));
            i++;
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                int del_index = itemPosition + 1; // item position starts at 0 whereas shared preference array_index starts at 1
                int last_index = Integer.parseInt(sharedpreferences.getString("array_index","")) - 1; // last index is array_index - 1 since array_index is the index of next entry

                // Show Alert
                Toast.makeText(getApplicationContext(), "Entry #" + del_index + " '" + itemValue + "' deleted!", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedpreferences.edit(); // initialize editor
                while (del_index < last_index) { // replace index to delete's data with data from entry above
                    editor.putString(Integer.toString(del_index), sharedpreferences.getString(Integer.toString(del_index + 1), ""));
                    del_index++;
                }
                editor.remove(Integer.toString(last_index)); // delete last entry
                editor.putString("array_index", Integer.toString(--last_index)); // update array_index
                editor.commit(); // commit changes

                arrayList.remove(itemPosition); // remove entry in array
                listView.setAdapter(adapter); // update UI
            }

        });
    }

}
