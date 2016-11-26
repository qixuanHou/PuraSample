package com.example.houqixuan.nov25.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by houqixuan on 11/25/16.
 */

public class GalleryDAO extends GalleryDBDAO {
    private static final String WHERE_ID_EQUALS = DBHelper.PATH
            + " =?";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    public GalleryDAO(Context context) {
        super(context);
    }

    public long save(Gallery gallery) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, gallery.getName());
        values.put(DBHelper.DATE, formatter.format(gallery.getDate()));
        return database.insert(DBHelper.GALLERY_TABLE, null, values);
    }

    public long update(Gallery gallery) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, gallery.getName());
        values.put(DBHelper.DATE, formatter.format(gallery.getDate()));

        long result = database.update(DBHelper.GALLERY_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(gallery.getPath()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Gallery gallery) {
        return database.delete(DBHelper.GALLERY_TABLE, WHERE_ID_EQUALS,
                new String[] { gallery.getPath() + "" });
    }

    public Gallery getGallery(String path) {
        Gallery gallery = null;

        String sql = "SELECT * FROM " + DBHelper.GALLERY_TABLE
                + " WHERE " + DBHelper.PATH + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { path + "" });

        if (cursor.moveToNext()) {
            gallery = new Gallery();
            gallery.setPath(cursor.getString(0));
            gallery.setName(cursor.getString(1));
            try {
                gallery.setDate(formatter.parse(cursor.getString(2)));
            } catch (ParseException e) {
                gallery.setDate(null);
            }

        }
        return gallery;
    }
}
