package com.techery.spares.db;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 18.06.13
 * Time: 00:15
 * To change this template use File | Settings | File Templates.
 */
public class AbstractDatabase {
    private ArrayList<TableGateway> tables = new ArrayList<TableGateway>();
    private SQLDatabase db;

    public AbstractDatabase(SQLDatabase db){
        this.db = db;
    }

    public void addTable(TableGateway table){
        table.setSqlDb(db);
        tables.add(table);
    }

    public void createTables(){
        for(TableGateway tm: tables){
            tm.createTable();
        }
    }

    public void dropTables(){
        for(TableGateway tm: tables){
            tm.dropTable();
        }
    }

    public void recreateTables(){
        dropTables();
        createTables();
    }
}
