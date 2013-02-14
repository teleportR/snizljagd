/*
 * SnizlJagd Android Game
 * 
 * Copyright (C) 2013 by it's authors. Some rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.teleportr.snizljagd;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class GameActivity extends FragmentActivity {

    private static final String TAG = "SnizlJagd";
    private GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (!getSharedPreferences("snzl", 0).contains("plan")) {
            Place.store(this, Tour.plan);
            getSharedPreferences("snzl", 0).edit().putBoolean("plan", true).commit();
        } else {
            Cursor r = getContentResolver().query(
                    Uri.parse("content://org.teleportr.snizljagd/places"), null, null, null, null);
            r.moveToFirst();
            Log.d(TAG, ":-) " + r.getString(1));
            Log.d(TAG, ":-) " + r.getString(2));
        }

        setContentView(R.layout.activity_game);
        map = ((SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map)).getMap();
    }

}
