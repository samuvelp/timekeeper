package com.example.user.timekeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 31-07-2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION =1;
    private static String DATEBASE_NAME ="attendance.db";
    private static String TABLE_NAME ="attendanceTable";
    private static String PRIMARY_ID ="primaryId";
    private static String CHECK_IN_TIME ="checkInTime";
    private static String CHECK_OUT_TIME ="checkoutTime";
    public DBHelper(Context context) {
        super(context, DATEBASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE ="CREATE TABLE "
                + TABLE_NAME + "("
                + PRIMARY_ID + " INTEGER PRIMARY KEY, "
                + CHECK_IN_TIME + " TEXT, "
                + CHECK_OUT_TIME + " TEXT"
                + " )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void insertCheckIn(String checkIn){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHECK_IN_TIME,checkIn);
        database.insert(TABLE_NAME,null,contentValues);
        database.close();
    }
    public void insertCheckOut(String checkOut){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHECK_OUT_TIME,checkOut);
        database.insert(TABLE_NAME,null,contentValues);
        database.close();
    }
    public ArrayList<HashMap<String,String>> getAttendance(){
        String checkIn ="null";
        String checkOut ="null";
        String query = "SELECT * FROM " + TABLE_NAME ;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                checkIn = cursor.getString(cursor.getColumnIndex(CHECK_IN_TIME));
                checkOut =cursor.getString(cursor.getColumnIndex(CHECK_OUT_TIME));
                HashMap<String ,String > map = new HashMap<>();
                map.put(CHECK_IN_TIME,checkIn);
                map.put(CHECK_OUT_TIME,checkOut);
                list.add(map);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return  list;
    }

}
