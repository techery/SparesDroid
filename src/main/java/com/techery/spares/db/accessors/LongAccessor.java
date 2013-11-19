package com.techery.spares.db.accessors;

import com.techery.spares.db.DbFeildType;
import com.techery.spares.db.FieldType;
import com.techery.spares.db.manipulators.LongFieldManipulator;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 14.06.13
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class LongAccessor<OBJ_TYPE> extends FieldAccessor<OBJ_TYPE, Long> {
    private static LongFieldManipulator manipulator = new LongFieldManipulator();

    @Override
    public LongFieldManipulator getFieldManipulator(){
        return manipulator;
    }

    @Override
    public FieldType getType() {
        return FieldType.LONG;
    }
    @Override
    public DbFeildType getDbType(){
        return DbFeildType.INTEGER;
    }

}
