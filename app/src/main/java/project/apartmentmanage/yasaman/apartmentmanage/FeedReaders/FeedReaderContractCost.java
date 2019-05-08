package project.apartmentmanage.yasaman.apartmentmanage.FeedReaders;

import android.provider.BaseColumns;


public class FeedReaderContractCost {

    public FeedReaderContractCost(){}

    public static abstract class FeedEntryCost implements BaseColumns {


        public static final String Database_Name = "Cost_Database.db";

        // ***********

        public static final String Table_Name2 = "Cost_Table";
        public static final String Column_Name_UNITNUMBERC = "unitnumber";
        public static final String Column_Name_YEAR = "year";
        public static final String Column_Name_MONTH = "month";
        public static final String Column_Name_GREENSPACE = "greenspace";
        public static final String Column_Name_PARKING = "parking";
        public static final String Column_Name_CLEANING = "cleaning";
    }
}
