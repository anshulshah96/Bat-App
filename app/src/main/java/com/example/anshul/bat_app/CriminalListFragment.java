package com.example.anshul.bat_app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CriminalListFragment extends Fragment implements AdapterView.OnItemClickListener {
    Communicator communicator;
    ListView list;
    CriminalDbHelper cdb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        cdb=new CriminalDbHelper(getActivity());
        View view = inflater.inflate(R.layout.fragment_criminal_list,container,false);
        list= (ListView) view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,cdb.getNames());
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        return view;
    }

    public void setCommunicator(Communicator communicator){
        this.communicator=communicator;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        communicator.respond(position);
    }

    public interface Communicator{
        public void respond(int index);
    }


}
