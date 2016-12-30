package com.emergence.sharedpreferencesarraytry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class List extends Activity{

    public final static String filename = "data"; // define file name for data
    SharedPreferences sharedpreferences;
    TextView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        sharedpreferences = getSharedPreferences( filename, Context.MODE_PRIVATE); // initialize shared preferences
        list = (TextView) findViewById(R.id.listbox);

        int i = 1;

        while (sharedpreferences.contains(Integer.toString(i))) {
            list.append(Integer.toString(i) + ".   " + sharedpreferences.getString(Integer.toString(i),"") + "\n");
            i++;
        }

    }

    public void delete(View view) {
        SharedPreferences.Editor editor = sharedpreferences.edit();

        int last_index = 0;

        while(sharedpreferences.contains(Integer.toString(last_index))) {
            last_index++;
        }
        last_index--;

        // code to change delete data and replace it with the data from the last index

        editor.remove(Integer.toString(last_index));
        editor.commit();
    }

}
