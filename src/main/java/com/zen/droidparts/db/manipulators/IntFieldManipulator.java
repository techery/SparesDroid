package com.zen.droidparts.db.manipulators;

import android.content.ContentValues;
import android.database.Cursor;

import com.zen.droidparts.db.accessors.IntAccessor;
import com.zen.droidparts.db.fields.Field;


/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 23.06.13
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
public class IntFieldManipulator<OBJ_TYPE> extends FieldManipulator<OBJ_TYPE> {
    @Override
    public void setFromCursor(Cursor cursor, Field f, OBJ_TYPE obj){
        ((IntAccessor<OBJ_TYPE>)f.getAccessor()).set(obj, cursor.getInt(f.getIndex()));
    }

    @Override
    public void putToCv(OBJ_TYPE obj, Field f, ContentValues cv){
        cv.put(f.getName(), ((IntAccessor<OBJ_TYPE>) f.getAccessor()).get(obj));
    }
}
