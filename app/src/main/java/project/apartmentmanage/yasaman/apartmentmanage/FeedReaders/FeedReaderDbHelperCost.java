package project.apartmentmanage.yasaman.apartmentmanage.FeedReaders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContractCost.FeedEntryCost;

public class FeedReaderDbHelperCost extends SQLiteOpenHelper{


    private static final String TEXT_TYPE =" TEXT";


    /*SQL_CREATE_ENTRIES_COST*/

    final String SQL_CREATE_ENTRIES_COST =
            "CREATE TABLE IF  NOT EXISTS "+FeedEntryCost.Table_Name2 +
                    "(" + FeedEntryCost._ID + "INTEGER PRIMARY KEY ," +
                    FeedEntryCost.Column_Name_UNITNUMBERC + TEXT_TYPE +
                    "," +FeedEntryCost.Column_Name_YEAR + TEXT_TYPE +
                    "," + FeedEntryCost.Column_Name_MONTH + TEXT_TYPE +
                    "," + FeedEntryCost.Column_Name_GREENSPACE +TEXT_TYPE +
                    "," + FeedEntryCost.Column_Name_PARKING +TEXT_TYPE +
                    "," + FeedEntryCost.Column_Name_CLEANING +TEXT_TYPE + ");";



    /*SQL_DELETE_ENTRIES_COAST*/

    private static final String SQL_DELETE_ENTRIES_COST =
            "DROP TABLE IF EXISTS "+FeedEntryCost.Table_Name2;


    public static final int Database_Version = 1 ;
    public static final String Database_Name = "Cost_Database.db";


    public FeedReaderDbHelperCost (Context context) {
        super(context , Database_Name , null , Database_Version);

    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES_COST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES_COST);
        onCreate(db);
    }
}
