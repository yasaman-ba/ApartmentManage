package project.apartmentmanage.yasaman.apartmentmanage.Search;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContract;
import project.apartmentmanage.yasaman.apartmentmanage.R;

public class Search extends AppCompatActivity {

    public static String search_item;
    private SQLiteDatabase db;

    ListView lv_Search;

    Button btn_Search;

    EditText edt_Search;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    edt_Search = findViewById(R.id.edt_search_unitnumber);

    btn_Search = findViewById(R.id.btn_search);

    btn_Search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            search_item = edt_Search.getText().toString();


            if (search_item.equals("")){

                Toast.makeText(getApplicationContext(),"You can't leave field empty" , Toast.LENGTH_SHORT).show();


            } else {

                try {

                    db = openOrCreateDatabase("Units_Database.db", Context.MODE_PRIVATE, null);

                    Cursor searchRows = db.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntry.Table_Name +
                            " where unitnumber='" + search_item + "'", null);

                    final ArrayList<String> items = new ArrayList<String>();

                    if (searchRows.moveToFirst()) {

                        do {

                            String item = searchRows.getString(2);
                            items.add(item);

                            Log.i("result ", item);

                        } while (searchRows.moveToNext());
                    }
                    db.close();

                    lv_Search = findViewById(R.id.listview_search);

                    ArrayAdapter<String> NameAdapter = new ArrayAdapter<String>(Search.this, android.R.layout.simple_list_item_1, items);

                    lv_Search.setAdapter(NameAdapter);

                    lv_Search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent next = new Intent(Search.this, SearchResult.class);
                            next.putExtra("enter_unitnumber", search_item);

                            startActivity(next);

                        }
                    });

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Your search doesn't exist !", Toast.LENGTH_SHORT).show();
                }

                edt_Search.setText("");

            }
        }
    });

    }
}
