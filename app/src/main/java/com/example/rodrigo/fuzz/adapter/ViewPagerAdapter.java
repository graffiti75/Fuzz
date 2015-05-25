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
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = AllFragment.newInstance();
                break;
            case 1:
                fragment = TextFragment.newInstance();
                break;
            case 2:
                fragment = ImagesFragment.newInstance();
                break;
            default:
                fragment = AllFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}