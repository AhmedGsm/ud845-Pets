package com.example.android.pets.data;

import android.net.Uri;
import android.provider.BaseColumns;


public final class PetsContract {
    private PetsContract(){}
    //URI's Content database
    //Content Authority
    public static final String CONTENT_AUTHORITY = "com.example.android.pets";
    //Pets table data

    public static final String PATH_PETS = "pets";
    //Base content Uri
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //Class Entry
    public static  class PetsEntry implements BaseColumns {
        // TABLE NAME
        public static final String TABLE_NAME = "pets";
        public static final int PETS_GENDER_MALE = 1;
        public static final int PETS_GENDER_FEMALE= 2;
        public static final int PETS_GENDER_UNKNOWN = 0;
       // COLUMNS NAMES
        public static final String COLUMN_PET_ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_PET_GENDER = "gender";
        public static final String COLUMN_PET_WEIGHT = "weight";
        //Full content Uri
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        public static boolean isValidGender(int gender) {
            return (gender == PETS_GENDER_MALE || gender == PETS_GENDER_FEMALE || gender == PETS_GENDER_UNKNOWN );
        }
    }

}
