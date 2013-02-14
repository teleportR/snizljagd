package org.teleportr.snizljagd;

import java.io.IOException;
import java.util.List;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class PlaceProvider extends ContentProvider {
    
    private class DatabaseHelper extends SQLiteOpenHelper {

        static final String TAG = "PlaceProvider";

        public DatabaseHelper(Context context) {
            super(context, "places.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE places (" +
                            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "name TEXT, " +
                            "address TEXT, " +
                            "lat INTEGER, " +
                            "lng INTEGER" +
                        ");");
            
//            store("Foo", "Warschauerstraße 47, Berlin");
//            store("Bar", "Friedrichstraße 3, Berlin");
            
            Log.d(TAG, "created DB");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }
    


    private DatabaseHelper db;
    
    @Override
    public boolean onCreate() {
        db = new DatabaseHelper(getContext());
        return false;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db.getWritableDatabase().insert("places", null, values);
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        return db.getReadableDatabase().query("places", null, null, null, null, null, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

}
