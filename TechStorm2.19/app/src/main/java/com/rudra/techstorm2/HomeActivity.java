package com.rudra.techstorm2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private int pageCount;
    private DrawerLayout navDrawer;
    private ImageButton menuButton;
    private ImageButton LeftButton;
    private ImageButton RightButton;
    private ImageButton navBackButton;
    private Button readMoreButton;
    private ImageButton youtubeButton;
    private ImageButton facebookButton;
    private ImageButton webButton;
    private NavigationView navigationView;
    private View headerLayout;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotifications",
                    "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "successfully registered to general";
                        if (!task.isSuccessful())
                            msg = "failed to subsribe to general";
                        Log.d("Subscribe", msg);
                    }
                });


        int[] headingID = {R.string.BrainTeaserTitle, R.string.GamingTitle, R.string.RoversTitle, R.string.CreativityTitle};
        int[] descID = {R.string.BrainTeaserDescription, R.string.GamingDescription, R.string.RoversDescription, R.string.CreativityDescription};
        int[] logoID = {R.drawable.brain_teasers_logo, R.drawable.gaming_logo, R.drawable.rover_logo, R.drawable.creativity_logo};
        pageCount = headingID.length;

        navDrawer = findViewById(R.id.drawerLayout);
        navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mViewPager = findViewById(R.id.viewPagerHome);
        navigationView = findViewById(R.id.navView);
        headerLayout = navigationView.getHeaderView(0);
        mAuth = FirebaseAuth.getInstance();
        ImageView techStormLogo = findViewById(R.id.techstormLogo);
        menuButton = findViewById(R.id.userMenu);
        LeftButton = findViewById(R.id.leftChevron);
        RightButton = findViewById(R.id.rightChevron);
        navBackButton = findViewById(R.id.navDrawerBack);
        readMoreButton = findViewById(R.id.readMoreButton);
        facebookButton = findViewById(R.id.facebookButton);
        youtubeButton = findViewById(R.id.youtubeButton);
        webButton = findViewById(R.id.webButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navDrawer.openDrawer(GravityCompat.START);
            }
        });
        LeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.arrowScroll(View.FOCUS_LEFT);
            }
        });
        RightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.arrowScroll(View.FOCUS_RIGHT);
            }
        });
        navBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navDrawer.closeDrawer(GravityCompat.START);
            }
        });
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/n/?Techstorm2.19/";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "vnd.youtube:Hfa0ClYkktc";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

            }
        });
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://techstorm2k19.github.io/techstorm/home.html";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
        readMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                intent.putExtra("CategoryNumber", mViewPager.getCurrentItem());
                startActivity(intent);
            }
        });
        techStormLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webButton.performClick();
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.aboutus)
                    startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));

                else if (id == R.id.team)
                    startActivity(new Intent(HomeActivity.this, TeamActivity.class));

                else if (id == R.id.developer)
                    startActivity(new Intent(HomeActivity.this, DeveloperActivity.class));

                else if (id == R.id.login)
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));

                else if (id == R.id.result) {
                    String url = "https://techstorm2k19.github.io/result/result.html";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } else if (id == R.id.sponsor) {
                    String url = "https://techstorm2k19.github.io/techstorm/home.html";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }
                navDrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        navDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                menuButton.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                menuButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mViewPager.addOnPageChangeListener(new androidx.viewpager.widget.ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setButtonVisibility();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), headingID, descID, logoID));
        mViewPager.setOffscreenPageLimit(3);
        setButtonVisibility();


    }

    public void setButtonVisibility() {


        if (mViewPager.getCurrentItem() == 0) {

            LeftButton.setActivated(false);
            LeftButton.setVisibility(View.INVISIBLE);
            return;
        }

        if (mViewPager.getCurrentItem() == pageCount - 1) {

            RightButton.setActivated(false);
            RightButton.setVisibility(View.INVISIBLE);
            return;
        }

        LeftButton.setActivated(true);
        LeftButton.setVisibility(View.VISIBLE);
        RightButton.setActivated(true);
        RightButton.setVisibility(View.VISIBLE);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {

        TextView userName = headerLayout.findViewById(R.id.userName);
        TextView userID = headerLayout.findViewById(R.id.userID);
        SimpleDraweeView simpleDraweeView = headerLayout.findViewById(R.id.userProfilePicture);
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));

            }
        });
        userID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        if (user != null) {
            String personName = user.getDisplayName();
            String personEmail = user.getEmail();
            Uri personPhoto = user.getPhotoUrl();
            if (personPhoto != null) {
                String profileString = personPhoto.toString();
                String profilePic = profileString.replace("s96-c/photo.jpg", "s200-c/photo.jpg");
                simpleDraweeView.setImageURI(profilePic);
                userName.setText(personName);
                userID.setText(personEmail);

            }
            String[] eName= new String[]{"Fanta C", "Omegatrix", "AppMania", "Technomania", "Fifa", "Clash of Clans", "PUBG Squad",
                    "PUBG Solo", "Khet", "Ro-Terrain", "Ro-Combat", "Ro-Soccer", "Ro-Navigator", "Ro-Picker",
                    "Ro-Puzzle", "Ro-Wings", "Passion With Reels"};
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            for (final String s : eName) {
                DocumentReference docRef = db.collection(s).document(personEmail);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            FirebaseMessaging.getInstance().subscribeToTopic(s.replaceAll(" ","-"))
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            String msg = "successfully registered to event";
                                            if (!task.isSuccessful())
                                                msg = "failed to subsribe to event";
                                            Log.d("Subscribe", msg);
                                        }
                                    });

                        }
                    }
                });

            }

            FirebaseMessaging.getInstance().subscribeToTopic(personEmail.replaceAll("@","-"))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg="successfully registered to email";
                            if(!task.isSuccessful())
                                msg="failed to subsribe to email";
                            Log.d("Subscribe",msg);
                        }
                    });


        } else {
            simpleDraweeView.setActualImageResource(R.drawable.user);
            userName.setText("Guest");
            userID.setText("Welcome to Techstorm");
        }
    }


}
