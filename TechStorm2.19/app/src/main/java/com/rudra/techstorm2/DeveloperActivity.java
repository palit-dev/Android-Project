package com.rudra.techstorm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DeveloperActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        ImageButton backButton=findViewById(R.id.developerBackButton);
        ImageButton contactDeveloper=findViewById(R.id.contact_rudra);
        ImageButton mailDeveloper=findViewById(R.id.mail_rudra);
        ImageButton linkedinDeveloper=findViewById(R.id.linkedin_rudra);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        contactDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","09051552156",null));
                startActivity(callIntent);
            }
        });
        mailDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent=new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:rudra.palit@gmail.com"));
                if(mailIntent.resolveActivity(getPackageManager())!=null)
                    startActivity(mailIntent);

            }
        });
        linkedinDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.linkedin.com/in/rudra-palit/";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

            }
        });
    }
}
