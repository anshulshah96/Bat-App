package com.example.anshul.bat_app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class ViewRecordsActivity extends Activity implements CriminalListFragment.Communicator{
    CriminalListFragment f1;
    CriminalDisplayFragment f2;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        manager=getFragmentManager();
        f1=(CriminalListFragment) manager.findFragmentById(R.id.fragment);
        f1.setCommunicator(this);

    }


    @Override
    public void respond(int index) {
        f2= (CriminalDisplayFragment) manager.findFragmentById(R.id.fragment2);
        if(f2!=null && f2.isVisible()){
            f2.setText(index);

        }
        else{
            Intent intent = new Intent(this,CriminalDisplayActivity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }
    }
}
