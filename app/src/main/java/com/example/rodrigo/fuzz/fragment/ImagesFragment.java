package com.example.rodrigo.fuzz.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rodrigo.fuzz.R;

/**
 * Created by Rodrigo Cericatto on 22/05/2015.
 */
public class ImagesFragment extends Fragment {

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public static ImagesFragment newInstance() {
        ImagesFragment fragment = new ImagesFragment();
        return fragment;
    }

    public ImagesFragment() {
    }

    //--------------------------------------------------
    // Fragment Life Cycle
    //--------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_images, container, false);
        return v;
    }
}