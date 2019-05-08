package project.apartmentmanage.yasaman.apartmentmanage.Contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import project.apartmentmanage.yasaman.apartmentmanage.R;

public class Contact extends AppCompatActivity {

    ImageButton imgbutton_contact ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        imgbutton_contact = findViewById(R.id.imgbutton_contact);

        imgbutton_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Text body:");
                intent.setData(Uri.parse("mailto:yasamanbaghparvar@gmail.com"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

    }
}
