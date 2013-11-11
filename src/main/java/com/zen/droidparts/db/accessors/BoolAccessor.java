package com.zen.droidparts.db.accessors;

import com.zen.droidparts.db.DbFeildType;
import com.zen.droidparts.db.FieldType;
import com.zen.droidparts.db.manipulators.BoolFieldManipulator;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 15.06.13
 * Time: 01:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class BoolAccessor<OBJ_TYPE> extends FieldAccessor<OBJ_TYPE, Boolean> {
    private static BoolFieldManipulator manipulator = new BoolFieldManipulator();

    @Override
    public BoolFieldManipulator getFieldManipulator(){
        return manipulator;
    }

    @Override
    public FieldType getType() {
        return FieldType.BOOL;
    }

    @Override
    public DbFeildType getDbType(){
        return DbFeildType.INTEGER;
    }

}
