package com.example.rodrigo.fuzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.rodrigo.fuzz.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Rodrigo Cericatto on 25/05/2015.
 */
public class ImageActivity extends Activity {

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        // Set the layout.
        setLayout();
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public void setLayout() {
        // Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle(getString(R.string.activity_image));
        toolbar.setNavigationIcon(R.drawable.ic_home_as_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_up_to_outside, R.anim.slide_up_from_outside);
            }
        });

        // Get extras.
        String link = getIntent().getExtras().getString(MainActivity.LINK_EXTRA);

        // Image.
        ImageView image = (ImageView) findViewById(R.id.id_image_view);
        if (link != null && link.length() > 0) {
            Picasso.with(this).load(link).into(image);
        }
    }
}