package project.apartmentmanage.yasaman.apartmentmanage.FeedReaders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContract.FeedEntry;

public class FeedReaderDbHelper extends SQLiteOpenHelper {


    private static final String TEXT_TYPE =" TEXT";


    /*SQL_CREATE_ENTRIES*/

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF  NOT EXISTS "+FeedEntry.Table_Name +
                    "(" + FeedEntry._ID + "INTEGER PRIMARY KEY ," +
                    FeedEntry.Column_Name_UNITNUMBER + TEXT_TYPE +
                    "," + FeedEntry.Column_Name_OWNER + TEXT_TYPE +
                    "," + FeedEntry.Column_Name_PHONE + TEXT_TYPE +
                    "," + FeedEntry.Column_Name_MOBILE + TEXT_TYPE +
                    "," + FeedEntry.Column_Name_FAMILY_MEMBER + TEXT_TYPE +");";



    /*SQL_DELETE_ENTRIES*/

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+FeedEntry.Table_Name;


    public static final int Database_Version = 1 ;
    public static final String Database_Name = "Units_Database.db";

    public FeedReaderDbHelper (Context context) {
        super(context , Database_Name , null , Database_Version);

    }


    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade (SQLiteDatabase db , int oldVersion , int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
