/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pets.data.PetDbHelper;
import com.example.android.pets.data.PetsContract;
import com.example.android.pets.data.PetsContract.PetsEntry;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertPet() {

        //Insert values to ContentValues object
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetsEntry.COLUMN_PET_NAME,"Toto");
        contentValues.put(PetsEntry.COLUMN_PET_BREED,"Terrier");
        contentValues.put(PetsEntry.COLUMN_PET_GENDER,PetsEntry.PETS_GENDER_MALE);
        contentValues.put(PetsEntry.COLUMN_PET_WEIGHT,17);
        Uri uri = getContentResolver().insert(PetsEntry.CONTENT_URI,  contentValues) ;
        Log.w("uri-->","uri" + uri);
        //Display Database row count
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {

        //projection array that define column to acces during querying database
        String[] projection = {PetsEntry.COLUMN_PET_ID,
                PetsEntry.COLUMN_PET_NAME,
                PetsEntry.COLUMN_PET_BREED,
                PetsEntry.COLUMN_PET_GENDER,
                PetsEntry.COLUMN_PET_WEIGHT};
        // Query the content provider using content resolver and return cursor object
        Cursor cursor = getContentResolver().query(PetsEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
        try {

            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
            int rowsCount = cursor.getCount();
            displayView.setText("Number of rows in pets database table: " + rowsCount);
            displayView.append(" \n " +PetsEntry.COLUMN_PET_ID + " - " +
                    PetsEntry.COLUMN_PET_NAME + " - " +
                    PetsEntry.COLUMN_PET_BREED + " - " +
                    PetsEntry.COLUMN_PET_GENDER + " - " +
                    PetsEntry.COLUMN_PET_WEIGHT + " \n "  );
            while(cursor.moveToNext()) {
                // Get columns indices passing the columns names
                int columnIndexID = cursor.getColumnIndex(PetsEntry.COLUMN_PET_ID);
                int columnIndexName = cursor.getColumnIndex(PetsEntry.COLUMN_PET_NAME);
                int columnIndexBreed = cursor.getColumnIndex(PetsEntry.COLUMN_PET_BREED);
                int columnIndexGender = cursor.getColumnIndex(PetsEntry.COLUMN_PET_GENDER);
                int columnIndexWeight = cursor.getColumnIndex(PetsEntry.COLUMN_PET_WEIGHT);
                // Display query results in textView
                displayView.append(String.valueOf(cursor.getInt(columnIndexID)));
                displayView.append(" - " + cursor.getString(columnIndexName));
                displayView.append(" - " + cursor.getString(columnIndexBreed));
                displayView.append(" - " + String.valueOf(cursor.getInt(columnIndexGender)));
                displayView.append(" - " + String.valueOf(cursor.getInt(columnIndexWeight)));
                displayView.append("\n");
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
