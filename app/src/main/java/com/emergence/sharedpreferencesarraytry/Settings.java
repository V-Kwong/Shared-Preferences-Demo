package com.emergence.sharedpreferencesarraytry;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends Activity{
    public final static String filename = "data"; // define file name for data
    SharedPreferences sharedpreferences;
    EditText name;
    EditText timeInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedpreferences = getSharedPreferences( filename, Context.MODE_PRIVATE); // initialize shared preferences

        name = (EditText) findViewById(R.id.name);
        timeInterval = (EditText) findViewById(R.id.timeInterval);

        if(sharedpreferences.contains("user_name")){
            name.setText(sharedpreferences.getString("user_name",""));
        }

        if(sharedpreferences.contains("timeInterval")) {
            timeInterval.setText(sharedpreferences.getString("timeInterval",""));
        }

    }

    public void save(View view){

        SharedPreferences.Editor editor = sharedpreferences.edit();

        name = (EditText) findViewById(R.id.name);
        timeInterval = (EditText) findViewById(R.id.timeInterval);

        String user_name = name.getText().toString();

        String time = timeInterval.getText().toString();
        time = time.replaceAll("[^0-9]", "");; // gets rid of any non 0-9 characters in input

        editor.putString("user_name", user_name);
        editor.putString("timeInterval", time);
        editor.commit();

        Toast.makeText(getApplicationContext(),"Settings saved!",Toast.LENGTH_SHORT).show();

    }

}
