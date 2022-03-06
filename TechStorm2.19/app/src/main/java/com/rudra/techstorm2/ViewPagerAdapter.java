package com.rudra.techstorm2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int[] headingID;
    private int[] descID;
    private int[] logoID;
    private boolean home;
    private int categoryNumber=-1;

    public ViewPagerAdapter(@NonNull FragmentManager fm,
                            int[] headingID, int[] descID, int[] logoID)
    {
        super(fm);
        this.headingID=headingID;
        this.descID=descID;
        this.logoID=logoID;
        this.home=true;
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm,
                            int[] headingID, int[] descID, int[] logoID, int categoryNumber)
    {
        super(fm);
        this.headingID=headingID;
        this.descID=descID;
        this.logoID=logoID;
        this.home=false;
        this.categoryNumber=categoryNumber;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("Heading",headingID[position]);
        bundle.putInt("Description",descID[position]);
        bundle.putInt("Logo",logoID[position]);
        bundle.putInt("EventNumber",position);
        bundle.putInt("CategoryNumber",categoryNumber);
        bundle.putBoolean("Home",home);
        MyViewPagerFragment mViewPagerFragment=new MyViewPagerFragment();
        mViewPagerFragment.setArguments(bundle);
        return mViewPagerFragment;
    }

    @Override
    public int getCount() {
        return headingID.length;
    }



}
