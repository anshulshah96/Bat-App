package com.example.anshul.bat_app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.net.URL;


public class CriminalDisplayFragment extends Fragment {
    TextView textView;
    ImageView image;
    CriminalDbHelper cdb;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_criminal_display, container, false);
        textView=(TextView)view.findViewById(R.id.cDispTv);
        image= (ImageView)view.findViewById(R.id.criminalImage);
        cdb=new CriminalDbHelper(getActivity());
        return view;
    }

    public void setText(int index){
        Criminal cr = cdb.getCriminalData(index);
        String gender = cr.isMale()?"Male":"Female";
        textView.setText("Name: "+cr.getName()+"\n"+
                "Age: "+cr.getAge()+"\n"+
                "Gender: "+gender+"\n"+
                "Crimes: "+cr.getCrime()+"\n"+
                "Last Seen Location: "+cr.getLastSeenLocation());

        Log.e("URI: ", cr.getImageURI());
        setImage(cr.getImageURI());

    }

    public void setImage(String uri){
        if(!uri.equalsIgnoreCase("null")){

            try {
            image.setImageURI(Uri.parse(uri));
            }
             catch (Exception e){
               Log.e("Image: ",e.getMessage());
             }

        }

    }

}
