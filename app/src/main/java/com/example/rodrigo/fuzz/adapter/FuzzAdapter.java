package com.example.rodrigo.fuzz.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rodrigo.fuzz.R;
import com.example.rodrigo.fuzz.model.Fuzz;

import java.util.List;

/**
 * Created by Rodrigo Cericatto on 22/05/2015.
 */
public class FuzzAdapter extends RecyclerView.Adapter<FuzzAdapter.ViewHolder> {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private List<Fuzz> mItems;
    private Activity mContext;

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public FuzzAdapter(Activity context, List<Fuzz> items) {
        mContext = context;
        mItems = items;
    }

    //--------------------------------------------------
    // Adapter Methods
    //--------------------------------------------------

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fuzz, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Fuzz item = mItems.get(position);
    }

    @Override
    public int getItemCount() {
        if (mItems != null && mItems.size() > 0) {
            return mItems.size();
        }
        return 0;
    }

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder {
        /*
        public ImageView picture;
        public TextView name;
        public ImageView heart;
        */

        public ViewHolder(View view) {
            super(view);
            /*
            picture = (ImageView) view.findViewById(R.id.id_adapter_picture_image_view);
            name = (TextView) view.findViewById(R.id.id_adapter_name_text_view);
            heart = (ImageView) view.findViewById(R.id.id_adapter_heart_image_view);
            */
        }
    }
}