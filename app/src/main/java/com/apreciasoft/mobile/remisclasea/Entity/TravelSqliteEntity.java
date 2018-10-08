package com.apreciasoft.mobile.remisclasea.Entity;


import android.provider.BaseColumns;

public  abstract class TravelSqliteEntity implements BaseColumns
{
    public static final String TABLE_NAME = "travel_history";
    public static final String COLUMN_ID = "idTravel";
    public static final String COLUMN_DISTANCE = "distance";
    public static final String KEY_CREATED_AT = "CREATEDAT";
    public static final String IS_DRETURN = "isReturn";



}
