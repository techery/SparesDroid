package com.zen.droidparts.db.manipulators;

import android.content.ContentValues;
import android.database.Cursor;

import com.zen.droidparts.db.accessors.TextAccessor;
import com.zen.droidparts.db.fields.Field;


/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 23.06.13
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public class TextFieldManipulator<OBJ_TYPE> extends FieldManipulator<OBJ_TYPE>{
    @Override
    public void setFromCursor(Cursor cursor, Field f, OBJ_TYPE obj){
        ((TextAccessor<OBJ_TYPE>)f.getAccessor()).set(obj,
                DigitalStringFix.decode(cursor.getString(f.getIndex())));
    }

    @Override
    public void putToCv(OBJ_TYPE obj, Field f, ContentValues cv){
        cv.put(f.getName(), DigitalStringFix.encode(((TextAccessor<OBJ_TYPE>)f.getAccessor()).get(obj)));
    }

}
