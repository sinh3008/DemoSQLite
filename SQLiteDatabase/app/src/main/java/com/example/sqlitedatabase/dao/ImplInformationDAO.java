package com.example.sqlitedatabase.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlitedatabase.db.DatabaseHelper;
import com.example.sqlitedatabase.entity.Information;

import java.util.ArrayList;
import java.util.List;

public class ImplInformationDAO implements IInformation {

    private Context mCtx;
    private SQLiteDatabase mDB;

    public ImplInformationDAO(Context mCtx) {
        this.mCtx = mCtx;
        DatabaseHelper helper = new DatabaseHelper(this.mCtx);
        mDB = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    @Override
    public List<Information> selectAll() {
        String sql = "SELECT * FROM tblInformation;";
        Cursor c = mDB.rawQuery(sql, null);
        List<Information> list = new ArrayList<>();
        while (c.moveToNext()) {
            int ID = c.getInt(c.getColumnIndex("ID"));
            String Name = c.getString(c.getColumnIndex("Name"));
            String Number = c.getString(c.getColumnIndex("Number"));
            String Email = c.getString(c.getColumnIndex("Email"));
            Information i = new Information(ID, Name, Number, Email);
            list.add(i);
        }
        return list;
    }

    @Override
    public boolean insert(Information i) {
        ContentValues cv = new ContentValues();
        cv.put("Name", i.getName());
        cv.put("Number", i.getNumber());
        cv.put("Email", i.getEmail());

        long checked = mDB.insert("tblInformation", null, cv);
        if (checked > 0) {
            return true;
        } else {
            return false;
        }

    }

    @SuppressLint("Range")
    @Override
    public Information detail(int ID) {
        String sql = "SELECT * FROM tblInformation WHERE ID = ?";

        String strArgs[] = {String.valueOf(ID)};
        Cursor c = mDB.rawQuery(sql, strArgs);

        while (c.moveToNext()) {
            //int IDi = c.getInt(c.getColumnIndex("ID"));
            String Name = c.getString(c.getColumnIndex("Name"));
            String Number = c.getString(c.getColumnIndex("Number"));
            String Email = c.getString(c.getColumnIndex("Email"));
            Information i = new Information(ID, Name, Number, Email);
            return i;
        }
        return null;
    }

    @Override
    public boolean update(Information i) {
        ContentValues cv = new ContentValues();
        cv.put("Name", i.getName());
        cv.put("Number", i.getNumber());
        cv.put("Email", i.getEmail());
        int slBanGhi = mDB.update("tblInformation", cv, "ID = ?", new String[]{String.valueOf(i.getID())});
        if (slBanGhi > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(int ID) {
        String sql = "DELETE FROM tblInformation WHERE ID = ?";
        String strArgs[] = {String.valueOf(ID)};
        int slbanxoa = mDB.delete("tblInformation", "ID = ?", strArgs);
        if (slbanxoa > 0) {
            return true;
        } else {
            return false;
        }
    }


}
