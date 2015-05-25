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
import com.example.rodrigo.fuzz.adapter.TextAdapter;
import com.example.rodrigo.fuzz.manager.ContentManager;
import com.example.rodrigo.fuzz.model.Fuzz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Cericatto on 22/05/2015.
 */
public class TextFragment extends Fragment {

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public static TextFragment newInstance() {
        TextFragment fragment = new TextFragment();
        return fragment;
    }

    public TextFragment() {
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
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        setRecyclerView(view);
        return view;
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public void setRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.id_text_fragment_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        TextAdapter adapter = new TextAdapter(getActivity(), getElementsByTextType());
        recyclerView.setAdapter(adapter);
    }

    public List<Fuzz> getElementsByTextType() {
        List<Fuzz> list = ContentManager.getInstance().getFuzzList();
        List<Fuzz> outputList = new ArrayList<Fuzz>();
        for (Fuzz elem : list) {
            String type = elem.getType();
            if (type.equals("text")) {
                outputList.add(elem);
            }
        }
        return outputList;
    }
}