package com.apreciasoft.mobile.remisclasea.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.apreciasoft.mobile.remisclasea.Entity.TravelSqliteEntity;

public class SqliteUtil extends SQLiteOpenHelper {
    //Se declaran e inicializan las variables encargadas de almacenar las consultas para crear la tabla 'Ventas',
//y las consultas de eliminar/crear la Base de Datos 'Ferreteria.sqlite'.
    private static final String SQL_CREATE_ENTRIES =

            "CREATE TABLE "
                    + TravelSqliteEntity.TABLE_NAME+  "(" +
                    TravelSqliteEntity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TravelSqliteEntity.COLUMN_ID + " INTEGER,"
                    + TravelSqliteEntity.COLUMN_DISTANCE + " DOUBLE,"
                    + TravelSqliteEntity.IS_DRETURN + " INTEGER,"
                    + TravelSqliteEntity.KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TravelSqliteEntity.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "asremis.sqlite";


    public SqliteUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Método para crear la Tabla que recibe la consulta Transact-SQL
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    //Método que elimina la tabla y vuelve a llamar al método que 	la crea
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}