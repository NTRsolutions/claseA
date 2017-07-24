package com.apreciasoft.admin.asremis.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apreciasoft.admin.asremis.Entity.InfoTravelEntity;
import com.apreciasoft.admin.asremis.R;

import java.util.List;

/**
 * Created by Admin on 19/1/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<InfoTravelEntity> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public TextView mtv_blah;
        public  TextView mtv_amount;


        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.car_travel);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
            mtv_amount = (TextView) v.findViewById(R.id.tv_amount);
            mtv_blah = (TextView) v.findViewById(R.id.tv_blah);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<InfoTravelEntity> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_travel, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getCodTravel());
        holder.mtv_blah.setText(mDataset.get(position).getNameOrigin());
        holder.mtv_amount.setText(mDataset.get(position).getTotalAmount());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}