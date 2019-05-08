package project.apartmentmanage.yasaman.apartmentmanage.Costs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContractCost;
import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderDbHelperCost;
import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContractCost.FeedEntryCost;
import project.apartmentmanage.yasaman.apartmentmanage.R;

public class AddCost extends AppCompatActivity {

    private SQLiteDatabase cdbw ;
    private SQLiteDatabase cdb;
    private FeedReaderDbHelperCost cDbHelper;
    private ContentValues Values ;

    private static final String TEXT_TYPE =" TEXT";

    EditText edt_Unit_Number;
    EditText edt_Year;
    EditText edt_Month;
    EditText edt_GreenSpace;
    EditText edt_Parking;
    EditText edt_Cleaning;

    Button btn_save;

    public static  String get_unitnumber;
    public static String get_year;
    public static String get_month;
    public static String get_greenspace;
    public static String get_parking;
    public static String get_cleaning;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcost);

        btn_save = findViewById(R.id.btn_addcost_save);

        cDbHelper = new FeedReaderDbHelperCost(getBaseContext());

        cdbw = cDbHelper.getWritableDatabase();

        edt_Unit_Number = findViewById(R.id.edt_addcost_unitNum);
        edt_Year = findViewById(R.id.edt_addcost_year);
        edt_Month = findViewById(R.id.edt_addcost_month);
        edt_GreenSpace = findViewById(R.id.edt_addcost_greenspace);
        edt_Parking = findViewById(R.id.edt_addcost_parking);
        edt_Cleaning = findViewById(R.id.edt_addcost_cleaning);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                get_unitnumber = edt_Unit_Number.getText().toString();
                get_year = edt_Year.getText().toString();
                get_month = edt_Month.getText().toString();
                get_greenspace = edt_GreenSpace.getText().toString();
                get_parking = edt_Parking.getText().toString();
                get_cleaning = edt_Cleaning.getText().toString();

                if(get_unitnumber.equals("") || get_year.equals("") || get_month.equals("") ||
                            get_greenspace.equals("") || get_parking.equals("") || get_cleaning.equals("")){

                    Toast.makeText(getApplicationContext(),"You can't leave field empty" , Toast.LENGTH_SHORT).show();


                } else {


                    Values = new ContentValues();

                    Values.put(FeedEntryCost.Column_Name_UNITNUMBERC, get_unitnumber);
                    Values.put(FeedEntryCost.Column_Name_YEAR, get_year);
                    Values.put(FeedEntryCost.Column_Name_MONTH, get_month);
                    Values.put(FeedEntryCost.Column_Name_GREENSPACE, get_greenspace);
                    Values.put(FeedEntryCost.Column_Name_PARKING, get_parking);
                    Values.put(FeedEntryCost.Column_Name_CLEANING, get_cleaning);


                    cdbw.insert(FeedEntryCost.Table_Name2, null, Values);

                    String mymonth = Values.getAsString(FeedEntryCost.Column_Name_MONTH);

                    Toast.makeText(getApplicationContext(), "Added Successfully! ", Toast.LENGTH_SHORT).show();

                    Log.i("month", mymonth);

                    edt_Unit_Number.setText("");
                    edt_Year.setText("");
                    edt_Month.setText("");
                    edt_GreenSpace.setText("");
                    edt_Parking.setText("");
                    edt_Cleaning.setText("");

                }


            }
        });

    }

    protected  void onResume(){
        super.onResume();

        try {

            cdb = openOrCreateDatabase("Cost_Database.db", Context.MODE_PRIVATE, null);
            Cursor allrows = cdb.rawQuery("SELECT * FROM " + FeedEntryCost.Table_Name2, null);

            if(allrows.moveToFirst()){

                do {

                    String item = allrows.getString(1);

                    Log.d("show select", item);

                    Toast.makeText(getApplicationContext(), "show select.", Toast.LENGTH_SHORT).show();

                }while (allrows.moveToNext());

            } cdb.close();

        }catch (Exception e){

            final String SQL_CREATE_ENTRIES_COST =
                    "CREATE TABLE IF  NOT EXISTS "+FeedEntryCost.Table_Name2 +
                            "(" + BaseColumns._ID + "INTEGER PRIMARY KEY ," +
                            FeedEntryCost.Column_Name_UNITNUMBERC + TEXT_TYPE +
                            "," +FeedEntryCost.Column_Name_YEAR + TEXT_TYPE +
                            "," + FeedEntryCost.Column_Name_MONTH + TEXT_TYPE +
                            "," + FeedEntryCost.Column_Name_GREENSPACE +TEXT_TYPE +
                            "," + FeedEntryCost.Column_Name_PARKING +TEXT_TYPE +
                            "," + FeedEntryCost.Column_Name_CLEANING +TEXT_TYPE + ");";

            cdb.execSQL(SQL_CREATE_ENTRIES_COST);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        cdbw.close();
    }
}
