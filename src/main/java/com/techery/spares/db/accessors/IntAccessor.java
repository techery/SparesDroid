package com.techery.spares.db.accessors;

import com.techery.spares.db.DbFeildType;
import com.techery.spares.db.FieldType;
import com.techery.spares.db.manipulators.IntFieldManipulator;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 14.06.13
 * Time: 23:37
 * To change this template use File | Settings | File Templates.
 */
public abstract class IntAccessor<OBJ_TYPE> extends FieldAccessor<OBJ_TYPE, Integer> {
    private static IntFieldManipulator manipulator = new IntFieldManipulator();

    @Override
    public IntFieldManipulator getFieldManipulator(){
        return manipulator;
    }

    @Override
    public FieldType getType() {
        return FieldType.INTEGER;
    }

    @Override
    public DbFeildType getDbType(){
        return DbFeildType.INTEGER;
    }




}