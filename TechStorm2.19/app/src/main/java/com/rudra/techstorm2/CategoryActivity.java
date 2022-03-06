package com.rudra.techstorm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class CategoryActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private int pageCount;
    private int categoryNumber;
    private ImageButton rightButton;
    private ImageButton leftButton;
    private ImageButton categoryBackButton;
    private Button readMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        rightButton = findViewById(R.id.rightChevron);
        leftButton = findViewById(R.id.leftChevron);
        categoryBackButton = findViewById(R.id.categoryBackButton);
        readMoreButton = findViewById(R.id.readMoreButtonCategory);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.arrowScroll(View.FOCUS_RIGHT);
            }
        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.arrowScroll(View.FOCUS_LEFT);
            }
        });
        categoryBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        readMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, EventActivity.class);
                intent.putExtra("CategoryNumber", categoryNumber);
                intent.putExtra("EventNumber", mViewPager.getCurrentItem());
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        categoryNumber = intent.getIntExtra("CategoryNumber", 0);

        int[] headingID;
        int[] descID;
        int[] logoID;

        switch (categoryNumber) {
            case 0:
                headingID = new int[]{R.string.FantaCTitle, R.string.OmegatrixTitle, R.string.AppManiaTitle, R.string.TechnomaniaTitle};
                descID = new int[]{R.string.FantaCTags, R.string.OmegatrixTags, R.string.AppManiaTags, R.string.TechnomaniaTags};
                logoID = new int[]{R.drawable.fantac, R.drawable.omegatrix, R.drawable.app_mania, R.drawable.technomania};

                break;
            case 1:
                headingID = new int[]{R.string.FifaTitle, R.string.KhetTitle, R.string.CocTitle,R.string.PubgTitle};
                descID = new int[]{R.string.FifaTags, R.string.KhetTags, R.string.CocTags, R.string.PubgTags};
                logoID = new int[]{R.drawable.fifa, R.drawable.khet, R.drawable.coc, R.drawable.pubg};

                break;
            case 2:
                headingID = new int[]{R.string.RoTerranceTitle, R.string.RoCombatTitle, R.string.RoSoccerTitle, R.string.RoPickerTitle, R.string.RoNavigatorTitle, R.string.RoPuzzleTitle, R.string.RoWingsTitle};
                descID = new int[]{R.string.RoTerranceTags, R.string.RoCombatTags, R.string.RoSoccerTags, R.string.RoPickerTags, R.string.RoNavigatorTags, R.string.RoPuzzleTags, R.string.RoWingsTags};
                logoID = new int[]{R.drawable.ro_terrance, R.drawable.ro_combat, R.drawable.ro_soccer, R.drawable.ro_picker, R.drawable.ro_navigator, R.drawable.ro_puzzle, R.drawable.ro_wings};

                break;
            default:
                headingID = new int[]{R.string.PassionWithReelsTitle};
                descID = new int[]{R.string.PassionWithReelsTags};
                logoID = new int[]{R.drawable.passion_with_reels};

        }
        pageCount = headingID.length;
        mViewPager = findViewById(R.id.viewPagerCategory);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), headingID, descID, logoID,categoryNumber));
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
        setButtonVisibility();
    }

    public void setButtonVisibility() {

        if ((mViewPager.getCurrentItem() == 0) ||(mViewPager.getCurrentItem() == pageCount - 1))
        {
            if(mViewPager.getCurrentItem() == 0)

            {
                leftButton.setActivated(false);
                leftButton.setVisibility(View.INVISIBLE);
            }
            if(mViewPager.getCurrentItem() == pageCount - 1)
            {
                rightButton.setActivated(false);
                rightButton.setVisibility(View.INVISIBLE);
            }


        }
        else
        {
            leftButton.setActivated(true);
            leftButton.setVisibility(View.VISIBLE);
            rightButton.setActivated(true);
            rightButton.setVisibility(View.VISIBLE);
        }



    }


}
