package com.swathi.dbaplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Swathi on 2/17/2018.
 */

public class Database extends SQLiteOpenHelper {
    private String Tag="DATABASE";
    //database version
    private  static final int DATABASE_VERSION=1;
    //DATABASE NAME
    private static final String DATABASE_NAME="Infodata";
    //table name
    private static final String TABLE_NAME="publicinfo";
    //column name
    private static final String NAME="Name";
    private static final String PH_NUMBER="ph_name";
    private static final String KEY_ID="id";
    //String query
    private static final String CREATE_QUERY="CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
            +PH_NUMBER +" TEXT" +")";
    private static  final String DROP_QUERY="DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_QUERY="SELECT * FROM " + TABLE_NAME;


    Moduleclass moduleClass = new Moduleclass();
    public  Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//factory:null(cursor command)
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_QUERY);
        onCreate(db);
    }

    public void addDetails(String Name, int phnum) {
        SQLiteDatabase database=this.getWritableDatabase();
        //need content values for putting data into db
        ContentValues values=new ContentValues();
        values.put(NAME,Name);
        values.put(PH_NUMBER,phnum);
        //insert a single row
        database.insert(TABLE_NAME,null,values);
        database.close();
        Log.i(Tag,"Inserted");
    }
    public ArrayList<Moduleclass>retriveData(){
        SQLiteDatabase database=this.getWritableDatabase();
        ArrayList<Moduleclass>arrayList=new ArrayList<>();
        Cursor cursor=database.rawQuery(SELECT_QUERY,null);
        Log.i(Tag,cursor.getCount() +"");
        if(cursor.moveToFirst())
            //adding a contact to list
        {
            for (int i = 0; i < cursor.getCount(); i++) {
                Moduleclass moduleclass = new Moduleclass();//import line of code
                moduleclass.Id = Integer.parseInt(cursor.getString(0));
                moduleclass.Name = cursor.getString(1);
                moduleclass.phnum = cursor.getString(2);
                arrayList.add(moduleclass);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return arrayList;
    }

}
