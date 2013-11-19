package com.techery.spares.db.accessors;

import com.techery.spares.db.DbFeildType;
import com.techery.spares.db.FieldType;
import com.techery.spares.db.manipulators.TextFieldManipulator;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 14.06.13
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */
public abstract class TextAccessor<OBJ_TYPE> extends FieldAccessor<OBJ_TYPE, String> {

    private static TextFieldManipulator manipulator = new TextFieldManipulator();

    @Override
    public TextFieldManipulator getFieldManipulator(){
        return manipulator;
    }

    @Override
    public FieldType getType() {
        return FieldType.TEXT;
    }

    @Override
    public DbFeildType getDbType(){
        return DbFeildType.TEXT;
    }
}