package project.apartmentmanage.yasaman.apartmentmanage.Backup;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import project.apartmentmanage.yasaman.apartmentmanage.R;

public class Backup extends AppCompatActivity {

    Button btn_backup;
    EditText edt_backupName;

    String get_backupName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);

        btn_backup = findViewById(R.id.btn_backup);
        edt_backupName = findViewById(R.id.edt_backup_name);

        btn_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_backupName = edt_backupName.getText().toString();

                if ( get_backupName.equals("")){

                    Toast.makeText(getApplicationContext(),"You can't leave field empty" , Toast.LENGTH_SHORT).show();


                } else {

                    try {

                        copyAppDbToDownloadFolder();

                        Toast.makeText(getApplicationContext(),
                                "Your Backup file now is available in Download Folder directory!", Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {

                        e.printStackTrace();


                    }

                }
            }
        });


    }

    public void copyAppDbToDownloadFolder() {

        try{


            File backupCostDB = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), get_backupName+"cost.db");
            File backupUnitDB = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), get_backupName+"unit.db");

            File costDB = getApplicationContext().getDatabasePath("Cost_Database.db");
            File unitDB = getApplicationContext().getDatabasePath("Units_Database.db");

            if(costDB.exists()){

                FileInputStream costFileinputStream = new FileInputStream(costDB);
                FileInputStream unitFileinputStream = new FileInputStream(costDB);

                FileOutputStream costFileOutputStream = new FileOutputStream(backupCostDB);
                FileOutputStream unitFileOutputStream = new FileOutputStream(backupUnitDB);

                costFileinputStream.close();
                unitFileinputStream.close();

                costFileOutputStream.close();
                unitFileOutputStream.close();

                Log.i("DataBases" , "are copied to download folder successfully");

            }



        } catch (Exception e){


            Toast.makeText(getApplicationContext(), "Fail!", Toast.LENGTH_SHORT).show();

            Log.d("Fail", "reason:", e);



        }
    }

}
