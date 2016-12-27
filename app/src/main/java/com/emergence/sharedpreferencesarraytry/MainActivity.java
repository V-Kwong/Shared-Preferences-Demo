package com.emergence.sharedpreferencesarraytry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    public final static String filename = "data"; // define file name for data
    SharedPreferences sharedpreferences;
    TextView input;
    TextView key;
    TextView output;
    //Button listBtn;
    int array_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //input = (TextView) findViewById(R.id.input); // initialize input as input edit text box
        //key = (TextView) findViewById(R.id.key); // initialize key as key edit text box
        //listBtn = (Button) findViewById(R.id.list); // initialize listBtn as List of Entries Button
        sharedpreferences = getSharedPreferences( filename, Context.MODE_PRIVATE); // initialize shared preferences
       // listBtn.setOnClickListener(new View.OnClickListener() {
       //     public void onClick(View v) {
       //         go_to_list();
       //     }
       // });
    }

    public void submit(View view){
        input = (TextView) findViewById(R.id.input); // initialize input as input edit text box
        String data = input.getText().toString(); // get string in input edit text box
        if (data.equals("")) {
            return;
        }
        SharedPreferences.Editor editor = sharedpreferences.edit(); // initialize editor
        editor.putString( Integer.toString(array_index), data); // key is array index and data is input
        editor.commit(); // commit changes
        input.setText(""); // clear input edit text box
        array_index++; // increase array index
    }

    public void retrieve(View view) {
        key = (TextView) findViewById(R.id.key); // initialize key as key edit text box
        output = (TextView) findViewById(R.id.output); // initialize output as output text box
        String output_key = key.getText().toString(); // get string in output key edit text box
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
        output.setText("All entries cleared!"); // display to notify user
        array_index = 0;
    }

    public void go_to_list(View view) {
        Intent intent = new Intent(this, List.class);
        startActivity(intent);
    }
}
