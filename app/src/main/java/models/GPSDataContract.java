package models;

import android.provider.BaseColumns;

public final class GPSDataContract {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + GPSDataEntry.TABLE_NAME + " (" +
                    GPSDataEntry._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT, " +
                    GPSDataEntry.LATITUDE + REAL_TYPE + " NOT NULL, " +
                    GPSDataEntry.LONGITUDE + REAL_TYPE + " NOT NULL, " +
                    GPSDataEntry.ALTITUDE + REAL_TYPE + " NOT NULL, " +
                    GPSDataEntry.TIME + " TIME DEFAULT CURRENT_TIMESTAMP"+
                    ");";

    public static abstract class GPSDataEntry implements BaseColumns {
        public static final String TABLE_NAME = "GPSDATA";
        public static final String LATITUDE = "Latitude";
        public static final String LONGITUDE = "Longitude";
        public static final String ALTITUDE = "Altitude";
        public static final String TIME = "TimeUpdated";
    }
}

