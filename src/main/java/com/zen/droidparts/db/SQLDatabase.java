package com.zen.droidparts.db;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 16.06.13
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public interface SQLDatabase {
    public int insert (String table,  ContentValues values);
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs);
    public Cursor rawQuery(String sql, String[] selectionArgs);
    public int delete(String table, String whereClause, String[] whereArgs);
    public void execSQL (String sql);
    public String getErrorMessage();
    public boolean needUpgrade(int version);
    public void close();
}
