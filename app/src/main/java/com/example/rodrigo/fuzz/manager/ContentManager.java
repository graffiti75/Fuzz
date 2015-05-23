package com.example.rodrigo.fuzz.manager;

import com.example.rodrigo.fuzz.model.Fuzz;

import java.util.List;

/**
 * Created by Rodrigo Cericatto on 23/05/2015.
 */
public class ContentManager {

    //----------------------------------------------
    // Statics
    //----------------------------------------------

    // The singleton instance.
    private static ContentManager sInstance = null;

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private List<Fuzz> mFuzzList;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public ContentManager() {}

    public static ContentManager getInstance() {
        if (sInstance == null) {
            sInstance = new ContentManager();
        }
        return sInstance;
    }

    //----------------------------------------------
    // Methods
    //----------------------------------------------

    public void setFuzzList(List<Fuzz> fuzzList) {
        mFuzzList = fuzzList;
    }

    public List<Fuzz> getFuzzList() {
        return mFuzzList;
    }
}