package com.example.rodrigo.fuzz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rodrigo.fuzz.R;
import com.example.rodrigo.fuzz.adapter.ViewPagerAdapter;
import com.example.rodrigo.fuzz.manager.ContentManager;
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
    public static final String LINK_EXTRA = "link_extra";

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private MaterialDialog mMaterialDialog;
    private RetrofitService mService;

    private ViewPager mPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private TextView mStatusTextView;

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

        // Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        // Status.
        mStatusTextView = (TextView) findViewById(R.id.id_text_view);
        mStatusTextView.setText(getString(R.string.fragment_all));

        // View Pager.
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.id_view_pager);
        mPager.setAdapter(mViewPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String text = "";
                switch (position) {
                    case 0:
                        text = getString(R.string.fragment_all);
                        break;
                    case 1:
                        text = getString(R.string.fragment_text);
                        break;
                    case 2:
                        text = getString(R.string.fragment_images);
                        break;
                }
                mStatusTextView.setText(text);
            }

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
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
                ContentManager.getInstance().setFuzzList(object);
                dataLoadedSuccessfully(object);
            }
        });
    }

    public void dataLoadedSuccessfully(List<Fuzz> object) {
        // Data loaded.
        //mRecyclerView.setVisibility(View.VISIBLE);
        mMaterialDialog.cancel();
    }

    //--------------------------------------------------
    // Outside Methods
    //--------------------------------------------------

    public void openImageActivity(String link) {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        Bundle extras = new Bundle();
        extras.putString(LINK_EXTRA, link);
        intent.putExtras(extras);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_from_outside, R.anim.slide_up_to_outside);
    }
}