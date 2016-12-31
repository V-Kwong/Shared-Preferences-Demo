package com.emergence.sharedpreferencesarraytry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public final static String filename = "data"; // define file name for data
    SharedPreferences sharedpreferences;
    TextView input;
    TextView key;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences( filename, Context.MODE_PRIVATE); // initialize shared preferences

        SharedPreferences.Editor editor = sharedpreferences.edit(); // initialize editor
        if (!sharedpreferences.contains("array_index")) { // if array_index doesn't exist/start up of application, array_index's data is 1
            editor.putString("array_index", "1");
            editor.commit();
        }

    }

    public void submit(View view){

        SharedPreferences.Editor editor = sharedpreferences.edit(); // initialize editor
        if (!(sharedpreferences.contains("array_index"))) { // if array_index doesn't exist/start up of application, array_index's data is 1
            editor.putString("array_index", "1");
            editor.commit();
        }

        input = (TextView) findViewById(R.id.input); // initialize input as input edit text box
        String data = input.getText().toString(); // get string in input edit text box
        if (data.equals("")) { // doesn't allow user to submit empty text box entry by accident
            return;
        }

        editor.putString( sharedpreferences.getString("array_index", ""), data); // key is array_index's data and data is input
        editor.commit(); // commit changes

        input.setText(""); // clear input edit text box

        int index = Integer.parseInt(sharedpreferences.getString("array_index","")); // retrieve array_index's data
        index++; // increment index variable
        editor.putString("array_index", Integer.toString(index)); // use index variable to increment array_index's data
        editor.commit(); // commit changes

        Toast.makeText(getApplicationContext(),"Entry #" + Integer.toString(index - 1) + " submitted!", Toast.LENGTH_SHORT).show(); // notify user that entry has been submitted

    }

    public void retrieve(View view) {
        key = (TextView) findViewById(R.id.key); // initialize key as key edit text box
        output = (TextView) findViewById(R.id.output); // initialize output as output text box
        String output_key = key.getText().toString(); // get string in output key edit text box
        output_key = output_key.replaceAll("[^0-9]", "");; // gets rid of any non 0-9 characters in input
        if (sharedpreferences.contains(output_key)) { // print out data corresponding to key to output text box if it exists
            output.setText(sharedpreferences.getString(output_key, ""));
        }
        else
            output.setText("Data for the corresponding key does not exist!");
    }

    public void clear(View view) {
        SharedPreferences.Editor editor = sharedpreferences.edit(); // initialize editor
        editor.clear(); // clear all memory
        editor.commit(); // commit changes
        Toast.makeText(getApplicationContext(), "All entries cleared!", Toast.LENGTH_SHORT).show(); // display toast to notify user
    }

    public void go_to_list(View view) {
        Intent intent = new Intent(this, List.class);
        startActivity(intent);
    }
}
