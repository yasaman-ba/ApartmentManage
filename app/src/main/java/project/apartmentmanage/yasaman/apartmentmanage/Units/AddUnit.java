package project.apartmentmanage.yasaman.apartmentmanage.Units;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderDbHelper;
import project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContract.FeedEntry;
import project.apartmentmanage.yasaman.apartmentmanage.R;

public class AddUnit extends AppCompatActivity {


    EditText edt_Unit_Number;
    EditText edt_Owner_Name;
    EditText edt_Phone_Number;
    EditText edt_Mobile_Number;
    EditText edt_Family_Member;

    Button btn_Save;

    private FeedReaderDbHelper mDbHelper ;
    private SQLiteDatabase db ;
    private ContentValues Values ;

    public static String get_unitNumber;
    public static String get_ownerName;
    public static String get_phoneNumber;
    public static String get_mobileNumber;
    public static String get_familyMember;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addunit);

        mDbHelper = new FeedReaderDbHelper(getBaseContext());


        db = mDbHelper.getWritableDatabase();

        edt_Unit_Number = findViewById(R.id.edt_addunit_unitNum);
        edt_Owner_Name = findViewById(R.id.edt_addunit_ownerName);
        edt_Phone_Number = findViewById(R.id.edt_addunit_phoneNum);
        edt_Mobile_Number = findViewById(R.id.edt_addunit_mobileNum);
        edt_Family_Member = findViewById(R.id.edt_addunit_familymember);

        btn_Save = findViewById(R.id.btn_addunit_save);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_unitNumber = edt_Unit_Number.getText().toString();
                get_ownerName = edt_Owner_Name.getText().toString();
                get_phoneNumber = edt_Phone_Number.getText().toString();
                get_mobileNumber = edt_Mobile_Number.getText().toString();
                get_familyMember = edt_Family_Member.getText().toString();

                if (get_unitNumber.equals("") || get_ownerName.equals("") || get_phoneNumber.equals("") ||
                            get_mobileNumber.equals("") || get_familyMember.equals("")) {


                    Toast.makeText(getApplicationContext(),"You can't leave field empty" , Toast.LENGTH_SHORT).show();


                } else {

                    Values = new ContentValues();


                    Values.put(FeedEntry.Column_Name_UNITNUMBER, get_unitNumber);
                    Values.put(FeedEntry.Column_Name_OWNER, get_ownerName);
                    Values.put(FeedEntry.Column_Name_PHONE, get_phoneNumber);
                    Values.put(FeedEntry.Column_Name_MOBILE, get_mobileNumber);
                    Values.put(FeedEntry.Column_Name_FAMILY_MEMBER, get_familyMember);

                    db.insert(FeedEntry.Table_Name, null, Values);

                    String myunit = Values.getAsString(FeedEntry.Column_Name_UNITNUMBER);

                    Log.i("Addunit", myunit);

                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();

                    edt_Unit_Number.setText("");
                    edt_Owner_Name.setText("");
                    edt_Phone_Number.setText("");
                    edt_Mobile_Number.setText("");
                    edt_Family_Member.setText("");

                }

            }
        });




    }

    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        db.close();

        /* when we come back to previous activity we should close db to avoid from information damage*/
    }
}
