package com.example.cliff.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FitnessAppHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "Fitness_App";
    private static final int DB_VERSION = 1;

    FitnessAppHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //If the old version is less than 1 then that means the database has not been created
        //so the tables must be made for the first time
        if (oldVersion < 1)
        {
            db.execSQL("CREATE TABLE EXERCISE (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "REPS INTEGER," +
                    "NUMBEROFREPS INTEGER," +
                    "WEIGHT INTEGER," +
                    "USESWEIGHT INTEGER);");

            db.execSQL("CREATE TABLE WORKOUT (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "EXERCISE1 INTEGER," +
                    "EXERCISE2 INTEGER," +
                    "EXERCISE3 INTEGER);");

            db.execSQL("CREATE TABLE PROFILE (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "PICTURE BLOB," +
                    "AGE INTEGER," +
                    "GENDER TEXT," +
                    "HEIGHT TEXT," +
                    "WEIGHT INTEGER);");
        }
        if (oldVersion < 2)
        {
            //For future use
        }

    }

    //helper method to add rows to the exercise table
    private static void insertExercise(SQLiteDatabase db, String name, int reps, int numberOfReps, int weight, int usesWeight)
    {
        ContentValues exerciseValues = new ContentValues();
        exerciseValues.put("NAME", name);
        exerciseValues.put("REPS", reps);
        exerciseValues.put("NUMBEROFREPS", numberOfReps);
        exerciseValues.put("WEIGHT", weight);
        exerciseValues.put("USESWEIGHT", usesWeight);
    }

    //helper method to add rows to the workout table
    private static void insertWorkout()
    {
    }
}