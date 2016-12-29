package com.emergence.sharedpreferencesarraytry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class List extends Activity{

    public final static String filename = "data"; // define file name for data
    SharedPreferences sharedpreferences;
    TextView list;
    LinearLayout LinearLayout;
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        sharedpreferences = getSharedPreferences( filename, Context.MODE_PRIVATE); // initialize shared preferences
        // list = (TextView) findViewById(R.id.listbox); // list is the text box
        LinearLayout = (LinearLayout) findViewById(R.id.linearLayout); // LinearLayout refers to the linear layout

        int i = 0;

        while (sharedpreferences.contains(Integer.toString(i))) {
            //list.append(Integer.toString(i + 1) + ".   " + sharedpreferences.getString(Integer.toString(i),""));

            list = new TextView(this); // print out each entry
            list.setText(Integer.toString(i + 1) + ".   " + sharedpreferences.getString(Integer.toString(i),""));
            list.setId(i);
            LinearLayout.addView(list);

            deleteBtn = new Button(this); // print out button for each entry
            deleteBtn.setText("Delete");
            deleteBtn.setId(i);
            LinearLayout.addView(deleteBtn);

            //list.append("\n");
            i++;
        }

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //delete(deleteBtn);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                int last_index = 0;

                while(sharedpreferences.contains(Integer.toString(last_index))) {
                    last_index++;
                }
                last_index--;

                // code to delete selected data and replace it with the data from the last index
                int delIndex = deleteBtn.getId();

                while(delIndex < last_index) {
                    editor.putString(Integer.toString(delIndex),sharedpreferences.getString(Integer.toString(delIndex + 1),""));
                    delIndex++;
                }

                editor.remove(Integer.toString(last_index));
                editor.commit();
                LinearLayout.invalidate();
                //notifyDataSetChanged();
            }
        });

    }

    public void delete(View view) {
        SharedPreferences.Editor editor = sharedpreferences.edit();

        int last_index = 0;

        while(sharedpreferences.contains(Integer.toString(last_index))) {
            last_index++;
        }
        last_index--;

        // code to delete selected data and replace it with the data from the last index
        int delIndex = deleteBtn.getId();

        while(delIndex < last_index) {
            editor.putString(Integer.toString(delIndex),sharedpreferences.getString(Integer.toString(delIndex + 1),""));
            delIndex++;
        }

        editor.remove(Integer.toString(last_index));
        editor.commit();
        LinearLayout.invalidate();
        //notifyDataSetChanged();
    }

}
