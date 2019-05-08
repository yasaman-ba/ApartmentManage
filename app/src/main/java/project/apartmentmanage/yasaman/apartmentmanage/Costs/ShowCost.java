package project.apartmentmanage.yasaman.apartmentmanage.Costs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContract.FeedEntry;
import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContractCost.FeedEntryCost;
import project.apartmentmanage.yasaman.apartmentmanage.R;

public class ShowCost extends AppCompatActivity {

    private final String TAG = "Select item";

    String position ;

    public static String get_greenspace;
    public static String get_parking;
    public static String get_cleaning;

    public static String get_month;
    public static String get_year;
    public static String get_unitnumber;

    TextView tv_show_greenspace;
    TextView tv_show_parking;
    TextView tv_show_cleaning;

    EditText edt_Month;
    EditText edt_Year;

    Button btn_Showcost;

    private SQLiteDatabase db;
    private SQLiteDatabase cdb;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcost);

        position = getIntent().getExtras().getString("selectitem");

        Log.i(TAG, position);

        btn_Showcost = findViewById(R.id.btn_showcost);
        edt_Month = findViewById(R.id.edt_showcost_month);
        edt_Year = findViewById(R.id.edt_showcost_year);

        tv_show_greenspace = findViewById(R.id.tv_showcost_greenspace);
        tv_show_parking = findViewById(R.id.tv_showcost_parking);
        tv_show_cleaning = findViewById(R.id.tv_showcost_cleaning);

        btn_Showcost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_month = edt_Month.getText().toString();
                get_year = edt_Year.getText().toString();


                if( get_year.equals("") || get_month.equals("")) {


                    Toast.makeText(getApplicationContext(), "You can't leave field empty", Toast.LENGTH_SHORT).show();


                  /*  edt_Month.setText("");
                    edt_Year.setText("");*/


                }else {

                    try {

                        db = openOrCreateDatabase("Units_Database.db", Context.MODE_PRIVATE, null);
                        Cursor unitNumberRows = db.rawQuery("SELECT * FROM " +

                                FeedEntry.Table_Name + " WHERE owner='" + position + "'", null);

                        if (unitNumberRows.moveToFirst()) {
                            do {

                                get_unitnumber = unitNumberRows.getString(1);

                                Log.i("result ", get_unitnumber);


                            } while (unitNumberRows.moveToNext());

                        }
                        db.close();

                        cdb = openOrCreateDatabase("Cost_Database.db", Context.MODE_PRIVATE, null);

                        Cursor costRows = cdb.rawQuery("SELECT * FROM " +
                                FeedEntryCost.Table_Name2 + " WHERE unitnumber='" + get_unitnumber + "' AND month='" + get_month + "' AND year='" + get_year + "'", null);

                        if (costRows.moveToFirst()) {
                            do {

                                get_greenspace = costRows.getString(4);
                                get_parking = costRows.getString(5);
                                get_cleaning = costRows.getString(6);


                            } while (costRows.moveToNext());
                        }
                        cdb.close();

                        tv_show_greenspace.setText(get_greenspace);
                        tv_show_parking.setText(get_parking);
                        tv_show_cleaning.setText(get_cleaning);

                        


                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), "Can't find any cost !", Toast.LENGTH_LONG).show();


                    }

                }
            }
        });

    }
}
