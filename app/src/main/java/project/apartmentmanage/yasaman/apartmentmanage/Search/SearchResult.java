package project.apartmentmanage.yasaman.apartmentmanage.Search;

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

import  project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContract.FeedEntry;
import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContract;
import project.apartmentmanage.yasaman.apartmentmanage.R;

public class SearchResult extends AppCompatActivity {

    private SQLiteDatabase db;

    TextView tv_ownerName_heading;
    TextView tv_ownerName;
    TextView tv_mobileNum;
    TextView tv_familyMember;

    EditText edt_ownerName;
    EditText edt_mobileNum;
    EditText edt_familyMember;

    Button btn_update;

    String p;

    public static String get_newName;
    public static String get_newMobile;
    public static String get_newMember;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        p = getIntent().getExtras().getString("enter_unitnumber");

        db = openOrCreateDatabase("Units_Database.db", Context.MODE_PRIVATE, null);

        Cursor searchRows = db.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntry.Table_Name +
                " where unitnumber='"+p+"'", null);

        tv_ownerName_heading = findViewById(R.id.tv_searchresult_ownerName_heading);
        tv_ownerName = findViewById(R.id.tv_searchresult_ownerName);
        tv_mobileNum = findViewById(R.id.tv_searchresult_mobileNum);
        tv_familyMember = findViewById(R.id.tv_searchresult_familymember);

        edt_ownerName = findViewById(R.id.edt_searchresult_ownerName);
        edt_mobileNum = findViewById(R.id.edt_searchresult_mobileNum);
        edt_familyMember = findViewById(R.id.edt_searchresult_familymember);

        btn_update = findViewById(R.id.btn_searchresult_update);

        if (searchRows.moveToFirst()){

            String item_1 = searchRows.getString(2);
            String item_2 = searchRows.getString(4);
            String item_3 = searchRows.getString(5);

            Log.i("owner name ", item_1);

            tv_ownerName_heading.setText(item_1);
            tv_ownerName.setText(item_1);
            tv_mobileNum.setText(item_2);
            tv_familyMember.setText(item_3);


        } db.close();


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_newName = edt_ownerName.getText().toString();
                get_newMobile = edt_mobileNum.getText().toString();
                get_newMember = edt_familyMember.getText().toString();



                if (get_newName.equals("") || get_newMobile.equals("") || get_newMember.equals("")){

                    Log.e("Error ","you didn't wrote anything");

                    Toast.makeText(getApplicationContext(),"You can't leave field empty" , Toast.LENGTH_SHORT).show();

                   /* edt_ownerName.setText("");
                    edt_mobileNum.setText("");
                    edt_familyMember.setText("");*/


                } else {

                    try {

                        db = openOrCreateDatabase("Units_Database.db", Context.MODE_PRIVATE, null);

                        db.execSQL("UPDATE " + FeedEntry.Table_Name + " SET owner='" + get_newName +
                                "' , mobile ='" + get_newMobile +
                                "' , familymember ='" + get_newMember + "' WHERE unitnumber ='" + p + "'");

                        Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_SHORT).show();
                        db.close();

                        edt_ownerName.setText("");
                        edt_mobileNum.setText("");
                        edt_familyMember.setText("");


                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), "Can't update !", Toast.LENGTH_SHORT).show();
                    }

                }

             }
        });

    }

}
