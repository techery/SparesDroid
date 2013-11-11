package com.zen.droidparts.db.accessors;

import com.zen.droidparts.db.DbFeildType;
import com.zen.droidparts.db.FieldType;
import com.zen.droidparts.db.manipulators.FieldManipulator;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 14.06.13
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class FieldAccessor<OBJ_T, FIELD_T>{
    public abstract FIELD_T get(OBJ_T obj);
    public abstract void set(OBJ_T obj, FIELD_T fld);
    public abstract FieldType getType();
    public abstract FieldManipulator<OBJ_T> getFieldManipulator();
    public abstract DbFeildType getDbType();

    public String getDbTypeName(){
        return getDbType().name();
    }
}
