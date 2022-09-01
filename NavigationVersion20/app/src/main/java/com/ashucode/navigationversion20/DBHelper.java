package com.ashucode.navigationversion20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"Student.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB)
    {
        DB.execSQL("create table Contact(RollNo integer primary key AUTOINCREMENT, Name text,Age text," +
                " Sex text, Address text)");
        DB.execSQL("create table Markslist(RollNo integer primary key AUTOINCREMENT, English text , Maths text , Science text ," +
                "ComputerApplication text, SocialStudies text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1)
    {
        DB.execSQL("drop table if exists Contact");
        DB.execSQL("drop table if exists Markslist");
    }

    public void insertdata(String Name, String Age, String Sex, String Address)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", Name);
        cv.put("Age", Age);
        cv.put("Sex", Sex);
        cv.put("Address", Address);

        long result = DB.insert("Contact", null, cv);
    }
    public void insertMarksdetails(String English, String Maths, String Science, String ComputerApplication, String SocialStudies)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("English", English);
        cv.put("Maths", Maths);
        cv.put("Science", Science);
        cv.put("ComputerApplication", ComputerApplication);
        cv.put("SocialStudies", SocialStudies);

        long result = DB.insert("Markslist", null, cv);
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from Contact", null);
    }

    public Cursor getMarksData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from Markslist", null);
    }
}
