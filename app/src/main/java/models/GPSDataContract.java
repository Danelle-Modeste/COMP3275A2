package models;

import android.provider.BaseColumns;

public final class GPSDataContract {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + SensorEntry.TABLE_NAME + "(" +
                    SensorEntry._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT, " +
                    SensorEntry.LATITUDE + REAL_TYPE + " NOT NULL, " +
                    SensorEntry.LONGITUDE + REAL_TYPE + " NOT NULL, " +
                    SensorEntry.ALTITUDE + REAL_TYPE + " NOT NULL, " +
                    SensorEntry.TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP"+
                    ");";

    public static abstract class SensorEntry implements BaseColumns {
        public static final String TABLE_NAME = "GPS DATA";
        public static final String LATITUDE = "Latitude";
        public static final String LONGITUDE = "Longitude";
        public static final String ALTITUDE = "Altitude";
        public static final String TIME = "Time Updated";
    }
}

