package project.apartmentmanage.yasaman.apartmentmanage.FeedReaders;

import android.provider.BaseColumns;

public class FeedReaderContract {

    public FeedReaderContract(){}

    public static abstract class FeedEntry implements BaseColumns {


        /* we called it as My_Table in old code */

        public static final String Table_Name = "Units_Table";
        public static final String Database_Name = "Units_Database.db";

        public static final String Column_Name_UNITNUMBER = "unitnumber";
        public static final String Column_Name_OWNER = "owner";
        public static final String Column_Name_PHONE = "phone";
        public static final String Column_Name_MOBILE = "mobile";
        public static final String Column_Name_FAMILY_MEMBER = "familymember" ;


    }
}
