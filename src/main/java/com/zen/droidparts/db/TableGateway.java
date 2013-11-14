package com.zen.droidparts.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.zen.droidparts.db.accessors.FieldAccessor;
import com.zen.droidparts.db.accessors.IntAccessor;
import com.zen.droidparts.db.fields.Field;
import com.zen.droidparts.db.fields.PrimaryKey;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 14.06.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class TableGateway<OBJ_TYPE extends DbObject> {
    private String tableName;
    private ArrayList<Field> fields = new ArrayList<Field>();
    private Field pk;
    private Class<OBJ_TYPE> objClass;
    private SQLDatabase sqlDb;
    private String[] columns;
    private String columnsStr = null ;
    private String findByIdCondition = null;






    public TableGateway(SQLDatabase sqlDb, String tableName, Class<OBJ_TYPE> objClass){
        init(sqlDb, tableName, objClass);
    }

    public TableGateway(String tableName, Class<OBJ_TYPE> objClass){
        init(null, tableName, objClass);
    }

    private void init(SQLDatabase sqlDb, String tableName, Class<OBJ_TYPE> objClass){
        this.sqlDb =  sqlDb;
        this.tableName = tableName;
        this.objClass = objClass;

        addField(new PrimaryKey(new IntAccessor<OBJ_TYPE>(){
            public Integer get(OBJ_TYPE obj) { return new Integer(obj.getId()) ;}
            public void set(OBJ_TYPE obj, Integer val) { obj.setId(val.intValue());}
        }));
    }


    public SQLDatabase getSqlDb() {
        return sqlDb;
    }

    public void setSqlDb(SQLDatabase sqlDb) {
        this.sqlDb = sqlDb;
    }


    public void createTable(){
        StringBuffer buf = new StringBuffer();
        buf.append("CREATE TABLE " );
        buf.append(tableName);
        buf.append("(");

        boolean first = true;
        for(Field f : fields){
            if(first){
                first = false;
            } else{
                buf.append(", ");
            }

            buf.append(f.getName());
            buf.append(' ');
            buf.append(f.getAccessor().getDbTypeName());

            if(f.isPrimaryKey()) {
                buf.append(" PRIMARY KEY");
                if(f.isAutoincrement()){
                    buf.append(" AUTOINCREMENT");
                }
            }

        }

        buf.append(")");
        execSQL(buf.toString());
    }

    public void addField(Field field){
        fields.add(field);
        field.setIndex(fields.size() - 1);
        if(field.isPrimaryKey())
            pk = field;
    }

    public void addField(String name, FieldAccessor accessor){
        addField(new Field(name, accessor));
    }

    public void dropTable(){
        execSQL("DROP TABLE " + tableName);
    }

    public int insert(OBJ_TYPE obj){
        int id = getDB().insert(getTableName(), obj2cv(obj));
        if(id >= 0){
            obj.setId(id);
        }
        return id;
    }


    public ContentValues obj2cv(OBJ_TYPE obj){
        ContentValues cv = new ContentValues();
        for(Field f: fields){
            if(!f.isPrimaryKey()){
                f.getAccessor().getFieldManipulator().putToCv(obj, f, cv);
            }
        }
        return  cv;
    }

    private OBJ_TYPE createObj(){
        try {
            return objClass.newInstance();
        } catch (Exception e){
            throw new DbRuntimeException("Cant instantiate object" + e.getMessage());
        }
    }

    public OBJ_TYPE cursor2obj(Cursor cursor){
        if(cursor == null)
            throw new IllegalArgumentException("cursor has to be not null");

        OBJ_TYPE obj = createObj();

        for(Field f: fields){
            f.getAccessor().getFieldManipulator().setFromCursor(cursor, f, obj);
        }

        return  obj;
    }


    public void delete(int id){
        delete(getFindByIdCondition(), id);
    }

    public void delete(String query, Object... params){
        getDB().delete(getTableName(), query, paramsToString(params));
    }

    public int count(){
        return count(null, null);
    }

    public int count(String query, Object... params){
        StringBuffer sqlBuff = new StringBuffer();
        sqlBuff.append("SELECT COUNT(*) FROM ");
       sqlBuff.append(getTableName());
        if(query != null){
            sqlBuff.append(" WHERE ");
            sqlBuff.append(query);
        }

        String[] strparams = null;
        if(params != null)
            strparams = paramsToString(params);

        Cursor cursor = getDB().rawQuery(sqlBuff.toString(), strparams);
        cursor.moveToNext();
        int count = cursor.getInt(0);
        cursor.close();

        return count;
    }

    public boolean exists(String query, Object... params){
        return (count(query,params) > 0);
    }

    public void update(int id, OBJ_TYPE obj){
        update(id, obj2cv(obj));
    }

    public void update(int id, ContentValues cv){
        update(cv, getFindByIdCondition(), id);
    }

    public void update(ContentValues cv, String query, Object... params){
        getDB().update(getTableName(), cv, query, paramsToString(params));
    }

    public void update(OBJ_TYPE obj, String query, Object... params){
        getDB().update(getTableName(), obj2cv(obj), query, paramsToString(params));
    }

    public int save(OBJ_TYPE obj){
       if(obj.getId() < 0){
           return insert(obj);
       } else {
           update(obj.getId(), obj);
           return obj.getId();
       }
    }

    public ArrayList<OBJ_TYPE> findAll(String query, Object... params){
        return  rawQuery(query,params);
    }

    public Cursor findAllCursor(String query, Object... params){
        return  rawQueryCursor(query, params);
    }

    private Cursor rawQueryCursor(String query, Object[] params) {
        StringBuffer sqlBuff = new StringBuffer();
        sqlBuff.append("SELECT ");
        sqlBuff.append(getColumnsStr());
        sqlBuff.append(" FROM ");
        sqlBuff.append(getTableName());
        if(query != null){
            sqlBuff.append(" WHERE ");
            sqlBuff.append(query);
        }

        String[] strparams = paramsToString(params);

        Cursor cursor = getDB().rawQuery(sqlBuff.toString(), strparams);

        return cursor;
    }

    public OBJ_TYPE find(int id){
        return findOne(getFindByIdCondition(), id);
    }

    public OBJ_TYPE findOne(String query, Object... params){
        ArrayList<OBJ_TYPE> results =  rawQuery(query + " LIMIT 1",params);
        if(results.size() == 0)
            return null;
        return results.get(0);
    }

    private ArrayList<OBJ_TYPE> rawQuery(String query, Object[] params){
        Cursor cursor = findAllCursor(query, params);

        ArrayList<OBJ_TYPE> results = new ArrayList<OBJ_TYPE>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()){
            results.add(cursor2obj(cursor));
        }
        cursor.close();
        return results;
    }



    public Cursor findAllCursor(){
        return rawQueryCursor(null, null);
    }

    public ArrayList<OBJ_TYPE> findAll(){
        return rawQuery(null, null);
    }


    public String getTableName() {
        return tableName;
    }

    public SQLDatabase getDB() {
        return sqlDb;
    }


    protected void execSQL(String sql){
        getDB().execSQL(sql);
    }

    public String[] getColumns() {
        if(columns == null){
            columns = new String[fields.size()];
            int i = 0;
            for(Field f:fields){
                columns[i] = f.getName();
                i++;
            }
        }
        return columns;
    }

    public String getColumnsStr() {
        if(columnsStr == null){
            String[] columns = getColumns();
            StringBuffer buff = new StringBuffer();
            for(int i = 0; i < columns.length; i++){
                if(i != 0){
                    buff.append(',');
                }
                buff.append(columns[i]);
            }
            columnsStr = buff.toString();
        }
        return columnsStr;
    }

    public String getFindByIdCondition(){
        if(findByIdCondition == null){
            if(pk == null)  {
                findByIdCondition = "id=?";
            }else{
                findByIdCondition = pk.getName() + "=?";
            }
        }
        return findByIdCondition;
    }

    private String[] paramsToString(Object[] params){
        if(params == null)
            return null;

        String[] strparams = new String[params.length];
        for(int i = 0 ; i< params.length; i++){
            if(params[i] instanceof  Boolean){
                strparams[i]  = ((Boolean)params[i]).booleanValue() ? "1" : "0";
            } else{
                strparams[i] = params[i].toString();
            }
        }
        return strparams;
    }

}
