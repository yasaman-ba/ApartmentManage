package project.apartmentmanage.yasaman.apartmentmanage.Units;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

import project.apartmentmanage.yasaman.apartmentmanage.Costs.ShowCost;
import project.apartmentmanage.yasaman.apartmentmanage.R;

import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContract.FeedEntry;

public class Units extends AppCompatActivity{

    private static final String TEXT_TYPE =" TEXT";


    private SQLiteDatabase db ;

    String selectitem;

    ListView lv_units ;

    Button btn_addunits;

    ArrayList<String> Names ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);

        btn_addunits = findViewById(R.id.btn_units_addunits);

        btn_addunits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(Units.this, AddUnit.class);
                startActivity(next);

            }
        });

    }

    protected void onResume(){

        super.onResume();

        try {
            db = openOrCreateDatabase("Units_Database.db", Context.MODE_PRIVATE,null);
            Cursor allrows  = db.rawQuery("SELECT * FROM "+  FeedEntry.Table_Name, null);

            Names = new ArrayList<String>();

            if (allrows.moveToFirst()){
                do {

                    String NAME = allrows.getString(2);

                    Names.add(NAME);

                    Log.d("Show Names: ", NAME);

                }while (allrows.moveToNext());

            } db.close();

            lv_units = findViewById(R.id.listview_units);

            ArrayAdapter<String> NameAdapter = new ArrayAdapter<String>(Units.this ,
                    android.R.layout.simple_list_item_1 , Names);

            lv_units.setAdapter(NameAdapter);

            lv_units.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    selectitem = Names.get(position);

                    Intent next = new Intent(Units.this, ShowCost.class);
                    next.putExtra("selectitem", selectitem);
                    startActivity(next);
                }
            });

        }catch (Exception e){

            Toast.makeText(getApplicationContext(), "Your DataBase is empty !", Toast.LENGTH_SHORT).show();

            final String SQL_CREATE_ENTRIES =
                    "CREATE TABLE IF  NOT EXISTS "+FeedEntry.Table_Name +
                            "(" + BaseColumns._ID + "INTEGER PRIMARY KEY ," +
                            FeedEntry.Column_Name_UNITNUMBER + TEXT_TYPE +
                            "," + FeedEntry.Column_Name_OWNER + TEXT_TYPE +
                            "," + FeedEntry.Column_Name_PHONE + TEXT_TYPE +
                            "," + FeedEntry.Column_Name_MOBILE + TEXT_TYPE +
                            "," + FeedEntry.Column_Name_FAMILY_MEMBER + TEXT_TYPE +");";

            db.execSQL(SQL_CREATE_ENTRIES);


        }
    }

    protected void onPause(){

        super.onPause();
        db.close();

        /* when we come back to previous activity we should close db to avoid from information damage*/

    }
}
