package com.rudra.techstorm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EventActivity extends AppCompatActivity {

    ImageView imageView;
    TextView eventDetails;
    TextView eventRules;
    TextView eventTitle;
    TextView eventHelpDesk;
    TextView eventFees;
    TextView collapsingTextView;
    ImageButton helpDeskCallButton;
    Button registerButton;
    Button registerButtonExtra;
    ImageButton backButton;
    String callNumber="123456789";
    int CategoryNumber;
    int EventNumber;
    int registrationId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent intent=getIntent();
        CategoryNumber=intent.getIntExtra("CategoryNumber",0);
        EventNumber=intent.getIntExtra("EventNumber",0);
        imageView=findViewById(R.id.imageView);
        eventDetails=findViewById(R.id.eventDetails);
        eventRules=findViewById(R.id.eventRules);
        eventTitle=findViewById(R.id.eventTitle);
        eventHelpDesk=findViewById(R.id.eventHelpDesk);
        helpDeskCallButton=findViewById(R.id.helpDeskCallButton);
        registerButton=findViewById(R.id.registerButton);
        backButton=findViewById(R.id.eventBackButton);
        collapsingTextView=findViewById(R.id.collapsingTextView);
        eventFees=findViewById(R.id.eventFees);
        registerButtonExtra=findViewById(R.id.registerButtonExtra);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        registerButtonExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if(currentUser!=null)
                {
                    int registrationIdExtra=107;
                    Intent intent=new Intent(EventActivity.this,RegistrationActivity.class);
                    intent.putExtra("event_id",registrationIdExtra);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(EventActivity.this,"Please Sign In to register",Toast.LENGTH_LONG).show();
                }
            }
        });

        setupLayout();

        helpDeskCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",callNumber,null));
                startActivity(callIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if(currentUser!=null)
                {
                    Intent intent=new Intent(EventActivity.this,RegistrationActivity.class);
                    intent.putExtra("event_id",registrationId);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(EventActivity.this,"Please Sign In to register",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setupLayout() {

        switch(CategoryNumber)
        {
            case 0:
                switch(EventNumber)
                {
                    case 0:
                        eventTitle.setText(R.string.FantaCTitle);
                        collapsingTextView.setText(R.string.FantaCTitle);
                        eventDetails.setText(R.string.FantaCDetails);
                        eventRules.setText(R.string.FantaCRules);
                        eventHelpDesk.setText(R.string.FantaCHelp);
                        eventFees.setText(R.string.FantaCFees);
                        imageView.setImageResource(R.drawable.fantac);
                        registrationId=1;
                        callNumber=getResources().getString(R.string.FantaCContact);
                        break;
                    case 1:
                        eventTitle.setText(R.string.OmegatrixTitle);
                        collapsingTextView.setText(R.string.OmegatrixTitle);
                        eventDetails.setText(R.string.OmegatrixDetails);
                        eventRules.setText(R.string.OmegatrixRules);
                        eventHelpDesk.setText(R.string.OmegatrixHelp);
                        eventFees.setText(R.string.OmegatrixFees);
                        imageView.setImageResource(R.drawable.omegatrix);
                        registrationId=2;
                        callNumber=getResources().getString(R.string.OmegatrixContact);
                        break;
                    case 2:
                        eventTitle.setText(R.string.AppManiaTitle);
                        collapsingTextView.setText(R.string.AppManiaTitle);
                        eventDetails.setText(R.string.AppManiaDetails);
                        eventRules.setText(R.string.AppManiaRules);
                        eventHelpDesk.setText(R.string.AppManiaHelp);
                        eventFees.setText(R.string.AppManiaFees);
                        imageView.setImageResource(R.drawable.app_mania);
                        registrationId=3;
                        callNumber=getResources().getString(R.string.AppManiaContact);
                        break;
                    case 3:
                        eventTitle.setText(R.string.TechnomaniaTitle);
                        collapsingTextView.setText(R.string.TechnomaniaTitle);
                        eventDetails.setText(R.string.TechnomaniaDetails);
                        eventRules.setText(R.string.TechnomaniaRules);
                        eventHelpDesk.setText(R.string.TechnomaniaHelp);
                        eventFees.setText(R.string.TechnomaniaFees);
                        imageView.setImageResource(R.drawable.technomania);
                        registrationId=4;
                        callNumber=getResources().getString(R.string.TechnomaniaContact);
                        break;
                }
                break;
            case 1:
                switch(EventNumber)
                {
                    case 0:
                        eventTitle.setText(R.string.FifaTitle);
                        collapsingTextView.setText(R.string.FifaTitle);
                        eventDetails.setText(R.string.FifaDetails);
                        eventRules.setText(R.string.FifaRules);
                        eventHelpDesk.setText(R.string.FifaHelp);
                        eventFees.setText(R.string.FifaFees);
                        imageView.setImageResource(R.drawable.fifa);
                        registrationId=5;
                        callNumber=getResources().getString(R.string.FifaContact);
                        break;
                    case 1:
                        eventTitle.setText(R.string.KhetTitle);
                        collapsingTextView.setText(R.string.KhetTitle);
                        eventDetails.setText(R.string.KhetDetails);
                        eventRules.setText(R.string.KhetRules);
                        eventHelpDesk.setText(R.string.KhetHelp);
                        eventFees.setText(R.string.KhetFees);
                        imageView.setImageResource(R.drawable.khet);
                        registrationId=8;
                        callNumber=getResources().getString(R.string.KhetContact);
                        break;
                    case 2:
                        eventTitle.setText(R.string.CocTitle);
                        collapsingTextView.setText(R.string.CocTitle);
                        eventDetails.setText(R.string.CocDetails);
                        eventRules.setText(R.string.CocRules);
                        eventHelpDesk.setText(R.string.CocHelp);
                        eventFees.setText(R.string.CocFees);
                        imageView.setImageResource(R.drawable.coc);
                        registrationId=6;
                        callNumber=getResources().getString(R.string.CocContact);
                        break;
                    case 3:
                        eventTitle.setText(R.string.PubgTitle);
                        collapsingTextView.setText(R.string.PubgTitle);
                        eventDetails.setText(R.string.PubgDetails);
                        eventRules.setText(R.string.PubgRules);
                        eventHelpDesk.setText(R.string.PubgHelp);
                        eventFees.setText(R.string.PubgFees);
                        imageView.setImageResource(R.drawable.pubg);
                        registrationId=7;
                        callNumber=getResources().getString(R.string.PubgContact);
                        registerButtonExtra.setVisibility(View.VISIBLE);
                        registerButton.setText("REGISTER TEAM");
                        break;

                }break;

            case 2:
                switch(EventNumber)
                {
                    case 0:
                        eventTitle.setText(R.string.RoTerranceTitle);
                        collapsingTextView.setText(R.string.RoTerranceTitle);
                        eventDetails.setText(R.string.RoTerranceDetails);
                        eventRules.setText(R.string.RoTerranceRules);
                        eventHelpDesk.setText(R.string.RoTerranceHelp);
                        eventFees.setText(R.string.RoTerranceFees);
                        imageView.setImageResource(R.drawable.ro_terrance);
                        registrationId=9;
                        callNumber=getResources().getString(R.string.RoTerranceContact);
                        break;
                    case 1:
                        eventTitle.setText(R.string.RoCombatTitle);
                        collapsingTextView.setText(R.string.RoCombatTitle);
                        eventDetails.setText(R.string.RoCombatDetails);
                        eventRules.setText(R.string.RoCombatRules);
                        eventHelpDesk.setText(R.string.RoCombatHelp);
                        eventFees.setText(R.string.RoCombatFees);
                        imageView.setImageResource(R.drawable.ro_combat);
                        registrationId=10;
                        callNumber=getResources().getString(R.string.RoCombatContact);
                        break;
                    case 2:
                        eventTitle.setText(R.string.RoSoccerTitle);
                        collapsingTextView.setText(R.string.RoSoccerTitle);
                        eventDetails.setText(R.string.RoSoccerDetails);
                        eventRules.setText(R.string.RoSoccerRules);
                        eventHelpDesk.setText(R.string.RoSoccerHelp);
                        eventFees.setText(R.string.RoSoccerFees);
                        imageView.setImageResource(R.drawable.ro_soccer);
                        registrationId=11;
                        callNumber=getResources().getString(R.string.RoSoccerContact);
                        break;

                    case 3:
                        eventTitle.setText(R.string.RoPickerTitle);
                        collapsingTextView.setText(R.string.RoPickerTitle);
                        eventDetails.setText(R.string.RoPickerDetails);
                        eventRules.setText(R.string.RoPickerRules);
                        eventHelpDesk.setText(R.string.RoPickerHelp);
                        eventFees.setText(R.string.RoPickerFees);
                        imageView.setImageResource(R.drawable.ro_picker);
                        registrationId=12;
                        callNumber=getResources().getString(R.string.RoPickerContact);
                        break;

                    case 4:
                        eventTitle.setText(R.string.RoNavigatorTitle);
                        collapsingTextView.setText(R.string.RoNavigatorTitle);
                        eventDetails.setText(R.string.RoNavigatorDetails);
                        eventRules.setText(R.string.RoNavigatorRules);
                        eventHelpDesk.setText(R.string.RoNavigatorHelp);
                        eventFees.setText(R.string.RoNavigatorFees);
                        imageView.setImageResource(R.drawable.ro_navigator);
                        registrationId=13;
                        callNumber=getResources().getString(R.string.RoNavigatorContact);
                        break;

                    case 5:
                        eventTitle.setText(R.string.RoPuzzleTitle);
                        collapsingTextView.setText(R.string.RoPuzzleTitle);
                        eventDetails.setText(R.string.RoPuzzleDetails);
                        eventRules.setText(R.string.RoPuzzleRules);
                        eventHelpDesk.setText(R.string.RoPuzzleHelp);
                        eventFees.setText(R.string.RoPuzzleFees);
                        imageView.setImageResource(R.drawable.ro_puzzle);
                        registrationId=14;
                        callNumber=getResources().getString(R.string.RoPuzzleContact);
                        break;
                    case 6:
                        eventTitle.setText(R.string.RoWingsTitle);
                        collapsingTextView.setText(R.string.RoWingsTitle);
                        eventDetails.setText(R.string.RoWingsDetails);
                        eventRules.setText(R.string.RoWingsRules);
                        eventHelpDesk.setText(R.string.RoWingsHelp);
                        eventFees.setText(R.string.RoWingsFees);
                        imageView.setImageResource(R.drawable.ro_wings);
                        registrationId=15;
                        callNumber=getResources().getString(R.string.RoWingsContact);
                        break;
                }break;
            case 3:
                switch(EventNumber)
                {
                    case 0:
                        eventTitle.setText(R.string.PassionWithReelsTitle);
                        collapsingTextView.setText(R.string.PassionWithReelsTitle);
                        eventDetails.setText(R.string.PassionWithReelsDetails);
                        eventRules.setText(R.string.PassionWithReelsRules);
                        eventHelpDesk.setText(R.string.PassionWithReelsHelp);
                        eventFees.setText(R.string.PassionWithReelsFees);
                        imageView.setImageResource(R.drawable.passion_with_reels);
                        registrationId=16;
                        callNumber=getResources().getString(R.string.PassionWithReelsContact);
                        break;
                }break;
        }


    }
}
