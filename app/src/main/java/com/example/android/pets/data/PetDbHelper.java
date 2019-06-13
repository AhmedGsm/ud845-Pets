package com.example.android.pets.data;

import com.example.android.pets.data.PetsContract.PetsEntry;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class PetDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pets.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_SCHEMA = "CREATE TABLE " + PetsEntry.TABLE_NAME +
            " (" +
            PetsEntry.COLUMN_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PetsEntry.COLUMN_PET_NAME + " TEXT," +
            PetsEntry.COLUMN_PET_BREED + " TEXT NOT NULL," +
            PetsEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL," +
            PetsEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0" +
            ");";
    private static final String SQL_DROP_TABLE_SCHEMA = "DROP TABLE IF EXISTS  " + PetsEntry.TABLE_NAME ;

    public PetDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table query
        db.execSQL(SQL_CREATE_TABLE_SCHEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table query
        db.execSQL(SQL_DROP_TABLE_SCHEMA);
        //Then Create table query with updated columns
        db.execSQL(SQL_CREATE_TABLE_SCHEMA);

    }
}
