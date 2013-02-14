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

import java.io.IOException;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Place {
    
    protected static final String TAG = "Place";

    static void store(final Context ctx, final String[] tour) {
        new AsyncTask<String, Integer, String>() {

            @Override
            protected String doInBackground(String... params) {
                for (String place : tour) {
                    String [] p = place.split(":");
                    String address = p[1];
                    String name = p[0];
                    try {
                        List<Address> r = new Geocoder(ctx).getFromLocationName(address, 5);
                        if (r.size() == 0) {
                            Log.d(TAG, "Not found: "+name);
                        } else {
                            if (r.size() > 1) {
                                Log.d(TAG, "MORE found: "+name);
                            }
                            ContentValues cv = new ContentValues();
                            cv.put("name", name);
                            cv.put("address", address);
                            cv.put("lat", (long) r.get(0).getLatitude()*10E6);
                            cv.put("lng", (long) r.get(0).getLongitude()*10E6);
                            ctx.getContentResolver().insert(
                                    Uri.parse("content://org.teleportr.snizljagd/places"), cv);
                            Log.d(TAG, "stored "+name+" lng="+r.get(0).getLongitude());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "places stored.");
                return null;
            }
        }.execute("");
    }
}
