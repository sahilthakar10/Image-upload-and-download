package com.example.abc.image;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC on 20-08-2017.
 */

public class DABASEHANDLER extends SQLiteOpenHelper {

    private static final String TABLE_REGISTRATION = "aq";
    private static final String KEY_ID = "id";
    private static final String KEY_phone = "phone";
    private static final String KEY_photo = "photo";

    public DABASEHANDLER(Context context) {
        super(context, Dbversion.DATABASE_NAME, null, Dbversion.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REGISTRATION = "CREATE TABLE IF NOT EXISTS " + TABLE_REGISTRATION + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_phone + " TEXT not null unique," + KEY_photo + " TEXT" + ")";
        db.execSQL(CREATE_REGISTRATION);
        Log.e("sqffl", CREATE_REGISTRATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        onCreate(db);
    }

    public void addDATA(Registration reeg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_phone, reeg.getphone());
        db.insert(TABLE_REGISTRATION, null, value);
        db.close();

    }

    public void update(String photo) {
        Log.e("urll", photo);
        String photos = photo;
        SQLiteDatabase DB = this.getWritableDatabase();
        String url = "UPDATE " + TABLE_REGISTRATION + " SET photo='" + photos + "'   WHERE phone=9909856507";
        DB.execSQL(url);
        Log.e("updatequery", url);
    }

    public List<Registration> getAllData() {

        List<Registration> list = new ArrayList<Registration>();
        String select = "SELECT * FROM " + TABLE_REGISTRATION + " WHERE phone=9909856507";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                Registration obj = new Registration();
                obj.setId(Integer.parseInt(cursor.getString(0)));
                obj.setphone(cursor.getString(1));
                obj.setphoto(cursor.getString(2));
                list.add(obj);
            } while (cursor.moveToNext());
        }
        return list;

    }
}