package com.apreciasoft.mobile.remisclasea.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.apreciasoft.mobile.remisclasea.Entity.LiquidationEntity;
import com.apreciasoft.mobile.remisclasea.R;

import java.util.List;

/**
 * Created by usario on 4/7/2017.
 */



public class AdapterRows extends RecyclerView.Adapter<AdapterRows.ViewHolder> {
    private List<LiquidationEntity> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView mcar_liquidation;
        public TextView minfo_text;
        public TextView mtxt_type;
        public TextView mtxt_cod;
        public TextView mtxt_date;
        public ImageButton mImageButton;


        public ViewHolder(View v) {
            super(v);

            mcar_liquidation = (CardView) v.findViewById(R.id.car_liquidation);
            minfo_text = (TextView) v.findViewById(R.id.info_text);
            mtxt_type = (TextView) v.findViewById(R.id.txt_type);
            mtxt_cod = (TextView) v.findViewById(R.id.txt_cod);
            mtxt_date = (TextView) v.findViewById(R.id.txt_date);
            mImageButton= (ImageButton) v.findViewById(R.id.imageButton);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRows(List<LiquidationEntity> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterRows.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowslist, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterRows.ViewHolder holder, final int position) {
        holder.minfo_text.setText(mDataset.get(position).getTotalLiquidation()+"$");
        holder.mtxt_cod.setText(mDataset.get(position).getCodeCardx());
        holder.mtxt_date.setText(mDataset.get(position).getDateTrasaction());


        if (mDataset.get(position).getIdTipeOperation() == 1) {
            holder.mtxt_type.setText("Liquidacion");
        }else  {
            holder.mtxt_type.setText("Adelanto");
        }

        if (mDataset.get(position).getIsProcesPayment() == 1) {
            holder.mtxt_type.setText("Pago De Liquidacion");
        }


        holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.mImageButton,position);
            }
        });



    }

    private void showPopupMenu(View view,int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(),view );
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.liquidationcarview, popup.getMenu());
       // popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }






}



