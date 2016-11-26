package com.example.houqixuan.dbtest;

/**
 * Created by houqixuan on 11/23/16.
 */



import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class employee {

    protected SQLiteDatabase database;
    private db dbHelper;
    private Context mContext;

    public employee(Context context) {
        this.mContext = context;
        dbHelper = db.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = db.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

	/*public void close() {
		dbHelper.close();
		database = null;
	}*/
}