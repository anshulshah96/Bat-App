package com.example.anshul.bat_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
    /**
     * Called when the user clicks the Send button
     */


    public void newRecord(View view) {
        Intent intent = new Intent(this, NewRecordActivity.class);
        startActivity(intent);


    }

    public void viewRecord(View view) {
        Intent intent = new Intent(this, ViewRecordsActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}