package com.example.anshul.bat_app;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class CriminalDisplayActivity extends Activity {
//Launched During Portrait Mode
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_display);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        CriminalDisplayFragment f2 = (CriminalDisplayFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        if(f2!=null) {
            f2.setText(index);
        }
        }

}
