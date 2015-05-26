package com.example.rodrigo.fuzz.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rodrigo.fuzz.R;
import com.example.rodrigo.fuzz.activity.MainActivity;
import com.example.rodrigo.fuzz.model.Fuzz;

import java.util.List;

/**
 * Created by Rodrigo Cericatto on 23/05/2015.
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private List<Fuzz> mItems;
    private Activity mContext;

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public TextAdapter(Activity context, List<Fuzz> items) {
        mContext = context;
        mItems = items;
    }

    //--------------------------------------------------
    // Adapter Methods
    //--------------------------------------------------

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_text, parent, false);
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

        // Data.
        String text = mContext.getString(R.string.adapter_data);
        holder.data.setText(text + " " + item.getData());

        // Data Listener.
        holder.data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openWebViewActivity();
            }
        });
    }

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView type;
        public TextView data;

        public ViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.id_adapter_text_id_text_view);
            type = (TextView) view.findViewById(R.id.id_adapter_text_type_text_view);
            data = (TextView) view.findViewById(R.id.id_adapter_text_data_text_view);
        }
    }
}