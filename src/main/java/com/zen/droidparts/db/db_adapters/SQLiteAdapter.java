package com.zen.droidparts.db.db_adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.zen.droidparts.db.SQLDatabase;


/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 16.06.13
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
public class SQLiteAdapter implements SQLDatabase {
    private SQLiteDatabase sqlite;
    private String lastError = null;

    public SQLiteAdapter(SQLiteDatabase sqlite){
        this.sqlite = sqlite;
    }

    public int insert (String table,  ContentValues values){
        lastError = null;
        return (int)sqlite.insert(table, null, values);
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs){
        lastError = null;
        return sqlite.update(table, values, whereClause, whereArgs);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs){
        lastError = null;
        return sqlite.rawQuery(sql, selectionArgs);
    }
    public int delete(String table, String whereClause, String[] whereArgs){
        lastError = null;
        return sqlite.delete(table, whereClause, whereArgs);
    }

    public void execSQL (String sql){
        lastError = null;
        try{
            sqlite.execSQL(sql);
        }catch (SQLException ex){
            lastError = ex.getMessage();
        }
    }
    public String getErrorMessage(){
        return lastError;
    }

    public boolean needUpgrade(int version){
        return sqlite.needUpgrade(version);
    }

    public void close(){
        sqlite.close();
    }
}
