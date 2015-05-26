package com.example.rodrigo.fuzz.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodrigo.fuzz.R;
import com.example.rodrigo.fuzz.activity.MainActivity;
import com.example.rodrigo.fuzz.model.Fuzz;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rodrigo Cericatto on 22/05/2015.
 */
public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private List<Fuzz> mItems;
    private Activity mContext;

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public AllAdapter(Activity context, List<Fuzz> items) {
        mContext = context;
        mItems = items;
    }

    //--------------------------------------------------
    // Adapter Methods
    //--------------------------------------------------

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_all, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Fuzz item = mItems.get(position);

        setIdField(holder, item);
        setTypeField(holder, item);
        setDataField(holder, item, position);
    }

    @Override
    public int getItemCount() {
        if (mItems != null && mItems.size() > 0) {
            return mItems.size();
        }
        return 0;
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public void setIdField(ViewHolder holder, Fuzz item) {
        final MainActivity activity = (MainActivity)mContext;

        // Id.
        String text = mContext.getString(R.string.adapter_id);
        holder.id.setText(text + " " + item.getId());

        // Id Listener.
        holder.id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openWebViewActivity();
            }
        });
    }

    public void setTypeField(ViewHolder holder, Fuzz item) {
        final MainActivity activity = (MainActivity)mContext;

        // Type.
        String text = mContext.getString(R.string.adapter_type);
        holder.type.setText(text + " " + item.getType());

        // Type Listener.
        holder.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openWebViewActivity();
            }
        });
    }

    public void setDataField(ViewHolder holder, Fuzz item, final Integer position) {
        final MainActivity activity = (MainActivity)mContext;
        String type = item.getType();

        // Verifies the item type.
        if (type.equals("image")) {
            setDataImageField(holder, item, position);
        } else {
            setDataTextField(holder, item);
        }
    }

    public void setDataTextField(ViewHolder holder, Fuzz item) {
        final MainActivity activity = (MainActivity)mContext;
        holder.dataImage.setVisibility(View.GONE);

        String text = mContext.getString(R.string.adapter_data);
        holder.dataText.setText(text + " " + item.getData());
        holder.dataText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openWebViewActivity();
            }
        });
    }

    public void setDataImageField(ViewHolder holder, Fuzz item, final Integer position) {
        final MainActivity activity = (MainActivity)mContext;
        holder.dataImage.setVisibility(View.VISIBLE);

        String text = mContext.getString(R.string.adapter_data);
        holder.dataText.setText(text);
        String data = item.getData();

        // Inflates the image.
        Picasso.with(mContext).load(data).error(R.drawable.retrofit_error)
            .placeholder(R.drawable.place_holder).into(holder.dataImage);

        // Data listener.
        holder.dataImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = mItems.get(position).getData();
                activity.openImageActivity(link);
            }
        });
    }

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView type;
        public TextView dataText;
        public ImageView dataImage;

        public ViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.id_adapter_all_id_text_view);
            type = (TextView) view.findViewById(R.id.id_adapter_all_type_text_view);
            dataText = (TextView) view.findViewById(R.id.id_adapter_all_data_text_view);
            dataImage = (ImageView) view.findViewById(R.id.id_adapter_all_data_image_view);
        }
    }
}