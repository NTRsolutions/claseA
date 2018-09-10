package com.apreciasoft.admin.remisclasea.Adapter;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.apreciasoft.admin.remisclasea.Entity.notification;
import com.apreciasoft.admin.remisclasea.Fracments.NotificationsFrangment;
import com.apreciasoft.admin.remisclasea.R;
import java.util.List;

/**
 * Created by usario on 25/4/2017.
 */



public class NorificationAdapter
        extends RecyclerView.Adapter<NorificationAdapter.MyViewHolder>  {

    private List<notification> mDataset;
    private Context context;
    private Fragment fragment;
    private final OnItemClickListener listener;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CardView mCardView;
        public TextView mTextView;
        public TextView mtv_blah;
        public MyViewHolder temObj = null;
        public ImageButton mImageButton;


        public MyViewHolder(View v) {
            super(v);

            temObj =  this;

            mCardView = (CardView) v.findViewById(R.id.car_notifications);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
            mtv_blah = (TextView) v.findViewById(R.id.tv_blah);
            TextView mtv_blah2 = (TextView) v.findViewById(R.id.tv_blah2);
            TextView mtv_blah3 = (TextView) v.findViewById(R.id.tv_blah3);

            mtv_blah2.setVisibility(View.GONE);
            mtv_blah3.setVisibility(View.GONE);

            mImageButton= (ImageButton) v.findViewById(R.id.imageButton);



        }

        public void bind(final notification item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }


        @Override
        public void onClick(View v) {

        }
    }

    public interface  OnItemClickListener{
        void onItemClick(notification item);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public NorificationAdapter(List<notification> myDataset, Fragment fragment, Context context,OnItemClickListener listener) {

        this.context = context;
        mDataset = myDataset;
        this.fragment = fragment;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NorificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_notifications, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset.get(position).getTitle());
        holder.mtv_blah.setText(mDataset.get(position).getSubTitle());

        if(mDataset.get(position).isRead == 1)
        {
            holder.mImageButton.setEnabled(false);
        }


/*        holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.mImageButton,position);
            }
        });*/


        holder.bind(mDataset.get(position), listener);

    }


    private void showPopupMenu(View view, final int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(),view );
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.notification_option, popup.getMenu());
       // popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.opt_confirm:
                        // do what you need.
                        ((NotificationsFrangment)fragment).event_confirm(position);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
        popup.show();
    }

    @Override
    public int getItemCount() {

        int r = 0;
        if(mDataset != null)
        {
            r = mDataset.size();
        }
        return r;
    }
}