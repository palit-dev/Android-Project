package com.rudra.techstorm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyViewPagerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if(getArguments().getBoolean("Home"))
        {
            View inf=inflater.inflate(R.layout.view_pager_fragment_home,container,false);
            TextView mHeading= inf.findViewById(R.id.swipeScreenHeading);
            TextView mDesc= inf.findViewById(R.id.swipeScreenDescription);
            ImageView mLogo= inf.findViewById(R.id.swipeScreenLogo);

            mHeading.setText(getArguments().getInt("Heading"));
            mDesc.setText(getArguments().getInt("Description"));
            mLogo.setImageResource(getArguments().getInt("Logo"));
            mLogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CategoryActivity.class);
                    intent.putExtra("CategoryNumber", getArguments().getInt("EventNumber"));
                    startActivity(intent);
                }
            });
            mDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CategoryActivity.class);
                    intent.putExtra("CategoryNumber", getArguments().getInt("EventNumber"));
                    startActivity(intent);
                }
            });
            mHeading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CategoryActivity.class);
                    intent.putExtra("CategoryNumber", getArguments().getInt("EventNumber"));
                    startActivity(intent);
                }
            });
            return inf;
        }


        View inf=inflater.inflate(R.layout.view_pager_fragment_category,container,false);
        TextView mHeading= inf.findViewById(R.id.swipeScreenCategoryHeading);
        TextView mDesc= inf.findViewById(R.id.swipeScreenCategoryDescription);
        ImageView mLogo= inf.findViewById(R.id.swipeScreenCategoryLogo);

        mHeading.setText(getArguments().getInt("Heading"));
        mDesc.setText(getArguments().getInt("Description"));
        mLogo.setImageResource(getArguments().getInt("Logo"));
        mLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra("EventNumber", getArguments().getInt("EventNumber"));
                intent.putExtra("CategoryNumber",getArguments().getInt("CategoryNumber"));
                startActivity(intent);
            }
        });
        mDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra("EventNumber", getArguments().getInt("EventNumber"));
                intent.putExtra("CategoryNumber",getArguments().getInt("CategoryNumber"));
                startActivity(intent);
            }
        });
        mHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra("EventNumber", getArguments().getInt("EventNumber"));
                intent.putExtra("CategoryNumber",getArguments().getInt("CategoryNumber"));
                startActivity(intent);
            }
        });
        return inf;



    }
}
