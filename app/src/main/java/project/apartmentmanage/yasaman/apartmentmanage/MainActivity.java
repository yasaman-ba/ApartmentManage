package project.apartmentmanage.yasaman.apartmentmanage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import  project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContract.FeedEntry;
import  project.apartmentmanage.yasaman.apartmentmanage.FeedReaders.FeedReaderContractCost.FeedEntryCost;


import project.apartmentmanage.yasaman.apartmentmanage.Backup.Backup;
import project.apartmentmanage.yasaman.apartmentmanage.Contact.Contact;
import project.apartmentmanage.yasaman.apartmentmanage.Costs.AddCost;
import project.apartmentmanage.yasaman.apartmentmanage.Search.Search;
import project.apartmentmanage.yasaman.apartmentmanage.Units.Units;

public class MainActivity extends AppCompatActivity {

    ImageView img_home;
    ImageView img_addcost;
    ImageView img_backup;
    ImageView img_search;
    ImageView img_refresh;
    ImageView img_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        img_home = findViewById(R.id.img_home);
        img_addcost = findViewById(R.id.img_addcost);
        img_backup = findViewById(R.id.img_backup);
        img_search = findViewById(R.id.img_search);
        img_refresh = findViewById(R.id.img_refresh);
        img_contact = findViewById(R.id.img_contact);



        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(MainActivity.this, Units.class);
                startActivity(next);

                Toast.makeText(MainActivity.this ,"This is a test!", Toast.LENGTH_LONG).show();

            }
        });

        img_addcost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(MainActivity.this, AddCost.class);
                startActivity(next);

            }
        });

        img_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(MainActivity.this, Backup.class);
                startActivity(next);

            }
        });

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(MainActivity.this, Search.class);
                startActivity(next);


            }
        });

        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                alertDialog.setTitle("Delete Your Data");
                alertDialog.setMessage("Do you want to delete all of your data ?");

                alertDialog.setIcon(R.drawable.delete);

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try{

                            SQLiteDatabase db = openOrCreateDatabase(FeedEntry.Database_Name,
                                    Context.MODE_PRIVATE, null);

                            SQLiteDatabase cdb = openOrCreateDatabase(FeedEntryCost.Database_Name,
                                    Context.MODE_PRIVATE, null);

                            db.execSQL("DROP TABLE " + FeedEntry.Table_Name);
                            cdb.execSQL("DROP TABLE " + FeedEntryCost.Table_Name2);

                            db.close();
                            cdb.close();

                            Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();


                        } catch (Exception e){

                            Toast.makeText(getApplicationContext(), "You Can't Delete Data !", Toast.LENGTH_LONG).show();

                        }
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setCancelable(false);
                alertDialog.show();


            }
        });

        img_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(MainActivity.this, Contact.class);
                startActivity(next);


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
