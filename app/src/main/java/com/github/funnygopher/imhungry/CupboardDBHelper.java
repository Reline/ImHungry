package com.github.funnygopher.imhungry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class CupboardDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "imhungry.db";
    public static final int DATBASE_VERSION = 1;

    static {
        // The classes that are registered to cupboard
        cupboard().register(Place.class);
    }

    public CupboardDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Makes sure the tables are created
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
