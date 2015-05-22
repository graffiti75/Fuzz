package com.example.rodrigo.fuzz.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rodrigo.fuzz.R;
import com.example.rodrigo.fuzz.adapter.FuzzAdapter;
import com.example.rodrigo.fuzz.adapter.ViewPagerAdapter;
import com.example.rodrigo.fuzz.model.Fuzz;
import com.example.rodrigo.fuzz.service.RetrofitService;

import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Rodrigo Cericatto on 22/05/2015.
 */
public class MainActivity extends FragmentActivity {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    public static final String END_POINT = "http://quizzes.fuzzstaging.com/";

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private RetrofitService mService;
    private List<Fuzz> mFavouritesList;

    private MaterialDialog mMaterialDialog;

    private RecyclerView mRecyclerView;
    private FuzzAdapter mAdapter;

    private ViewPager mPager;
    private ViewPagerAdapter mViewPagerAdapter;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the layout.
        setLayout();

        // Retrofit.
        getFuzzData();
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public void setLayout() {
        // Progress Bar.
        mMaterialDialog = new MaterialDialog.Builder(this).title(R.string.progress_dialog).content(R.string.please_wait).progress(true, 0).show();

        // Recycler View.
        //mRecyclerView = (RecyclerView) findViewById(R.id.id_recycler_view);
        //mRecyclerView.setVisibility(View.INVISIBLE);

        // Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        // View Pager.
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.id_view_pager);
        mPager.setAdapter(mViewPagerAdapter);
    }

    public void getFuzzData() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(END_POINT).build();
        mService = restAdapter.create(RetrofitService.class);
        mService.listFuzz(new Callback<List<Fuzz>>() {
            @Override
            public void failure(RetrofitError retrofitError) {
                String cause = retrofitError.getCause().getMessage();
                String text = getString(R.string.error_cause) + cause + ".";
                Crouton.makeText(MainActivity.this, text, Style.ALERT).show();
            }

            @Override
            public void success(List<Fuzz> object, Response response) {
                dataLoadedSuccessfully(object);
            }
        });
    }

    public void dataLoadedSuccessfully(List<Fuzz> object) {
        // Data loaded.
        //mRecyclerView.setVisibility(View.VISIBLE);
        mMaterialDialog.cancel();

        // Set lists.
        setRecyclerView();
    }

    public void setRecyclerView() {
        /*
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new FuzzAdapter(this, mFavouritesList);
        mRecyclerView.setAdapter(mAdapter);
        */
    }
}