package com.techery.spares.db.accessors;

import com.techery.spares.db.DbFeildType;
import com.techery.spares.db.FieldType;
import com.techery.spares.db.manipulators.BlobFieldManipulator;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 14.06.13
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class BlobAccessor<OBJ_TYPE> extends FieldAccessor<OBJ_TYPE, byte[]> {

    private static BlobFieldManipulator manipulator = new BlobFieldManipulator();

    @Override
    public BlobFieldManipulator getFieldManipulator(){
        return manipulator;
    }

    @Override
    public FieldType getType() {
        return FieldType.BLOB;
    }

    @Override
    public DbFeildType getDbType(){
        return DbFeildType.BLOB;
    }

}
