package com.example.sqlitetestrcc61;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteDataBase extends SQLiteOpenHelper {


    // remove all parameter from constructor except Context
    public SqliteDataBase(@Nullable Context context) {
        // name means name of database
        //factory make it null
        // version make it 1
        super(context, "Rcc61", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // use it to create new table in database
        //db -> any name
        //execSQL() -> method do orders on my data base by object SQLiteDatabase
        db.execSQL("create table TodoList (event Text , id Integer PRIMARY KEY AUTOINCREMENT , do Integer );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // db : any name
        // if version number is changed , compiler enter in onUpgrade method to delete old table then create new table again
        db.execSQL("Drop table If Exists TodoList ");
    }

    public long InsertData (String eventdata ){
        //getWritableDatabase() to write on database
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues enter spacial data in specific column
        ContentValues data = new ContentValues();
        data.put("event" , eventdata);

        long result = db.insert("TodoList" , null ,data);
        //insert() -> method to insert data into table
        return result;
    }

    public Cursor GetData(){
        SQLiteDatabase db = this.getReadableDatabase();
        //rawQuery method to get data from sqlite
        // * : all
        //Cursor : read data by it from sqlite
        Cursor cursor = db.rawQuery("select * from TodoList " , null );
        return cursor;

    }

    public int  UpdateData (int done , int id ){
        // for update in checkbox in row design
        SQLiteDatabase db = this.getWritableDatabase();// build in class
        ContentValues data = new ContentValues();
        // do : means column in data base
        data.put("do" , done);

        // update method to update in sqlite databse
        int result = db.update("TodoList" , data , "id = ?" , new String[]{String.valueOf(id)} );

        return result ;

    }


    public int Remove (int id ){
        SQLiteDatabase db = this.getWritableDatabase();
        int r =db.delete("TodoList" , "id = ? " , new String[]{String.valueOf(id)});
        return r;

    }



}
