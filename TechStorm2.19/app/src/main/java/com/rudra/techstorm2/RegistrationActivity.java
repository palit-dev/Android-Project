package com.rudra.techstorm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class RegistrationActivity extends AppCompatActivity {

    private static final int max_size = 7;
    private FirebaseAuth mAuth;
    private TextInputEditText leaderName;
    private TextInputEditText leaderEmail;
    private TextInputEditText eventName;
    private TextInputEditText teamName;
    private TextInputEditText collegeName;
    private TextInputEditText contactNumber;
    private TextInputEditText altContactNumber;
    private TextInputEditText[] memberName;
    private Button finishButton;
    private ImageButton backButton;
    private TextInputLayout[] member;
    private Spinner leaderGender;
    private Spinner[] memberGender;
    private String eName="Hello World";
    private FirebaseFirestore db;
    private int size=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        finishButton=findViewById(R.id.registrationFinish);
        backButton=findViewById(R.id.registrationBackButton);
        leaderName=findViewById(R.id.leaderName);
        leaderEmail=findViewById(R.id.leaderEmailId);
        eventName=findViewById(R.id.registrationEventName);
        teamName=findViewById(R.id.registrationTeamName);
        collegeName=findViewById(R.id.registrationCollegeName);
        contactNumber=findViewById(R.id.registrationContactNumber);
        altContactNumber=findViewById(R.id.registrationAlternateContactNumber);
        leaderGender=findViewById(R.id.leaderSpinner);
        member=new TextInputLayout[max_size];
        memberGender=new Spinner[max_size];
        memberName=new TextInputEditText[max_size];

        for(int i=0;i<max_size;i++)
        {
            int resId=getResources().getIdentifier("memberSpinner"+i,"id",getPackageName());
            memberGender[i]=findViewById(resId);
            resId=getResources().getIdentifier("memberName"+i,"id",getPackageName());
            memberName[i]=findViewById(resId);
            resId=getResources().getIdentifier("member"+i,"id",getPackageName());
            member[i]=findViewById(resId);
        }



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(checkFormValidity())
                {

                    CollectionReference dbEvents=db.collection(eName);
                    String team_name=teamName.getText().toString().trim();
                    String leader_name=leaderName.getText().toString().trim();
                    String leader_dept=leaderGender.getSelectedItem().toString().trim();
                    String leader_email=leaderEmail.getText().toString().trim();
                    int team_size=size;
                    String[] member_name=new String[size-1];
                    String[] member_dept=new String[size-1];
                    for (int i=0;i<size-1;i++)
                    {
                        member_name[i]=memberName[i].getText().toString().trim();
                        member_dept[i]=memberGender[i].getSelectedItem().toString().trim();
                    }
                    String college_name=collegeName.getText().toString().trim();
                    String contact_no=contactNumber.getText().toString().trim();
                    String altcontact_no=altContactNumber.getText().toString().trim();

                    Team team=new Team(
                            team_name,
                            leader_email,
                            leader_name,
                            leader_dept,
                            team_size,
                            member_name,
                            member_dept,
                            college_name,
                            contact_no,
                            altcontact_no);

                    dbEvents.document(leader_email).set(team).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(RegistrationActivity.this,"Registration successful", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegistrationActivity.this,"Registration Unsuccessful", Toast.LENGTH_LONG).show();
                                }
                            });

                }

            }
        });



    }

    private boolean checkFormValidity()
    {
        boolean flag=true;

        if(teamName.getText().toString().trim().equals(""))
        {
            teamName.setError("Team name cannot be empty");
            flag=false;
        }

        if(collegeName.getText().toString().trim().equals(""))
        {
            collegeName.setError("College name cannot be empty");
            flag=false;
        }

        if(contactNumber.getText().toString().trim().equals(""))
        {
            contactNumber.setError("Contact number cannot be empty");
            flag=false;
        }

        if(altContactNumber.getText().toString().trim().equals("") )
        {
            altContactNumber.setError("Alternate number cannot be empty");
            flag=false;
        }

        if(leaderGender.getSelectedItem().toString().equals("None"))
        {
            ((TextView)leaderGender.getSelectedView()).setError("Invalid");
            flag=false;
        }

        for(int i=0;i<size-1;i++)
        {
            if(memberName[i].getText().toString().trim().equals(""))
            {
                memberName[i].setText("Not Available");
            }
            else
            {
                if(memberGender[i].getSelectedItem().toString().equals("None"))
                {
                    ((TextView)memberGender[i].getSelectedView()).setError("Invalid");
                    flag=false;
                }

            }
        }

        return flag;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {


        if (user != null)
        {
            finishButton.setEnabled(true);
            teamName.setEnabled(true);
            collegeName.setEnabled(true);
            contactNumber.setEnabled(true);
            altContactNumber.setEnabled(true);
            leaderGender.setEnabled(true);
            for(int i=0;i<max_size;i++)
            {
                member[i].setEnabled(true);
                Log.d("Flag",i+"");
            }

            switch(getIntent().getIntExtra("event_id",1))
            {
                case 1:     eName="Fanta C";        size=2; break;
                case 2:     eName="Omegatrix";      size=1; break;
                case 3:     eName="AppMania";       size=5; break;
                case 4:     eName="Technomania";    size=4; break;
                case 5:     eName="Fifa";           size=1; break;
                case 6:     eName="Clash of Clans"; size=1; break;
                case 7:     eName="PUBG Squad";     size=4; break;
                case 107:   eName="PUBG Solo";      size=1; break;
                case 8:     eName="Khet";           size=1; break;
                case 9:     eName="Ro-Terrain";     size=5; break;
                case 10:    eName="Ro-Combat";      size=5; break;
                case 11:    eName="Ro-Soccer";      size=5; break;
                case 13:    eName="Ro-Navigator";   size=5; break;
                case 12:    eName="Ro-Picker";      size=5; break;
                case 14:    eName="Ro-Puzzle";      size=3; break;
                case 15:    eName="Ro-Wings";       size=5; break;
                case 16:    eName="Passion With Reels"; size=8; break;
            }


            eventName.setText(eName);
            leaderName.setText(user.getDisplayName());
            leaderEmail.setText(user.getEmail());
            for(int i=size-1;i<max_size;i++)
            {
                member[i].setVisibility(View.GONE);
            }

        }
        else
        {
            finishButton.setEnabled(false);
            teamName.setEnabled(false);
            collegeName.setEnabled(false);
            contactNumber.setEnabled(false);
            altContactNumber.setEnabled(false);
            leaderGender.setEnabled(false);
            for(int i=0;i<max_size;i++)
            {
                member[i].setEnabled(false);
            }
            Toast.makeText(RegistrationActivity.this,"Please Sign In to register",Toast.LENGTH_LONG).show();
        }
    }



}
