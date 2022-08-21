package com.example.sqlitedatabase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Information.sqlite";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE tblInformation (\n" +
                "    ID     INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                   NOT NULL,\n" +
                "    Name   TEXT    NOT NULL,\n" +
                "    Number TEXT    UNIQUE\n" +
                "                   NOT NULL,\n" +
                "    Email  TEXT    \n" +
                "                   NOT NULL\n" +
                ");\n";
        db.execSQL(sql);


        sql = "insert into tblInformation(ID, Name, Number, Email) VALUES (1,'Nguyễn Văn A','123-456','Anguyenvan@gmail.com');";
        db.execSQL(sql);

        sql = "insert into tblInformation(ID, Name, Number, Email) VALUES (2,'Nguyễn Văn B','123-4567','Bnguyenvan@gmail.com');";
        db.execSQL(sql);

        sql = "insert into tblInformation(ID, Name, Number, Email) VALUES (3,'Nguyễn Văn C','123-4568','Cnguyenvan@gmail.com');";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
