package com.zen.droidparts.db.db_adapters;

import android.content.ContentValues;
import android.database.Cursor;

import com.zen.droidparts.db.SQLDatabase;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 16.06.13
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public class SqlLoggingWrapper implements SQLDatabase {
    private SQLDatabase db;
    private Logger logger;

    public SqlLoggingWrapper(SQLDatabase db, Logger logger){
        if(db == null)
            throw new IllegalArgumentException("SQLDatabase can not be null");
        this.db = db;

        if(logger == null)
            throw new IllegalArgumentException("Logger can not be null");

        this.logger = logger;
    }

    public int insert (String table,  ContentValues values){
        log(" INSERT INTO " + table);
        logPairs(values);
        int r = db.insert(table, values);
        log(" -- returned : " + r);
        return r;
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs){
        log(" UPDATE " + table);
        logPairs(values);
        log(" WHERE " + whereClause + concatParams(whereArgs));
        int r = db.update(table, values,  whereClause, whereArgs);
        log(" -- returned : " + r);
        return r;
    }

    public Cursor rawQuery(String sql, String[] selectionArgs){
        log( sql  + concatParams(selectionArgs));
        Cursor c = db.rawQuery(sql, selectionArgs);
        log(" --- ");
        while (c.moveToNext()){
            StringBuffer buf = new StringBuffer();
            buf.append(c.getPosition());
            buf.append(": ");
            for(int i = 0; i < c.getColumnCount(); i++){
                switch (c.getType(i)){
                    case Cursor.FIELD_TYPE_NULL:
                        buf.append("NULL");
                        break;

                    case Cursor.FIELD_TYPE_STRING:
                        buf.append(c.getString(i));
                        break;

                    case Cursor.FIELD_TYPE_INTEGER:
                        buf.append(c.getInt(i));
                        break;

                    case Cursor.FIELD_TYPE_FLOAT:
                        buf.append(c.getFloat(i));
                        break;


                    case Cursor.FIELD_TYPE_BLOB:
                        buf.append(c.getBlob(i));
                        break;

                    default:
                        buf.append("UNKNOWN TYPE!!");
                        break;

                }

                buf.append("\t ");
            }
            log(buf.toString());
        }
        log(" results: " + c.getCount());
        log(" --- ");
        c.moveToPosition(-1);
        return c;
    }



    public int delete(String table, String whereClause, String[] whereArgs){
        log(" DELTE FROM  " + table +  whereClause + concatParams(whereArgs));
        int r = db.delete(table, whereClause, whereArgs);
        log(" -- returned : " + r);
        return r;
    }
    public void execSQL (String sql){
        log(sql);
        db.execSQL(sql);
        log(" -- ok");
    }

    public String getErrorMessage(){
        return db.getErrorMessage();
    }

    public boolean needUpgrade(int version){
        log("NEED UPGRADE REQUEST v." + version);
        boolean r = db.needUpgrade(version);
        log(" -- returned : " + (r ? "yes" : "no"));
        return r;
    }

    public void close(){
        log("CLOSE DATABASE");
    }

    private void logPairs(ContentValues values){
        for(String k:values.keySet()){
            log("\t" + k + "\t : " + values.get(k));
        }
    }

    private String concatParams(String[] params){
        StringBuffer buf = new StringBuffer();
        if(params != null){
            buf.append(" | ");
            for(String sp:params){
                buf.append(sp);
                buf.append(" | ");
            }
        }  else {
            buf.append("[no params]");
        }

        return buf.toString();
    }

    private void log(String str){
        this.logger.log(Level.INFO, str);
    }
}
