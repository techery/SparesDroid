package com.zen.droidparts.db;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 14.06.13
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public enum FieldType {
    STRING, INTEGER, LONG, TEXT, BLOB, BOOL;
    /*private static HashMap<FieldType,String> dbNamesMap = new HashMap<FieldType,String>(){{
        put(STRING, "STRING");
        put(INTEGER, "INTEGER");
        put(LONG, "INTEGER");
        put(TEXT, "TEXT");
        put(BLOB, "BLOB");
        put(BOOL, "INTEGER");
    }};

    public static  String getDbName(FieldType type){
        String dbName = dbNamesMap.get(type);
        if(dbName == null)
            throw new IllegalArgumentException("can not find db type name for type " + type.toString());
        return dbName;
    } */

}
