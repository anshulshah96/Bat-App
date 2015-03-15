package com.example.anshul.bat_app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.anshul.bat_app.R.id.editText2;


public class NewRecordActivity extends ActionBarActivity {
    private static EditText nameText;
    private static EditText ageText;
    private static EditText crimes;
    private static EditText location;
    private static Button gender;
    private static ImageView criminalImage;
    private static String imageURI="blank";
    private static boolean isMale=true;
    private static final int SELECT_PICTURE = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        nameText = (EditText) findViewById(R.id.editText);
        ageText = (EditText) findViewById(editText2);
        crimes = (EditText) findViewById(R.id.editText3);
        location = (EditText) findViewById(R.id.editText4);
        gender = (Button) findViewById(R.id.radioButton);
        criminalImage = (ImageView) findViewById(R.id.imageView);

    }

    public void selectImage(View view){
        Intent intent;
      //  intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_PICTURE);
        intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
               // imageURI = getPath(selectedImageUri);
                imageURI = selectedImageUri.toString();
                System.out.println("Image Path : " + imageURI);
                criminalImage.setVisibility(View.VISIBLE);
                criminalImage.setImageURI(null);
                criminalImage.setImageURI(selectedImageUri);
            }
        }
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void saveRecord(View view) {

        try {

            String name= nameText.getText().toString();
            int age=0;
            age = Integer.parseInt(ageText.getText().toString());
            String lastSeenLocation = location.getText().toString();
            String crimesText =crimes.getText().toString();
            if(name.length()>0&&age>0&&crimesText.length()>0&&lastSeenLocation.length()>0) {
                Log.i("button",isMale?"Male":"Female");
                Criminal cr = new Criminal(name, age, isMale, crimesText, lastSeenLocation,imageURI);
                CriminalDbHelper cdb = new CriminalDbHelper(this);
                long newRowId = cdb.updateData(cr);


                Toast.makeText(this, "Record Saved: "+newRowId,
                        Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Please fill all the entries.",
                        Toast.LENGTH_SHORT).show();

            }

        }
        catch(Exception e){
            Toast.makeText(this, "Enter valid inputs",
                    Toast.LENGTH_SHORT).show();
        }


    }

    public void setMale(View view) {
        isMale=true;
    }
    public void setFemale(View view) {
        isMale=false;
    }
}
