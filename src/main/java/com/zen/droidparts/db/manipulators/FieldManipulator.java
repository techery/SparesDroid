package com.zen.droidparts.db.manipulators;

import android.content.ContentValues;
import android.database.Cursor;

import com.zen.droidparts.db.fields.Field;


/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 23.06.13
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */

public abstract class FieldManipulator<OBJ_TYPE> {
    public abstract void setFromCursor(Cursor cursor, Field f, OBJ_TYPE obj);
    public abstract void putToCv(OBJ_TYPE obj, Field f, ContentValues cv);
}
