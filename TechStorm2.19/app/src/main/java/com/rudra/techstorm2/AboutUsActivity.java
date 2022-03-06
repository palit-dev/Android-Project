package com.rudra.techstorm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ImageButton backButton=findViewById(R.id.aboutusBackButton);
        ImageButton shirshoCallButton=findViewById(R.id.shirshoCallButton);
        ImageButton bisheswarCallButton=findViewById(R.id.bisheswarcallButton);
        ImageButton abhishekCallButton=findViewById(R.id.abhishekCallButton);
        ImageButton tathagataCallButton=findViewById(R.id.tathagataCallButton);
        ImageButton locateButton=findViewById(R.id.locateButton);
        ImageButton bppimtMailButton=findViewById(R.id.bppimtMailButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        shirshoCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",getString(R.string.shirsho_number),null));
                startActivity(callIntent);
            }
        });
        tathagataCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",getString(R.string.tathagata_number),null));
                startActivity(callIntent);
            }
        });
        bisheswarCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",getString(R.string.bisheshwar_number),null));
                startActivity(callIntent);
            }
        });
        abhishekCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",getString(R.string.abhishek_number),null));
                startActivity(callIntent);
            }
        });

        locateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("geo:22.6296285,88.4345555?q=B.P.+Poddar+Institute+of+Management+and+Technology"));
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(mapIntent);
                }
                else
                {
                    Toast.makeText(AboutUsActivity.this,"Please install Google Maps",Toast.LENGTH_LONG).show();
                }
            }
        });

        bppimtMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent=new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:com.rudra.com.rudra.techstorm2.19@gmail.com"));
                if(mailIntent.resolveActivity(getPackageManager())!=null)
                    startActivity(mailIntent);


            }
        });
    }
}
