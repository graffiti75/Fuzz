package com.example.rodrigo.fuzz.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rodrigo.fuzz.R;

/**
 * Created by Rodrigo Cericatto on 25/05/2015.
 */
public class WebViewActivity extends Activity {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    public static final String LINK = "https://fuzzproductions.com/";

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private MaterialDialog mMaterialDialog;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // Set the layout.
        setLayout();
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public void setLayout() {
        // Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle(getString(R.string.activity_web_view));
        toolbar.setNavigationIcon(R.drawable.ic_home_as_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_up_to_outside, R.anim.slide_up_from_outside);
            }
        });

        // Material Dialog.
        mMaterialDialog =  new MaterialDialog.Builder(this).title(R.string.progress_dialog)
            .content(R.string.please_wait).progress(true, 0).show();

        // Web View.
        WebView webView = (WebView) findViewById(R.id.id_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, final String url) {
                mMaterialDialog.cancel();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mMaterialDialog.show();
            }
        });
        webView.loadUrl(LINK);
    }
}