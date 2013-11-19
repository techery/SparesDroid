package com.techery.spares.db.manipulators;

import android.content.ContentValues;
import android.database.Cursor;

import com.techery.spares.db.accessors.BoolAccessor;
import com.techery.spares.db.fields.Field;


/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 23.06.13
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class BoolFieldManipulator<OBJ_TYPE> extends FieldManipulator<OBJ_TYPE> {
    @Override
    public void setFromCursor(Cursor cursor, Field f, OBJ_TYPE obj){
        ((BoolAccessor<OBJ_TYPE>)f.getAccessor()).set(obj, cursor.getInt(f.getIndex()) > 0);
    }

    @Override
    public void putToCv(OBJ_TYPE obj, Field f, ContentValues cv){
        cv.put(f.getName(),((BoolAccessor<OBJ_TYPE>)f.getAccessor()).get(obj) ? 1 : 0);
    }
}
