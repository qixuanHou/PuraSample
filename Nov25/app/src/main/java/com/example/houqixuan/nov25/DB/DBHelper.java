package com.example.houqixuan.nov25.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by houqixuan on 11/25/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ReVi";
    private static final int DATABASE_VERSION = 1;

    public static final String GALLERY_TABLE = "gallery";

    public static final String PATH = "path";
    public static final String NAME = "name";
    public static final String DATE = "date";

    public static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE "
            + GALLERY_TABLE + "(" + PATH + " TEXT PRIMARY KEY, "
            + NAME + " TEXT, " + DATE + " DATE" + ")";

    private static DBHelper instance;

    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
