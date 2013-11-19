package com.techery.spares.db.manipulators;

import android.content.ContentValues;
import android.database.Cursor;

import com.techery.spares.db.accessors.LongAccessor;
import com.techery.spares.db.fields.Field;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 23.06.13
 * Time: 11:25
 * To change this template use File | Settings | File Templates.
 */
public class LongFieldManipulator<OBJ_TYPE> extends FieldManipulator<OBJ_TYPE> {
    @Override
    public void setFromCursor(Cursor cursor, Field f, OBJ_TYPE obj){
        ((LongAccessor<OBJ_TYPE>)f.getAccessor()).set(obj, cursor.getLong(f.getIndex()));
    }

    @Override
    public void putToCv(OBJ_TYPE obj, Field f, ContentValues cv){
        cv.put(f.getName(),((LongAccessor<OBJ_TYPE>)f.getAccessor()).get(obj));
    }

}
