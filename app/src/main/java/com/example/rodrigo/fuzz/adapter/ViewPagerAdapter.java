package com.example.rodrigo.fuzz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rodrigo.fuzz.fragment.AllFragment;
import com.example.rodrigo.fuzz.fragment.ImagesFragment;
import com.example.rodrigo.fuzz.fragment.TextFragment;

/**
 * Created by Rodrigo on 22/05/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    //--------------------------------------------------
    // FragmentPagerAdapter Methods
    //--------------------------------------------------

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AllFragment.newInstance();
            case 1:
                return TextFragment.newInstance();
            case 2:
                return ImagesFragment.newInstance();
            default:
                return ImagesFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}