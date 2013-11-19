package com.techery.spares.db.fields;

import com.techery.spares.db.FieldType;
import com.techery.spares.db.accessors.FieldAccessor;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 14.06.13
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */


public class Field {

    private String name;
    //private String dbTypeName;
    protected boolean isPrimaryKey = false;
    private FieldAccessor accessor;
    private int index;
    private boolean isAutoincrement = false;

    public Field(String name, FieldAccessor accessor){
        this.name = name;
        this.accessor = accessor;
        //this.dbTypeName = FieldType.getDbName(accessor.getType());
    }


    public String getName() {
        return name;
    }

    public FieldType getType() {
        return accessor.getType();
    }

    /*public String getDbTypeName() {
        return dbTypeName;
    }*/

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public FieldAccessor getAccessor() {
        return accessor;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isAutoincrement() {
        return isAutoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        isAutoincrement = autoincrement;
    }
}
