package com.example.anshul.bat_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class CriminalDbHelper extends SQLiteOpenHelper {

    public static ArrayList<String> crNames;
    public static ArrayList<Criminal> crData;
    public static final String TABLE_NAME = "records_table";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_CRIMINAL_NAME = "name";
    public static final String COLUMN_NAME_CRIMINAL_AGE = "age";
    public static final String COLUMN_NAME_GENDER = "gender";
    public static final String COLUMN_NAME_CRIMES = "crimes";
    public static final String COLUMN_NAME_LAST_SEEN_LOCATION = "last_seen_location";
    public static final String COLUMN_NAME_NULLABLE = "NULL";
    public static final String COLUMN_NAME_IMAGE_URI="image_uri";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";
    private static final String COMMA_SEP = ",";
    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "records.db";
    private SQLiteDatabase db;
    private Context context;

    public CriminalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_CRIMINAL_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_CRIMINAL_AGE + INT_TYPE + COMMA_SEP +
                        COLUMN_NAME_GENDER + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_CRIMES + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_IMAGE_URI + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_LAST_SEEN_LOCATION + TEXT_TYPE + " )";
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public long updateData(Criminal cr){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_LAST_SEEN_LOCATION, cr.getLastSeenLocation());
        values.put(COLUMN_NAME_CRIMES, cr.getCrime());
        values.put(COLUMN_NAME_GENDER, cr.isMale()?"Male":"Female");
        values.put(COLUMN_NAME_CRIMINAL_AGE, cr.getAge());
        values.put(COLUMN_NAME_IMAGE_URI, cr.getImageURI());
        values.put(COLUMN_NAME_CRIMINAL_NAME, cr.getName());

        long newRowId=0;
        newRowId = db.insert(
                TABLE_NAME,
                COLUMN_NAME_NULLABLE,
                values);

       // dataLogPrint();

         db.close();

        return newRowId;
    }

    public void dataLogPrint(){
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE 1=1", null);
        c.moveToFirst();
        while(c!=null&&!c.isAfterLast()){
            Log.e("TABLE", c.getString(0) + " : " + c.getString(c.getColumnIndex(COLUMN_NAME_CRIMINAL_NAME)) + " : " +
                    c.getString(c.getColumnIndex(COLUMN_NAME_CRIMINAL_AGE)) + " : " + c.getString(c.getColumnIndex(COLUMN_NAME_GENDER))
                    + " : " + c.getString(c.getColumnIndex(COLUMN_NAME_CRIMES)) + " : " + c.getString(c.getColumnIndex(COLUMN_NAME_LAST_SEEN_LOCATION)));

            c.moveToNext();
        }
        //db.close();
    }
    public ArrayList<String> getNames(){
        ArrayList<String> arr = new ArrayList<>();
        db=this.getWritableDatabase();
        dataLogPrint();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+"  WHERE 1=1", null);
        if(c!=null) {
            c.moveToFirst();
            while (c != null && !c.isAfterLast()) {

                arr.add(c.getString(c.getColumnIndex(COLUMN_NAME_CRIMINAL_NAME)));
                Log.i("Data Get",c.getString(c.getColumnIndex(COLUMN_NAME_CRIMINAL_NAME)));
                c.moveToNext();
            }
            db.close();
            return arr;
        }
        else
            return null;
    }
    public Criminal getCriminalData(int index){
        db=this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_ID+" = "+(index+1),null);
        c.moveToFirst();
        Criminal cr = new Criminal(c.getString(c.getColumnIndex(COLUMN_NAME_CRIMINAL_NAME)),
                Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_NAME_CRIMINAL_AGE))),
                c.getString(c.getColumnIndex(COLUMN_NAME_GENDER)).equalsIgnoreCase("Male"),
                c.getString(c.getColumnIndex(COLUMN_NAME_CRIMES)),
                c.getString(c.getColumnIndex(COLUMN_NAME_LAST_SEEN_LOCATION)),
                c.getString(c.getColumnIndex(COLUMN_NAME_IMAGE_URI)));

        Log.e("uri: ","");
        db.close();

        return cr;
    }

}
