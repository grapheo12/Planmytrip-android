package com.example.planmytrip;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.planmytrip.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHotelViewRecyclerViewAdapter extends RecyclerView.Adapter<MyHotelViewRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;

    public MyHotelViewRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_hotel_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mId.setText(String.valueOf(mValues.get(position).hotel_id));
        holder.mName.setText(mValues.get(position).hotel_name);
        holder.mAddress.setText(mValues.get(position).hotel_address);
        holder.mCost.setText(String.valueOf(mValues.get(position).hotel_cost));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mId;
        public final TextView mName;
        public final TextView mAddress;
        public final TextView mCost;

        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mId = (TextView) view.findViewById(R.id.item_number);
            mName = (TextView) view.findViewById(R.id.hotel_name);
            mAddress = (TextView) view.findViewById(R.id.hotel_address);
            mCost = (TextView) view.findViewById(R.id.hotel_cost);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}