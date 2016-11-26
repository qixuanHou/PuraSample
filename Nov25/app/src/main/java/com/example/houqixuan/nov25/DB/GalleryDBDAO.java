package com.example.houqixuan.nov25.DB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by houqixuan on 11/25/16.
 */

public class GalleryDBDAO {
    protected SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context mContext;

    public GalleryDBDAO(Context context) {
        this.mContext = context;
        dbHelper = DBHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DBHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

	/*public void close() {
		dbHelper.close();
		database = null;
	}*/
}
