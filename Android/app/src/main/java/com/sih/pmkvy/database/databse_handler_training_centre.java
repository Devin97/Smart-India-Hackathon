package com.sih.pmkvy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 3/4/2017.
 */

public class databse_handler_training_centre extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pmkvy";
    private static final String TRAINING_CENTRES = "training_centre";

    //column names
    private static final String CENTRE_ID = "centre_id", CENTRE_NAME = "centre_name",
            CENTRE_PHONE = "centre_phone_number", CENTRE_INFO = "centre_info", CENTER_ADDRESS = "centre_address";


    public databse_handler_training_centre(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public databse_handler_training_centre(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Create","Creating Table on Create Method");
        String CREATE_TRAINING_CENTRE_TABLE = "CREATE TABLE '" + TRAINING_CENTRES + "' (" + CENTRE_ID + " INTEGER PRIMARY KEY, "
                + CENTRE_NAME + " TEXT," + CENTER_ADDRESS + " TEXT," + CENTRE_PHONE + " INTEGER," + CENTRE_INFO + " TEXT )";
        db.execSQL(CREATE_TRAINING_CENTRE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SingleDBAdapter", "Upgrading database from version " + oldVersion
                + " to "
                + newVersion + ", which will destroy all old data");
        String drop="Drop Table 'training_centre'" ;//+ TRAINING_CENTRES;
        db.execSQL(drop);

        //onCreate(db);
    }

    public void addCentre(sqlite_training_centre_data centre) {
        //Log.d("Create","Creating Table");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(CENTRE_ID, centre.getCentre_ID());
        values.put(CENTRE_NAME, centre.getCentre_name());
        values.put(CENTER_ADDRESS, centre.getCentre_address());
        values.put(CENTRE_PHONE, centre.getCentre_phone_no());
        values.put(CENTRE_INFO, centre.getCentre_info());
        db.insert(TRAINING_CENTRES, null, values);
        db.close();
    }

    public List<sqlite_training_centre_data> getAllCentreInfo() {

        List<sqlite_training_centre_data> centre_list = new ArrayList<sqlite_training_centre_data>();
        String selectQuery = "SELECT * FROM " + TRAINING_CENTRES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all centre info and adding it to list
        if (cursor.moveToFirst()) {
            do {
                sqlite_training_centre_data centre = new sqlite_training_centre_data();
                centre.setCentre_ID(Integer.parseInt(cursor.getString(0)));
                centre.setCentre_name(cursor.getString(1));
                centre.setCentre_address(cursor.getString(2));
                centre.setCentre_phone_no(Integer.parseInt(cursor.getString(3)));
                centre.setCentre_info(cursor.getString(4));
                centre_list.add(centre);

            } while (cursor.moveToNext());


        }

        return centre_list;

    }
}