package com.example.android.pets.data;

import android.provider.BaseColumns;

public final class PetsContract {
    private PetsContract(){}
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
    }

}
