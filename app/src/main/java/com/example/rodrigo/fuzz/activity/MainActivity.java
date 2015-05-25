package com.example.rodrigo.fuzz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rodrigo.fuzz.R;
import com.example.rodrigo.fuzz.adapter.ViewPagerAdapter;
import com.example.rodrigo.fuzz.manager.ContentManager;
import com.example.rodrigo.fuzz.model.Fuzz;
import com.example.rodrigo.fuzz.service.RetrofitService;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

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
    public static final String LINK_IMAGE_EXTRA = "link_image_extra";
    public static final String LINK_WEB_VIEW_EXTRA = "link_web_view_extra";

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private MaterialDialog mMaterialDialog;
    private RetrofitService mService;

    private Toolbar mToolbar;
    private ViewPager mPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private PageIndicator mIndicator;
    private Integer mCurrentPage = 0;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLayout();
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public void setLayout() {
        // Progress Bar.
        mMaterialDialog = new MaterialDialog.Builder(this).title(R.string.progress_dialog_retrofit)
            .content(R.string.please_wait).progress(true, 0).show();

        // Toolbar.
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mToolbar.setTitle(getString(R.string.app_name));

        // Retrofit.
        getFuzzData();
    }

    public void initViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.id_view_pager);
        mPager.setAdapter(mViewPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String text = getString(R.string.app_name);
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
                mToolbar.setTitle(text);
            }

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    public void initIndicator() {
        mIndicator = (CirclePageIndicator) findViewById(R.id.id_page_indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
            }

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
        initViewPager();
        initIndicator();
        mMaterialDialog.cancel();
    }

    //--------------------------------------------------
    // Outside Methods
    //--------------------------------------------------

    public void openImageActivity(String link) {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        Bundle extras = new Bundle();
        extras.putString(LINK_IMAGE_EXTRA, link);
        intent.putExtras(extras);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_from_outside, R.anim.slide_up_to_outside);
    }

    public void openWebViewActivity() {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_from_outside, R.anim.slide_up_to_outside);
    }
}