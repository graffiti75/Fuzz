package com.example.rodrigo.fuzz.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rodrigo.fuzz.R;
import com.example.rodrigo.fuzz.adapter.ImagesAdapter;
import com.example.rodrigo.fuzz.manager.ContentManager;
import com.example.rodrigo.fuzz.model.Fuzz;

import java.util.List;

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
        View view = inflater.inflate(R.layout.fragment_images, container, false);
        setRecyclerView(view);
        return view;
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public void setRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.id_images_fragment_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<Fuzz> list = ContentManager.getInstance().getFuzzList();
        ImagesAdapter adapter = new ImagesAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
    }
}