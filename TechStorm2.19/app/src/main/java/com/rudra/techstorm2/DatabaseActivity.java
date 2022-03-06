package com.rudra.techstorm2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

public class DatabaseActivity extends AppCompatActivity {

    private ImageButton backButton;
    private LinearLayout databaseLinearLayout;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String userEmail;
    private String[] eName;
    private TextView endBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        backButton = findViewById(R.id.databaseBackButton);
        databaseLinearLayout = findViewById(R.id.databaseLinearLayout);
        endBox = findViewById(R.id.databaseEndBox);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        eName = new String[]{"Fanta C", "Omegatrix", "AppMania", "Technomania", "Fifa", "Clash of Clans", "PUBG Squad",
                "PUBG Solo", "Khet", "Ro-Terrain", "Ro-Combat", "Ro-Soccer", "Ro-Navigator", "Ro-Picker",
                "Ro-Puzzle", "Ro-Wings", "Passion With Reels"};


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            userEmail = user.getEmail();
            databaseLinearLayout.removeAllViews();
            for (final String s : eName) {
                DocumentReference docRef = db.collection(s).document(userEmail);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            endBox.setText("Loading...");
                            Team team = documentSnapshot.toObject(Team.class);
                            addLayout(s, team.getLeader_name(), team.getLeader_email(),
                                    team.getTeam_name(), team.getMemberName(), team.getCollege_Name(),
                                    team.getContact_number() + "\n" + team.getAlt_contact_number());
                        }
                    }
                });

            }


        } else {
            endBox.setText("Please sign up");
        }
    }

    private void addLayout(String event_name, String leader_name, String leader_email, String team_name, List<String> members, String college, String contact) {
        View detailsLayout = LayoutInflater.from(this).inflate(R.layout.details_layout, databaseLinearLayout, false);
        TextView leaderName = detailsLayout.findViewById(R.id.bodyLeaderName);
        TextView leaderEmail = detailsLayout.findViewById(R.id.bodyLeaderEmail);
        TextView teamName = detailsLayout.findViewById(R.id.bodyEvent);
        TextView memberName = detailsLayout.findViewById(R.id.bodyMembers);
        TextView collegeName = detailsLayout.findViewById(R.id.bodyCollege);
        TextView contactDetails = detailsLayout.findViewById(R.id.bodyContact);
        TextView databaseEventTitle = detailsLayout.findViewById(R.id.databaseEventTitle);
        CardView databaseCardView = detailsLayout.findViewById(R.id.databaseCardView);
        final ImageButton readMoreButton = detailsLayout.findViewById(R.id.databaseMoreButton);
        final RelativeLayout collapsingDetails = detailsLayout.findViewById(R.id.collapsingDetails);

        collapsingDetails.setVisibility(View.GONE);
        databaseCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (collapsingDetails.getVisibility() == View.VISIBLE) {
                    collapsingDetails.setVisibility(View.GONE);

                    readMoreButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.arrow_down, null));
                } else {
                    collapsingDetails.setVisibility(View.VISIBLE);
                    //readMoreButton.setImageDrawable(getResources().getDrawable(R.drawable.arrow_up));
                    readMoreButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.arrow_up, null));
                }

            }
        });
        readMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (collapsingDetails.getVisibility() == View.VISIBLE) {
                    collapsingDetails.setVisibility(View.GONE);
                    //readMoreButton.setImageDrawable(getResources().getDrawable(R.drawable.arrow_down));
                    readMoreButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.arrow_down, null));
                } else {
                    collapsingDetails.setVisibility(View.VISIBLE);
                    //readMoreButton.setImageDrawable(getResources().getDrawable(R.drawable.arrow_up));
                    readMoreButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.arrow_up, null));
                }

            }
        });

        String memberString = "";
        leaderName.setText(leader_name);
        leaderEmail.setText(leader_email);
        teamName.setText(team_name);
        for (int i = 0; i < members.size(); i++)
            memberString = memberString + members.get(i) + "\n";
        memberName.setText(memberString);
        collegeName.setText(college);
        contactDetails.setText(contact);
        databaseEventTitle.setText(event_name);

        databaseLinearLayout.addView(detailsLayout);
        endBox.setText("That's all folks");
    }
}
