package com.zen.droidparts.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.zen.droidparts.db.db_adapters.SQLiteAdapter;

public abstract class DatabaseManager<DATABASE_CLASS extends AbstractDatabase> {
    private SQLiteOpenHelper dbHelper;
    private DATABASE_CLASS dataBase;

    public DatabaseManager(Context context) {
        this.dbHelper = new DBHelper(context, getDataBaseName(), getDataBaseVersion());
    }

    protected abstract int getDataBaseVersion();

    protected abstract String getDataBaseName();

    public DATABASE_CLASS createDatabase() {
        DATABASE_CLASS db = createDataBase(new SQLiteAdapter(dbHelper.getWritableDatabase()));
        db.createTables();
        return db;
    }

    protected abstract DATABASE_CLASS createDataBase(SQLDatabase db);

    public DATABASE_CLASS getDataBase(){
        if (dataBase == null) {
            dataBase = createDatabase();
        }
        return dataBase;
    }
}
